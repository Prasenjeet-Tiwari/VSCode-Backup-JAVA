import { useEffect, useRef, useState, useMemo, useCallback } from "react";

type Mood = "shy" | "brat" | "sweet" | "dramatic";

type Stats = {
  slaps: number;
  sessionStart: number;
  lastSlap: number | null;
  combo: number;
  maxCombo: number;
};

const MOOD_META: Record<Mood, { emoji: string; accent: string; ring: string; blush: string; tag: string }> = {
  shy: { emoji: "🫣", accent: "from-rose-400 to-pink-500", ring: "ring-rose-200", blush: "bg-rose-300/40", tag: "soft + giggly" },
  brat: { emoji: "😼", accent: "from-amber-400 to-orange-500", ring: "ring-amber-200", blush: "bg-amber-300/40", tag: "cheeky + loud" },
  sweet: { emoji: "😇", accent: "from-sky-400 to-indigo-500", ring: "ring-sky-200", blush: "bg-sky-300/40", tag: "bubbly + warm" },
  dramatic: { emoji: "🎭", accent: "from-fuchsia-500 to-violet-600", ring: "ring-fuchsia-200", blush: "bg-fuchsia-300/40", tag: "extra • on purpose" },
};

function clamp(n: number, min: number, max: number) {
  return Math.min(max, Math.max(min, n));
}

function dbFromRms(rms: number) {
  return 20 * Math.log10(rms + 1e-9);
}

function formatDuration(ms: number) {
  const s = Math.floor(ms / 1000);
  const m = Math.floor(s / 60);
  const rs = s % 60;
  if (m === 0) return `${s}s`;
  return `${m}m ${rs.toString().padStart(2, "0")}s`;
}

export default function App() {
  // UI state
  const [permission, setPermission] = useState<"idle" | "granted" | "denied" | "unsupported">("idle");
  const [listening, setListening] = useState(false);
  const [sensitivity, setSensitivity] = useState(0.22); // 0-1 (lower = less sensitive)
  const [enthusiasm, setEnthusiasm] = useState(0.65); // loudness / intensity
  const [mood, setMood] = useState<Mood>("brat");
  const [shakeScreen, setShakeScreen] = useState(true);
  const [visualOnly, setVisualOnly] = useState(false);
  const [calibrating, setCalibrating] = useState(false);
  const [showOnboard, setShowOnboard] = useState(true);
  const [showHelp, setShowHelp] = useState(false);

  // Refs for audio graph
  const audioCtxRef = useRef<AudioContext | null>(null);
  const mediaStreamRef = useRef<MediaStream | null>(null);
  const analyserRef = useRef<AnalyserNode | null>(null);
  const sourceRef = useRef<MediaStreamAudioSourceNode | null>(null);
  const dataArrayRef = useRef<Uint8Array | null>(null);
  const floatArrayRef = useRef<Float32Array<ArrayBuffer> | null>(null);
  const rafRef = useRef<number | null>(null);

  // runtime
  const [levelDb, setLevelDb] = useState(-90);
  const [peakDb, setPeakDb] = useState(-90);
  const [isHot, setIsHot] = useState(false);
  const lastTriggerRef = useRef(0);
  const prevRmsRef = useRef(0);
  const cooldownMsRef = useRef(520);

  // sample audio
  const fileInputRef = useRef<HTMLInputElement | null>(null);
  const [sampleBuffers, setSampleBuffers] = useState<Partial<Record<Mood, AudioBuffer>>>({});
  const [useSamples, setUseSamples] = useState(false);
  const activeAudioRef = useRef<HTMLAudioElement | null>(null);
  const comboTimerRef = useRef<number | null>(null);
  const shakeTimerRef = useRef<number | null>(null);

  const [stats, setStats] = useState<Stats>({
    slaps: 0,
    sessionStart: Date.now(),
    lastSlap: null,
    combo: 0,
    maxCombo: 0,
  });

  const [blush, setBlush] = useState(false);
  const [jiggle, setJiggle] = useState(false);

  const moodMeta = useMemo(() => MOOD_META[mood], [mood]);

  // derived threshold in dB from sensitivity (higher sensitivity = lower threshold)
  const thresholdDb = useMemo(() => {
    // Map sensitivity 0..1 to threshold -22..-52 dB (reduced sensitivity by default)
    const min = -52;
    const max = -22;
    return max - (max - min) * (1 - sensitivity);
  }, [sensitivity]);

  // Helper: play audio sample
  const playSample = useCallback(
    (moodKey: Mood, gainMul = 1) => {
      const ctx = audioCtxRef.current;
      if (!ctx || visualOnly) return false;
      const buf = sampleBuffers[moodKey] || sampleBuffers.brat || sampleBuffers.sweet || sampleBuffers.shy || sampleBuffers.dramatic;
      if (!buf) return false;

      // Stop previous sample audio
      if (activeAudioRef.current) {
        try { activeAudioRef.current.pause(); } catch {}
        activeAudioRef.current = null;
      }

      const src = ctx.createBufferSource();
      src.buffer = buf;
      // slight random playback rate for variation
      src.playbackRate.value = 0.98 + Math.random() * 0.07;

      const g = ctx.createGain();
      const baseGain = 0.25 + enthusiasm * 0.55;
      g.gain.setValueAtTime(0.0001, ctx.currentTime);
      g.gain.exponentialRampToValueAtTime(Math.min(1.2, baseGain * gainMul), ctx.currentTime + 0.02);
      g.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + buf.duration + 0.05);

      src.connect(g).connect(ctx.destination);
      src.start();

      return true;
    },
    [enthusiasm, sampleBuffers, visualOnly]
  );

  // Utility: play procedurally generated "moan" (SFW synth, suggestive, no explicit audio)
  const playMoan = useCallback(
    (intensity = 0.6) => {
      if (visualOnly) return;
      const ctx = audioCtxRef.current;
      if (!ctx) return;

      // try samples first
      if (useSamples && playSample(mood, 0.9 + intensity * 0.3)) {
        return;
      }

      const now = ctx.currentTime;

      // Core voice: two formant-ish bandpasses on a breathy source
      const breath = ctx.createBufferSource();
      const buffer = ctx.createBuffer(1, Math.floor(ctx.sampleRate * 0.9), ctx.sampleRate);
      const ch = buffer.getChannelData(0);
      // pinkish noise
      let b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, b6 = 0;
      for (let i = 0; i < ch.length; i++) {
        const white = Math.random() * 2 - 1;
        b0 = 0.99886 * b0 + white * 0.0555179;
        b1 = 0.99332 * b1 + white * 0.0750759;
        b2 = 0.969 * b2 + white * 0.153852;
        b3 = 0.8665 * b3 + white * 0.3104856;
        b4 = 0.55 * b4 + white * 0.5329522;
        b5 = -0.7616 * b5 - white * 0.016898;
        ch[i] = b0 + b1 + b2 + b3 + b4 + b5 + b6 + white * 0.5362;
        ch[i] *= 0.11;
        b6 = white * 0.115926;
      }
      breath.buffer = buffer;
      breath.loop = true;

      const breathGain = ctx.createGain();
      breathGain.gain.setValueAtTime(0, now);

      // Formant chain
      const hp = ctx.createBiquadFilter();
      hp.type = "highpass";
      hp.frequency.value = 180;
      hp.Q.value = 0.9;

      const bp1 = ctx.createBiquadFilter();
      bp1.type = "bandpass";
      // vowel-ish centers guided by mood
      const vowelCenters: Record<Mood, number> = { shy: 720, brat: 880, sweet: 640, dramatic: 950 };
      bp1.frequency.setValueAtTime(vowelCenters[mood], now);
      bp1.Q.value = 6;

      const bp2 = ctx.createBiquadFilter();
      bp2.type = "bandpass";
      bp2.frequency.setValueAtTime(vowelCenters[mood] * 2.2, now);
      bp2.Q.value = 6;

      const formantGain = ctx.createGain();
      formantGain.gain.value = 0;

      // Pitched layer for "voice"
      const osc = ctx.createOscillator();
      osc.type = "triangle";
      const baseFreq = 180 + intensity * 90 + (mood === "dramatic" ? 40 : 0) - (mood === "sweet" ? 20 : 0);
      osc.frequency.setValueAtTime(baseFreq, now);
      // playful glide
      osc.frequency.exponentialRampToValueAtTime(baseFreq * (1.25 + intensity * 0.3), now + 0.22);
      osc.frequency.exponentialRampToValueAtTime(baseFreq * 0.92, now + 0.55);

      const oscGain = ctx.createGain();
      oscGain.gain.setValueAtTime(0, now);

      // Master chain
      const master = ctx.createGain();
      const masterOut = Math.min(1, 0.12 + enthusiasm * 0.6 + intensity * 0.35);
      master.gain.setValueAtTime(0, now);
      master.gain.linearRampToValueAtTime(masterOut, now + 0.015);
      master.gain.exponentialRampToValueAtTime(0.0008, now + 0.78);

      // Light chorus via short delay modulation
      const delay = ctx.createDelay(0.04);
      delay.delayTime.setValueAtTime(0.012, now);
      const lfo = ctx.createOscillator();
      lfo.frequency.value = 5.2;
      const lfoGain = ctx.createGain();
      lfoGain.gain.value = 0.004;
      lfo.connect(lfoGain).connect(delay.delayTime);
      lfo.start(now);
      lfo.stop(now + 0.85);

      // Wire it
      breath.connect(breathGain).connect(hp).connect(bp1);
      const split = ctx.createGain();
      split.gain.value = 0.75;
      bp1.connect(split);
      split.connect(bp2).connect(formantGain);
      split.connect(formantGain);

      osc.connect(oscGain);

      const mix = ctx.createGain();
      mix.gain.value = 0.9;
      formantGain.connect(mix);
      oscGain.connect(mix);

      mix.connect(delay).connect(master);
      mix.connect(master); // parallel dry

      // envelopes
      const envAccent = 0.6 + intensity * 0.9;
      // Breath swell
      breathGain.gain.setValueAtTime(0.0001, now);
      breathGain.gain.exponentialRampToValueAtTime(0.18 * envAccent, now + 0.07);
      breathGain.gain.exponentialRampToValueAtTime(0.0005, now + 0.72);

      // Formants
      formantGain.gain.setValueAtTime(0.0001, now);
      formantGain.gain.exponentialRampToValueAtTime(0.9 * envAccent, now + 0.06);
      formantGain.gain.exponentialRampToValueAtTime(0.001, now + 0.68);

      // Osc
      oscGain.gain.setValueAtTime(0.0001, now);
      oscGain.gain.exponentialRampToValueAtTime(0.22 * (0.7 + enthusiasm * 0.6) * envAccent, now + 0.09);
      oscGain.gain.exponentialRampToValueAtTime(0.0005, now + 0.64);

      // dynamics: sidechainy dip after hit
      const comp = ctx.createDynamicsCompressor();
      comp.threshold.value = -24;
      comp.knee.value = 20;
      comp.ratio.value = 8;
      comp.attack.value = 0.004;
      comp.release.value = 0.22;

      master.connect(comp).connect(ctx.destination);

      // Start nodes
      breath.start(now);
      osc.start(now);
      osc.stop(now + 0.9);
      breath.stop(now + 0.9);

      // safety cleanup
      setTimeout(() => {
        try {
          hp.disconnect();
          bp1.disconnect();
          bp2.disconnect();
          breathGain.disconnect();
          formantGain.disconnect();
          osc.disconnect();
          oscGain.disconnect();
          mix.disconnect();
          delay.disconnect();
          lfo.disconnect();
          lfoGain.disconnect();
          master.disconnect();
          comp.disconnect();
        } catch {}
      }, 1200);
    },
    [enthusiasm, mood, visualOnly]
  );

  // Visual reaction
  const reactToSlap = useCallback(() => {
    setBlush(true);
    setJiggle(true);
    setIsHot(true);

    if (shakeScreen) {
      document.documentElement.classList.add("slap-shake");
      if (shakeTimerRef.current) window.clearTimeout(shakeTimerRef.current);
      shakeTimerRef.current = window.setTimeout(() => {
        document.documentElement.classList.remove("slap-shake");
      }, 320);
    }

    setTimeout(() => setBlush(false), 650);
    setTimeout(() => setJiggle(false), 420);
    setTimeout(() => setIsHot(false), 220);

    // combo
    setStats((s) => {
      const now = Date.now();
      const combo = s.lastSlap && now - s.lastSlap < 2000 ? s.combo + 1 : 1;
      const maxCombo = Math.max(s.maxCombo, combo);
      if (comboTimerRef.current) window.clearTimeout(comboTimerRef.current);
      comboTimerRef.current = window.setTimeout(() => {
        setStats((prev) => ({ ...prev, combo: 0 }));
      }, 2000);
      return {
        ...s,
        slaps: s.slaps + 1,
        lastSlap: now,
        combo,
        maxCombo,
      };
    });
  }, [shakeScreen]);

  // Trigger
  const trigger = useCallback(
    (strength: number) => {
      const nowMs = performance.now();
      if (nowMs - lastTriggerRef.current < cooldownMsRef.current) return;
      lastTriggerRef.current = nowMs;

      reactToSlap();
      playMoan(clamp(strength, 0, 1));
    },
    [playMoan, reactToSlap]
  );

  // Audio loop
  const loop = useCallback(() => {
    const analyser = analyserRef.current;
    const ctx = audioCtxRef.current;
    if (!analyser || !ctx) return;

    // time domain for transient detection
    const floatArr = floatArrayRef.current!;
    (analyser.getFloatTimeDomainData as unknown as (a: Float32Array) => void)(floatArr);

    // RMS
    let sum = 0;
    for (let i = 0; i < floatArr.length; i++) {
      const v = floatArr[i];
      sum += v * v;
    }
    const rms = Math.sqrt(sum / floatArr.length);
    const db = dbFromRms(rms);

    // attack detection
    const attack = rms - prevRmsRef.current;
    prevRmsRef.current = rms * 0.4 + prevRmsRef.current * 0.6; // smooth

    setLevelDb(db);
    setPeakDb((p) => Math.max(p * 0.995, db));

    // transient: quick positive attack AND level over threshold (reduced sensitivity)
    const thresholdLin = Math.pow(10, thresholdDb / 20);
    const isLoud = rms > thresholdLin * 1.25;
    const isSharp = attack > thresholdLin * 0.5;

    if (isLoud && isSharp) {
      const strength = clamp((db - thresholdDb) / 28, 0.15, 1);
      trigger(strength);
    }

    rafRef.current = requestAnimationFrame(loop);
  }, [thresholdDb, trigger]);

  const stopListening = useCallback(() => {
    setListening(false);
    if (rafRef.current) cancelAnimationFrame(rafRef.current);
    rafRef.current = null;

    if (sourceRef.current) {
      try {
        sourceRef.current.disconnect();
      } catch {}
      sourceRef.current = null;
    }
    if (analyserRef.current) {
      try {
        analyserRef.current.disconnect();
      } catch {}
      analyserRef.current = null;
    }
    if (mediaStreamRef.current) {
      mediaStreamRef.current.getTracks().forEach((t) => t.stop());
      mediaStreamRef.current = null;
    }
    // keep audio context alive for synth
  }, []);

  const startListening = useCallback(async () => {
    if (!("AudioContext" in window) || !navigator.mediaDevices?.getUserMedia) {
      setPermission("unsupported");
      return;
    }
    try {
      const stream = await navigator.mediaDevices.getUserMedia({
        audio: {
          echoCancellation: false,
          noiseSuppression: false,
          autoGainControl: false,
          channelCount: 1,
        },
        video: false,
      });
      mediaStreamRef.current = stream;
      setPermission("granted");

      let ctx = audioCtxRef.current;
      if (!ctx) {
        ctx = new (window.AudioContext || (window as unknown as { webkitAudioContext: typeof AudioContext }).webkitAudioContext)({
          latencyHint: "interactive",
        });
        audioCtxRef.current = ctx;
      }
      if (ctx.state === "suspended") await ctx.resume();

      const source = ctx.createMediaStreamSource(stream);
      sourceRef.current = source;

      const analyser = ctx.createAnalyser();
      analyser.fftSize = 2048;
      analyser.smoothingTimeConstant = 0.0;
      analyserRef.current = analyser;

      source.connect(analyser);

      dataArrayRef.current = new Uint8Array(analyser.frequencyBinCount);
      floatArrayRef.current = new Float32Array(analyser.fftSize) as Float32Array<ArrayBuffer>;

      setListening(true);
      setStats((s) => ({ ...s, sessionStart: Date.now(), slaps: 0, combo: 0, maxCombo: 0, lastSlap: null }));
      prevRmsRef.current = 0;

      // prime loop
      if (rafRef.current) cancelAnimationFrame(rafRef.current);
      rafRef.current = requestAnimationFrame(loop);
    } catch {
      setPermission("denied");
    }
  }, [loop]);

  // calibration: auto set threshold to ambient + margin
  const calibrate = useCallback(async () => {
    if (!analyserRef.current || !floatArrayRef.current) return;
    setCalibrating(true);

    const samples: number[] = [];
    const analyser = analyserRef.current;
    const buf = floatArrayRef.current;

    const end = performance.now() + 1500;
    const step = () => {
      (analyser.getFloatTimeDomainData as unknown as (a: Float32Array) => void)(buf);
      let sum = 0;
      for (let i = 0; i < buf.length; i++) sum += buf[i] * buf[i];
      const rms = Math.sqrt(sum / buf.length);
      samples.push(dbFromRms(rms));
      if (performance.now() < end) {
        requestAnimationFrame(step);
      } else {
        // median ambient
        samples.sort((a, b) => a - b);
        const med = samples[Math.floor(samples.length * 0.5)] ?? -60;
        const target = clamp(med + 22, -52, -22); // higher margin = less sensitive
        // invert mapping to sensitivity
        const min = -52, max = -22;
        const sens = 1 - (max - target) / (max - min);
        setSensitivity(clamp(sens, 0.05, 0.95));
        setCalibrating(false);
      }
    };
    requestAnimationFrame(step);
  }, []);

  // cleanup
  useEffect(() => {
    return () => {
      stopListening();
      audioCtxRef.current?.close().catch(() => {});
      if (comboTimerRef.current) window.clearTimeout(comboTimerRef.current);
      if (shakeTimerRef.current) window.clearTimeout(shakeTimerRef.current);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // keyboard: space to test
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (e.code === "Space") {
        e.preventDefault();
        trigger(0.8);
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [trigger]);

  // session time
  const [nowTick, setNowTick] = useState(0);
  useEffect(() => {
    const id = setInterval(() => setNowTick((t) => t + 1), 1000);
    return () => clearInterval(id);
  }, []);
  const sessionMs = useMemo(() => (listening ? Date.now() - stats.sessionStart : 0), [listening, stats.sessionStart, nowTick]);

  return (
    <div className="min-h-screen w-full bg-[#0b0b10] text-zinc-100 antialiased selection:bg-fuchsia-500/30 selection:text-white">
      <style>{`
        @keyframes slap {
          0% { transform: translate3d(0,0,0) rotate(0); }
          20% { transform: translate3d(-1px,1px,0) rotate(-0.4deg); }
          40% { transform: translate3d(2px,-1px,0) rotate(0.6deg); }
          60% { transform: translate3d(-2px,0,0) rotate(-0.3deg); }
          80% { transform: translate3d(1px,1px,0) rotate(0.2deg); }
          100% { transform: translate3d(0,0,0) rotate(0); }
        }
        html.slap-shake body { animation: slap 0.32s cubic-bezier(.36,.07,.19,.97); }

        @keyframes blushPulse {
          0% { opacity: 0; transform: scale(0.96); }
          20% { opacity: 1; transform: scale(1); }
          100% { opacity: 0; transform: scale(1.04); }
        }
        @keyframes jiggle {
          0% { transform: translateY(0) scale(1); }
          30% { transform: translateY(-2px) scale(1.01); }
          60% { transform: translateY(1px) scale(0.995); }
          100% { transform: translateY(0) scale(1); }
        }
        @keyframes meter {
          0% { transform: translateX(-100%); }
          100% { transform: translateX(100%); }
        }
      `}</style>

      {/* Header */}
      <header className="sticky top-0 z-40 backdrop-blur-xl bg-[#0b0b10]/70 border-b border-white/5">
        <div className="mx-auto max-w-6xl px-4 sm:px-6 lg:px-8 h-[68px] flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="relative">
              <div className="absolute inset-0 blur-2xl bg-fuchsia-500/20 rounded-full" />
              <div className="relative h-10 w-10 rounded-2xl bg-gradient-to-br from-fuchsia-500 to-violet-600 shadow-[0_0_0_1px_rgba(255,255,255,0.08)_inset,0_8_30px_-10px_rgba(217,70,239,0.6)] grid place-items-center">
                <span className="text-[22px] leading-none">🫦</span>
              </div>
            </div>
            <div>
              <div className="font-semibold tracking-tight text-[17px]">Spank • Web</div>
              <div className="text-[11px] text-zinc-400 -mt-0.5">Playful mic-reactive toy • SFW synth</div>
            </div>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={() => setShowHelp(v => !v)}
              className="group relative inline-flex items-center gap-2 rounded-full border border-white/10 bg-white/[0.04] px-3 py-1.5 text-sm hover:bg-white/[0.08] transition"
              aria-expanded={showHelp}
            >
              <span className="h-2 w-2 rounded-full bg-emerald-400 shadow-[0_0_10px_rgba(16,185,129,0.9)]" />
              <span className="hidden sm:inline">How it works</span>
              <span className="sm:hidden">Help</span>
            </button>
            <a
              href="https://github.com/taigrr/spank"
              target="_blank"
              rel="noreferrer"
              className="hidden sm:inline-flex items-center gap-2 rounded-full border border-white/10 bg-white/[0.03] px-3 py-1.5 text-sm hover:bg-white/[0.07] transition"
            >
              <svg width="16" height="16" viewBox="0 0 24 24" fill="currentColor" className="opacity-80">
                <path d="M12 .5C5.73.5.75 5.48.75 11.75c0 4.92 3.19 9.09 7.61 10.56.56.1.76-.24.76-.53 0-.26-.01-1.12-.02-2.03-3.1.67-3.76-1.33-3.76-1.33-.5-1.27-1.22-1.61-1.22-1.61-1-.68.07-.66.07-.66 1.1.08 1.68 1.13 1.68 1.13.98 1.68 2.57 1.2 3.2.92.1-.71.38-1.2.69-1.47-2.47-.28-5.07-1.23-5.07-5.47 0-1.21.43-2.2 1.14-2.97-.12-.28-.5-1.4.11-2.92 0 0 .93-.3 3.05 1.13a10.5 10.5 0 0 1 5.56 0c2.12-1.43 3.05-1.13 3.05-1.13.61 1.52.23 2.64.11 2.92.71.77 1.14 1.76 1.14 2.97 0 4.25-2.6 5.18-5.08 5.46.39.33.73.98.73 1.98 0 1.43-.01 2.58-.01 2.93 0 .29.2.64.77.53 4.42-1.47 7.61-5.64 7.61-10.56C23.25 5.48 18.27.5 12 .5z" />
              </svg>
              Inspiration
            </a>
          </div>
        </div>

        {showHelp && (
          <div className="border-t border-white/5 bg-zinc-950/60">
            <div className="mx-auto max-w-6xl px-4 sm:px-6 lg:px-8 py-4 grid gap-4 md:grid-cols-3 text-sm">
              <div className="rounded-2xl border border-white/10 bg-white/[0.03] p-4">
                <div className="font-medium mb-1">What this is</div>
                <p className="text-zinc-400">A playful, browser-only demo inspired by <span className="text-zinc-200">spank</span>. It listens to your microphone for sharp taps on your laptop/desktop and responds with cheeky, synthesized sounds and animations. No recordings leave your device.</p>
              </div>
              <div className="rounded-2xl border border-white/10 bg-white/[0.03] p-4">
                <div className="font-medium mb-1">How detection works</div>
                <p className="text-zinc-400">We watch for a sudden jump in loudness (attack transient) above a threshold you control. Sensitivity adjusts the dB threshold. There’s a short cooldown so it doesn’t spam.</p>
              </div>
              <div className="rounded-2xl border border-white/10 bg-white/[0.03] p-4">
                <div className="font-medium mb-1">Tips</div>
                <p className="text-zinc-400">Tap the palm rest or desk near the mic for best results. Use “Calibrate” to auto-set sensitivity to your ambient noise. Spacebar triggers a test reaction.</p>
              </div>
            </div>
          </div>
        )}
      </header>

      {/* Main */}
      <main className="mx-auto max-w-6xl px-4 sm:px-6 lg:px-8 py-8 lg:py-10">
        <div className="grid lg:grid-cols-5 gap-6 lg:gap-8 items-start">
          {/* Laptop card */}
          <section className="lg:col-span-3 relative">
            <div className={`relative rounded-[28px] border border-white/10 bg-[radial-gradient(1200px_600px_at_20%_-10%,rgba(217,70,239,0.18),transparent),radial-gradient(900px_500px_at_120%_110%,rgba(79,70,229,0.18),transparent)] p-6 sm:p-8 shadow-[inset_0_1px_0_0_rgba(255,255,255,0.06),0_20px_60px_-20px_rgba(0,0,0,0.7)] overflow-hidden`}>
              {/* glow */}
              <div className="pointer-events-none absolute -top-24 -right-24 h-72 w-72 rounded-full bg-fuchsia-500/20 blur-[90px]" />
              <div className="pointer-events-none absolute -bottom-24 -left-24 h-72 w-72 rounded-full bg-indigo-500/20 blur-[90px]" />

              {/* header row */}
              <div className="flex items-center justify-between gap-4">
                <div className="flex items-center gap-3">
                  <div className={`h-9 px-3 rounded-full bg-gradient-to-br ${moodMeta.accent} text-white grid place-items-center text-sm font-medium shadow-[0_6px_20px_-6px_rgba(0,0,0,0.6)] ring-1 ${moodMeta.ring} ring-inset`}>
                    <span className="mr-1">{moodMeta.emoji}</span> {mood.charAt(0).toUpperCase() + mood.slice(1)}
                  </div>
                  <div className="hidden sm:flex items-center gap-2 text-xs text-zinc-400">
                    <span className="px-2 py-1 rounded-full bg-white/5 border border-white/10">{moodMeta.tag}</span>
                    <span className="px-2 py-1 rounded-full bg-white/5 border border-white/10">SFW synth</span>
                  </div>
                </div>

                <div className="flex items-center gap-2">
                  <div className={`h-8 px-3 rounded-full border border-white/10 bg-white/[0.04] text-xs flex items-center gap-2 ${isHot ? "ring-2 ring-fuchsia-400/50" : ""}`}>
                    <span className={`h-2 w-2 rounded-full ${listening ? "bg-emerald-400 shadow-[0_0_10px_rgba(16,185,129,0.9)]" : "bg-zinc-500"}`} />
                    <span className="tracking-wide">{listening ? "Listening" : "Idle"}</span>
                  </div>
                </div>
              </div>

              {/* Laptop illustration */}
              <div className="mt-8 grid place-items-center">
                <div className={`relative transition-transform duration-300 ${jiggle ? "[animation:jiggle_0.42s_ease]" : ""}`}>
                  {/* screen glow */}
                  <div className={`absolute -inset-8 rounded-[40px] blur-2xl opacity-40 transition-opacity ${blush ? "opacity-80" : "opacity-40"} bg-gradient-to-br ${moodMeta.accent}`} />
                  {/* laptop */}
                  <div className="relative">
                    {/* lid */}
                    <div className="mx-auto w-[min(560px,86vw)] rounded-[28px] border border-white/10 bg-zinc-900 shadow-[0_25px_80px_-20px_rgba(0,0,0,0.8)] overflow-hidden">
                      <div className="relative h-[210px] sm:h-[240px] bg-[#0a0a0f] border-b border-white/5">
                        {/* screen */}
                        <div className="absolute inset-[14px] rounded-[18px] bg-[radial-gradient(600px_200px_at_50%_0%,rgba(255,255,255,0.07),transparent)] border border-white/10 overflow-hidden">
                          {/* cute face */}
                          <div className="absolute inset-0 grid place-items-center">
                            <div className="relative">
                              <div className={`absolute -inset-8 rounded-full blur-2xl ${moodMeta.blush} ${blush ? "animate-[blushPulse_0.65s_ease] opacity-100" : "opacity-0"}`} />
                              <div className="flex items-center gap-6 sm:gap-10">
                                {/* eyes */}
                                <div className="flex items-center gap-6 sm:gap-10">
                                  <div className={`h-3 w-10 sm:h-4 sm:w-12 rounded-full bg-white/90 shadow-[0_0_20px_rgba(255,255,255,0.25)] transition-transform ${isHot ? "scale-y-75" : ""}`} />
                                  <div className={`h-3 w-10 sm:h-4 sm:w-12 rounded-full bg-white/90 shadow-[0_0_20px_rgba(255,255,255,0.25)] transition-transform ${isHot ? "scale-y-75" : ""}`} />
                                </div>
                              </div>
                              {/* mouth */}
                              <div className="mt-6 sm:mt-7 flex justify-center">
                                <div className={`h-[10px] w-14 sm:h-[12px] sm:w-16 rounded-full bg-white/90 transition-all ${isHot ? "w-20 rounded-[20px]" : ""}`} />
                              </div>
                              {/* cheeks */}
                              <div className="absolute -left-10 top-1/2 -translate-y-1/2 h-10 w-10 rounded-full bg-rose-400/25 blur-[6px]" />
                              <div className="absolute -right-10 top-1/2 -translate-y-1/2 h-10 w-10 rounded-full bg-rose-400/25 blur-[6px]" />
                            </div>
                          </div>

                          {/* meter */}
                          <div className="absolute bottom-0 inset-x-0 h-[34px] border-t border-white/10 bg-black/40 backdrop-blur-md">
                            <div className="h-full w-full relative overflow-hidden">
                              <div
                                className="absolute inset-y-1 left-1 rounded-full bg-white/10"
                                style={{ width: `calc(${clamp((levelDb + 90) / 90, 0, 1) * 100}% - 8px)` }}
                              />
                              <div className="absolute inset-0 opacity-[0.15] mix-blend-soft-light">
                                <div className="absolute inset-0 bg-[linear-gradient(90deg,transparent,rgba(255,255,255,0.35),transparent)] w-[40%] [animation:meter_2.8s_linear_infinite]" />
                              </div>
                              <div className="absolute inset-0 flex items-center justify-between px-3 text-[10px] text-zinc-300/90 tracking-wide">
                                <span>IN</span>
                                <span className="tabular-nums">
                                  {levelDb.toFixed(0)} dB • pk {peakDb.toFixed(0)} dB • thr {thresholdDb.toFixed(0)} dB
                                </span>
                              </div>
                            </div>
                          </div>
                        </div>
                        {/* webcam dot */}
                        <div className="absolute top-2 left-1/2 -translate-x-1/2 h-1.5 w-1.5 rounded-full bg-zinc-700 ring-1 ring-white/10" />
                      </div>
                      {/* hinge */}
                      <div className="h-3 bg-zinc-950 border-t border-white/5" />
                      {/* base */}
                      <div className="relative bg-zinc-900 border-t border-white/10">
                        <div className="mx-auto max-w-[92%] px-4 sm:px-8 py-4 sm:py-5">
                          {/* keyboard */}
                          <div className="grid grid-cols-12 gap-[6px] sm:gap-2 opacity-90">
                            {Array.from({ length: 12 * 4 }).map((_, i) => (
                              <div key={i} className="h-[12px] sm:h-[14px] rounded-[4px] bg-zinc-800 border border-white/5 shadow-[inset_0_1px_0_0_rgba(255,255,255,0.04)]" />
                            ))}
                            <div className="col-span-12 grid grid-cols-12 gap-[6px] sm:gap-2 mt-0.5">
                              <div className="col-span-2 h-[12px] sm:h-[14px] rounded-[4px] bg-zinc-800 border border-white/5" />
                              <div className="col-span-8 h-[12px] sm:h-[14px] rounded-[4px] bg-zinc-800 border border-white/5" />
                              <div className="col-span-2 h-[12px] sm:h-[14px] rounded-[4px] bg-zinc-800 border border-white/5" />
                            </div>
                          </div>
                          {/* trackpad */}
                          <div className="mt-4 sm:mt-5 mx-auto h-[54px] sm:h-[64px] w-[64%] rounded-[18px] bg-zinc-800 border border-white/10 shadow-[inset_0_1px_0_0_rgba(255,255,255,0.05)] relative overflow-hidden">
                            <div className={`absolute inset-0 rounded-[18px] ${blush ? "opacity-100" : "opacity-0"} transition-opacity duration-300 bg-[radial-gradient(200px_80px_at_50%_10%,rgba(255,255,255,0.12),transparent)]`} />
                          </div>
                        </div>
                        <div className="h-3 rounded-b-[26px] bg-zinc-950" />
                      </div>
                    </div>
                    {/* shadow */}
                    <div className="mx-auto mt-5 h-6 w-[70%] rounded-full bg-black/60 blur-[18px]" />
                  </div>
                </div>
              </div>

              {/* Actions */}
              <div className="mt-8 flex flex-wrap items-center justify-between gap-3">
                <div className="flex items-center gap-2">
                  {!listening ? (
                    <button
                      onClick={startListening}
                      className="relative inline-flex items-center gap-2 rounded-2xl px-4 py-2.5 text-sm font-medium text-white bg-zinc-900 border border-white/10 shadow-[inset_0_1px_0_0_rgba(255,255,255,0.06),0_10px_30px_-10px_rgba(0,0,0,0.6)] hover:bg-zinc-800 transition"
                    >
                      <span className="h-2 w-2 rounded-full bg-emerald-400 shadow-[0_0_10px_rgba(16,185,129,0.9)]" />
                      Start listening
                    </button>
                  ) : (
                    <button
                      onClick={stopListening}
                      className="inline-flex items-center gap-2 rounded-2xl px-4 py-2.5 text-sm font-medium text-white bg-zinc-900 border border-white/10 hover:bg-zinc-800 transition"
                    >
                      <span className="h-2 w-2 rounded-full bg-rose-400 shadow-[0_0_10px_rgba(244,63,94,0.9)]" />
                      Stop
                    </button>
                  )}

                  <button
                    onClick={() => trigger(0.9)}
                    className="inline-flex items-center gap-2 rounded-2xl px-4 py-2.5 text-sm font-medium bg-white text-zinc-900 hover:bg-zinc-200 transition shadow-[0_10px_30px_-12px_rgba(255,255,255,0.25)]"
                  >
                    Test tap
                    <kbd className="ml-1 rounded bg-zinc-900 text-white px-1.5 py-0.5 text-[10px] border border-zinc-700">SPACE</kbd>
                  </button>

                  <button
                    onClick={calibrate}
                    disabled={!listening || calibrating}
                    className="inline-flex items-center gap-2 rounded-2xl px-3.5 py-2.5 text-sm font-medium bg-white/5 border border-white/10 hover:bg-white/10 transition disabled:opacity-50"
                  >
                    {calibrating ? (
                      <>
                        <svg className="animate-spin" width="16" height="16" viewBox="0 0 24 24" fill="none">
                          <circle cx="12" cy="12" r="10" stroke="currentColor" strokeOpacity="0.2" strokeWidth="4" />
                          <path d="M22 12a10 10 0 0 1-10 10" stroke="currentColor" strokeWidth="4" />
                        </svg>
                        Calibrating…
                      </>
                    ) : (
                      <>Calibrate</>
                    )}
                  </button>
                </div>

                <div className="flex items-center gap-3 text-xs text-zinc-400">
                  <label className="inline-flex items-center gap-2 cursor-pointer">
                    <input type="checkbox" className="sr-only" checked={shakeScreen} onChange={(e) => setShakeScreen(e.target.checked)} />
                    <span className={`h-5 w-9 rounded-full p-0.5 transition ${shakeScreen ? "bg-fuchsia-600" : "bg-zinc-700"} flex`}>
                      <span className={`h-4 w-4 rounded-full bg-white shadow transition ${shakeScreen ? "translate-x-4" : ""}`} />
                    </span>
                    Screen shake
                  </label>
                  <label className="inline-flex items-center gap-2 cursor-pointer">
                    <input type="checkbox" className="sr-only" checked={visualOnly} onChange={(e) => setVisualOnly(e.target.checked)} />
                    <span className={`h-5 w-9 rounded-full p-0.5 transition ${visualOnly ? "bg-zinc-600" : "bg-fuchsia-600"} flex`}>
                      <span className={`h-4 w-4 rounded-full bg-white shadow transition ${visualOnly ? "translate-x-4" : ""}`} />
                    </span>
                    Visual only
                  </label>
                </div>
              </div>

              {/* Permission / status */}
              <div className="mt-4">
                {permission === "denied" && (
                  <div className="rounded-2xl border border-rose-500/30 bg-rose-500/10 text-rose-100 px-4 py-3 text-sm">
                    Microphone permission was denied. Please allow mic access in your browser to enable tap detection.
                  </div>
                )}
                {permission === "unsupported" && (
                  <div className="rounded-2xl border border-amber-500/30 bg-amber-500/10 text-amber-100 px-4 py-3 text-sm">
                    Your browser doesn’t support microphone capture or Web Audio. Try Chrome, Edge, or Safari.
                  </div>
                )}
                {permission === "idle" && (
                  <div className="rounded-2xl border border-white/10 bg-white/[0.03] px-4 py-3 text-sm text-zinc-300">
                    Tip: Tap the palm rest or lightly slap the desk near the laptop mic. Keep taps crisp and short for best detection.
                  </div>
                )}
              </div>
            </div>

            {/* onboarding */}
            {showOnboard && (
              <div className="absolute inset-0 z-30 grid place-items-center p-4 bg-[#0b0b10]/80 backdrop-blur-xl rounded-[28px] border border-white/10">
                <div className="w-full max-w-lg rounded-[24px] border border-white/10 bg-zinc-950 p-6 shadow-2xl">
                  <div className="flex items-start gap-4">
                    <div className="h-12 w-12 rounded-2xl bg-gradient-to-br from-fuchsia-500 to-violet-600 grid place-items-center text-2xl shadow-lg">🫦</div>
                    <div className="flex-1">
                      <h2 className="text-lg font-semibold tracking-tight">Welcome to Spank • Web</h2>
                      <p className="mt-1 text-sm text-zinc-400">
                        A cheeky, SFW browser toy inspired by the original <span className="text-zinc-200">spank</span> project. It listens for sharp taps and reacts with playful, synthesized sounds and animations. Nothing is recorded or uploaded.
                      </p>
                      <ul className="mt-4 grid gap-2 text-sm text-zinc-300">
                        <li className="flex gap-2"><span className="mt-1 h-1.5 w-1.5 rounded-full bg-fuchsia-400" />Tap your laptop’s palm rest or the desk—short, crisp sounds work best.</li>
                        <li className="flex gap-2"><span className="mt-1 h-1.5 w-1.5 rounded-full bg-fuchsia-400" />Use Calibrate to auto-tune sensitivity to your environment.</li>
                        <li className="flex gap-2"><span className="mt-1 h-1.5 w-1.5 rounded-full bg-fuchsia-400" />Spacebar triggers a test reaction. Adjust mood and enthusiasm for different vibes.</li>
                      </ul>
                      <div className="mt-6 flex items-center justify-between">
                        <button
                          onClick={() => setShowOnboard(false)}
                          className="text-sm text-zinc-400 hover:text-zinc-200 underline decoration-zinc-600 underline-offset-4"
                        >
                          Skip
                        </button>
                        <button
                          onClick={async () => {
                            setShowOnboard(false);
                            await startListening();
                          }}
                          className="inline-flex items-center gap-2 rounded-2xl px-4 py-2.5 text-sm font-medium bg-white text-zinc-900 hover:bg-zinc-200 transition shadow-[0_10px_30px_-12px_rgba(255,255,255,0.25)]"
                        >
                          Allow mic & start
                          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2"><path d="M5 12h14" /><path d="m12 5 7 7-7 7" /></svg>
                        </button>
                      </div>
                      <p className="mt-3 text-[11px] text-zinc-500">
                        By continuing, you agree to use this toy respectfully and in private spaces. It’s meant for fun and does not store audio.
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            )}
          </section>

          {/* Controls */}
          <aside className="lg:col-span-2 space-y-6">
            {/* Stats */}
            <div className="rounded-[28px] border border-white/10 bg-zinc-950/70 p-5 sm:p-6 shadow-[inset_0_1px_0_0_rgba(255,255,255,0.04)]">
              <div className="flex items-center justify-between">
                <h3 className="text-sm font-semibold tracking-wide text-zinc-200 uppercase">Session</h3>
                <span className={`text-[11px] px-2 py-1 rounded-full border ${listening ? "border-emerald-500/30 text-emerald-200 bg-emerald-500/10" : "border-white/10 text-zinc-400 bg-white/5"}`}>
                  {listening ? "Active" : "Idle"}
                </span>
              </div>
              <div className="mt-4 grid grid-cols-2 gap-3">
                <div className="rounded-2xl bg-white/[0.03] border border-white/10 p-4">
                  <div className="text-[11px] uppercase tracking-wide text-zinc-400">Spanks</div>
                  <div className="mt-1 text-3xl font-semibold tracking-tight tabular-nums">{stats.slaps}</div>
                  <div className="mt-1 text-[11px] text-zinc-500">Total taps detected</div>
                </div>
                <div className="rounded-2xl bg-white/[0.03] border border-white/10 p-4">
                  <div className="text-[11px] uppercase tracking-wide text-zinc-400">Time</div>
                  <div className="mt-1 text-3xl font-semibold tracking-tight tabular-nums">{formatDuration(sessionMs)}</div>
                  <div className="mt-1 text-[11px] text-zinc-500">Since listening started</div>
                </div>
                <div className="rounded-2xl bg-white/[0.03] border border-white/10 p-4">
                  <div className="text-[11px] uppercase tracking-wide text-zinc-400">Combo</div>
                  <div className="mt-1 text-3xl font-semibold tracking-tight tabular-nums">{stats.combo}</div>
                  <div className="mt-1 text-[11px] text-zinc-500">Resets after 2s</div>
                </div>
                <div className="rounded-2xl bg-white/[0.03] border border-white/10 p-4">
                  <div className="text-[11px] uppercase tracking-wide text-zinc-400">Best combo</div>
                  <div className="mt-1 text-3xl font-semibold tracking-tight tabular-nums">{stats.maxCombo}</div>
                  <div className="mt-1 text-[11px] text-zinc-500">Personal best</div>
                </div>
              </div>
            </div>

              {/* Controls */}
            <div className="rounded-[28px] border border-white/10 bg-zinc-950/70 p-5 sm:p-6">
              <h3 className="text-sm font-semibold tracking-wide text-zinc-200 uppercase">Feel</h3>

              {/* Mood */}
              <div className="mt-4">
                <div className="text-xs text-zinc-400 mb-2">Mood</div>
                <div className="grid grid-cols-2 sm:grid-cols-4 gap-2">
                  {(Object.keys(MOOD_META) as Mood[]).map((m) => (
                    <button
                      key={m}
                      onClick={() => setMood(m)}
                      className={`group relative rounded-2xl border px-3 py-2.5 text-sm text-left transition ${
                        mood === m ? "bg-white text-zinc-900 border-white" : "bg-white/[0.04] border-white/10 hover:bg-white/[0.08] text-zinc-200"
                      }`}
                    >
                      <div className="flex items-center gap-2">
                        <span className="text-lg">{MOOD_META[m].emoji}</span>
                        <span className="font-medium capitalize">{m}</span>
                      </div>
                      <div className={`mt-1 text-[11px] ${mood === m ? "text-zinc-600" : "text-zinc-400"}`}>{MOOD_META[m].tag}</div>
                      <div className={`pointer-events-none absolute inset-0 rounded-2xl ring-1 ${mood === m ? "ring-black/10" : "ring-white/10"} ring-inset`} />
                    </button>
                  ))}
                </div>
              </div>

              {/* Samples */}
              <div className="mt-6 rounded-2xl border border-white/10 bg-white/[0.03] p-4">
                <div className="flex items-center justify-between gap-3">
                  <div>
                    <div className="text-xs text-zinc-300 font-medium">Custom audio</div>
                    <div className="text-[11px] text-zinc-500">Upload short SFW sounds to use instead of the synth. One per mood.</div>
                  </div>
                  <label className="inline-flex items-center gap-2 cursor-pointer">
                    <input type="checkbox" className="sr-only" checked={useSamples} onChange={(e) => setUseSamples(e.target.checked)} />
                    <span className={`h-5 w-9 rounded-full p-0.5 transition ${useSamples ? "bg-fuchsia-600" : "bg-zinc-700"} flex`}>
                      <span className={`h-4 w-4 rounded-full bg-white shadow transition ${useSamples ? "translate-x-4" : ""}`} />
                    </span>
                    <span className="text-xs text-zinc-400">Use samples</span>
                  </label>
                </div>

                <div className="mt-3 grid grid-cols-1 sm:grid-cols-2 gap-2">
                  {(Object.keys(MOOD_META) as Mood[]).map((m) => (
                    <div key={m} className="rounded-xl border border-white/10 bg-black/30 p-3 flex items-center justify-between gap-3">
                      <div className="flex items-center gap-2">
                        <span className="text-base">{MOOD_META[m].emoji}</span>
                        <span className="text-sm capitalize">{m}</span>
                        {sampleBuffers[m] && <span className="text-[10px] px-1.5 py-0.5 rounded-full bg-emerald-500/15 text-emerald-200 border border-emerald-500/20">loaded</span>}
                      </div>
                      <div className="flex items-center gap-2">
                        <button
                          onClick={() => fileInputRef.current?.click()}
                          data-mood={m}
                          className="text-xs rounded-lg px-2.5 py-1.5 bg-white/10 hover:bg-white/15 border border-white/10"
                        >
                          Upload
                        </button>
                        <button
                          onClick={async () => {
                            // quick preview
                            const ctx = audioCtxRef.current;
                            if (!ctx) { await startListening(); }
                            playSample(m, 1);
                          }}
                          disabled={!sampleBuffers[m]}
                          className="text-xs rounded-lg px-2.5 py-1.5 bg-white text-zinc-900 hover:bg-zinc-200 disabled:opacity-40"
                        >
                          Play
                        </button>
                        <button
                          onClick={() => setSampleBuffers((s) => { const c = {...s}; delete c[m]; return c; })}
                          disabled={!sampleBuffers[m]}
                          className="text-[11px] rounded-lg px-2 py-1 bg-white/5 hover:bg-white/10 border border-white/10 disabled:opacity-40"
                        >
                          Clear
                        </button>
                      </div>
                    </div>
                  ))}
                </div>

                <input
                  ref={fileInputRef}
                  type="file"
                  accept="audio/*"
                  className="hidden"
                  onChange={async (e) => {
                    const input = e.currentTarget;
                    const file = input.files?.[0];
                    if (!file) return;
                    // pick mood from the button that opened it
                    const moodBtn = document.activeElement as HTMLElement | null;
                    const pickedMood = (moodBtn?.getAttribute("data-mood") as Mood) || mood;
                    let ctx = audioCtxRef.current;
                    if (!ctx) { await startListening(); ctx = audioCtxRef.current; }
                    if (!ctx) return;
                    const arr = await file.arrayBuffer();
                    try {
                      const buf = await ctx.decodeAudioData(arr.slice(0));
                      setSampleBuffers((s) => ({ ...s, [pickedMood]: buf }));
                      setUseSamples(true);
                    } catch {}
                    input.value = "";
                  }}
                />

                <div className="mt-3 text-[11px] text-zinc-500">
                  Tips: keep files under ~2 seconds, WAV/MP3/AAC work. Samples play with slight random pitch for variety. Toggle off to revert to synth.
                </div>
              </div>

              {/* Sliders */}
              <div className="mt-6 space-y-5">
                <div>
                  <div className="flex items-center justify-between mb-2">
                    <label className="text-xs text-zinc-400">Sensitivity</label>
                    <span className="text-[11px] text-zinc-500 tabular-nums">{Math.round(sensitivity * 100)}%</span>
                  </div>
                  <input
                    type="range"
                    min={0}
                    max={1}
                    step={0.01}
                    value={sensitivity}
                    onChange={(e) => setSensitivity(parseFloat(e.target.value))}
                    className="w-full accent-fuchsia-500"
                  />
                  <div className="mt-1 flex justify-between text-[10px] text-zinc-500">
                    <span>Chill</span>
                    <span className="tabular-nums">thr {thresholdDb.toFixed(0)} dB</span>
                    <span>Hair-trigger</span>
                  </div>
                </div>

                <div>
                  <div className="flex items-center justify-between mb-2">
                    <label className="text-xs text-zinc-400">Enthusiasm</label>
                    <span className="text-[11px] text-zinc-500 tabular-nums">{Math.round(enthusiasm * 100)}%</span>
                  </div>
                  <input
                    type="range"
                    min={0}
                    max={1}
                    step={0.01}
                    value={enthusiasm}
                    onChange={(e) => setEnthusiasm(parseFloat(e.target.value))}
                    className="w-full accent-indigo-500"
                  />
                  <div className="mt-1 flex justify-between text-[10px] text-zinc-500">
                    <span>Teasing</span>
                    <span>Eager</span>
                  </div>
                </div>
              </div>

              {/* Meter bar */}
              <div className="mt-6 rounded-2xl border border-white/10 bg-black/40 p-3">
                <div className="text-[11px] text-zinc-400 mb-2">Live input</div>
                <div className="h-3 w-full rounded-full bg-white/5 overflow-hidden relative">
                  <div
                    className="absolute inset-y-0 left-0 bg-gradient-to-r from-zinc-500 to-fuchsia-400"
                    style={{ width: `${clamp((levelDb + 90) / 90, 0, 1) * 100}%` }}
                  />
                  <div
                    className="absolute top-0 bottom-0 w-[2px] bg-white/70"
                    style={{ left: `calc(${clamp((thresholdDb + 90) / 90, 0, 1) * 100}% - 1px)` }}
                  />
                </div>
                <div className="mt-2 flex justify-between text-[10px] text-zinc-500">
                  <span>-90 dB</span>
                  <span>threshold</span>
                  <span>0 dB</span>
                </div>
              </div>
            </div>

            {/* Notes */}
            <div className="rounded-[28px] border border-white/10 bg-zinc-950/50 p-5 sm:p-6">
              <h3 className="text-sm font-semibold tracking-wide text-zinc-200 uppercase">Notes</h3>
              <ul className="mt-3 space-y-2 text-sm text-zinc-400">
                <li className="flex gap-2"><span className="mt-2 h-1 w-1 rounded-full bg-zinc-500" />This is a browser-only demo. It does not record or upload audio.</li>
                <li className="flex gap-2"><span className="mt-2 h-1 w-1 rounded-full bg-zinc-500" />All sounds are synthesized in real time using the Web Audio API.</li>
                <li className="flex gap-2"><span className="mt-2 h-1 w-1 rounded-full bg-zinc-500" />Inspired by <a className="underline decoration-zinc-600 hover:decoration-zinc-300" href="https://github.com/taigrr/spank" target="_blank" rel="noreferrer">taigrr/spank</a>.</li>
              </ul>
            </div>
          </aside>
        </div>

        {/* Footer */}
        <div className="mt-10 pt-6 border-t border-white/5 text-[11px] text-zinc-500 flex flex-wrap items-center justify-between gap-3">
          <div className="flex items-center gap-2">
            <span className="opacity-70">Made for playful experimentation.</span>
            <span className="hidden sm:inline">Press Space to test.</span>
          </div>
          <div className="flex items-center gap-3">
            <span className="px-2 py-1 rounded-full bg-white/5 border border-white/10">No telemetry</span>
            <span className="px-2 py-1 rounded-full bg-white/5 border border-white/10">Offline capable</span>
            <span className="px-2 py-1 rounded-full bg-white/5 border border-white/10">SFW synth</span>
          </div>
        </div>
      </main>
    </div>
  );
}
