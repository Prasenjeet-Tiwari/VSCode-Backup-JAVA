# Spank-Web ✨ — Tap / Slap Reactive Web App
A playful, self-contained web application inspired by the open source project [taigrr/spank](https://github.com/taigrr/spank). Instead of a native desktop app, this version runs entirely in your browser (React + Vite + Tailwind) and reacts to sharp physical taps or light slaps near your computer’s microphone with a cute visual response and—optionally—a synthesized or sample-based “moan” sound.
> **Important:** This demo is a lighthearted, tongue-in-cheek toy. All sounds are **synthetic and safe-for-work**, or your own short audio samples that you choose to load locally. No audio is ever recorded, uploaded, or sent anywhere. Keep it respectful and use it only in private, consensual contexts.
---
## Table of Contents
- [What it does](#what-it-does)
- [Demo highlights](#demo-highlights)
- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Quick start (local)](#quick-start-local)
- [Development scripts](#development-scripts)
- [How it works (under the hood)](#how-it-works-under-the-hood)
  - [Microphone detection pipeline](#microphone-detection-pipeline)
  - [Threshold & sensitivity model](#threshold--sensitivity-model)
  - [Synth voice](#synth-voice)
  - [Custom samples](#custom-samples)
- [Using the app](#using-the-app)
- [Calibrating & tuning sensitivity](#calibrating--tuning-sensitivity)
- [Replacing or adding your own sounds](#replacing-or-adding-your-own-sounds)
- [Building for production](#building-for-production)
- [Deploying](#deploying)
- [Customization ideas](#customization-ideas)
- [Troubleshooting](#troubleshooting)
- [Privacy & security notes](#privacy--security-notes)
- [License and credits](#license-and-credits)
---
## What it does
- Listens to your microphone in the browser via the Web Audio API.
- Detects **short, sharp transients** (taps, slaps) by looking at the real-time signal’s RMS level and its **attack** (positive derivative) and **sharpness**.
- When a transient crosses a user-tunable threshold, the app:
  1. Plays a short, percussive vocal-like sound (procedural synth by default, or an optional user-uploaded sample).
  2. Triggers a small animation: the cartoon laptop “face” blushes, jiggles, and opens its mouth.
  3. Updates session stats: total tap count, current combo, best combo, session time.
- All processing is **local, in-memory**. Nothing is persisted except optional uploaded samples held temporarily in the AudioContext buffer (they vanish when you reload).
---
## Demo highlights
- **Zero-backend**: 100% client-side. No servers, no recordings.
- **Fine-grained controls**:
  - Sensitivity slider (0–100) maps to a dB threshold.
  - Enthusiasm slider controls output volume / intensity.
  - Four moods: `shy`, `brat`, `sweet`, `dramatic` (change filter formants and animation styling).
  - Optional screen shake and “visual only” mode.
- **Calibration**: measures ambient noise for ~1.5 s and places threshold ~22 dB above the baseline.
- **Custom audio panel**: upload per-mood short samples (WAV/MP3/AAC), preview them, and toggle sample vs synth playback.
- **Single-file build**: the production build bundles everything into one self-contained `dist/index.html` (~272 KB, ~78 KB gzipped).
---
## Tech stack
- **React 18** (function components, hooks)
- **TypeScript** (strict settings)
- **Vite 7** (dev server, build, `vite-plugin-singlefile`)
- **Tailwind CSS 4** via `@tailwindcss/vite` (utility-first styling)
- **Web Audio API** (`AudioContext`, `AnalyserNode`, `DynamicsCompressorNode`, `BiquadFilterNode`)
- **No external audio assets** by default—sound is synthesized procedurally
Key dev dependencies (see `package.json`):
```json
{
  "dependencies": {
    "react": "^18",
    "react-dom": "^18",
    "clsx": "^2",
    "tailwind-merge": "^2"
  },
  "devDependencies": {
    "@types/react": "^18",
    "@types/react-dom": "^18",
    "@vitejs/plugin-react": "^4",
    "typescript": "^5",
    "vite": "^7",
    "vite-plugin-singlefile": "^0.13",
    "@tailwindcss/vite": "^4"
  }
}
```
---
## Project structure
```
.
├─ index.html                # Vite entry; title and root div
├─ package.json              # scripts and deps
├─ tsconfig.json             # TypeScript config
├─ vite.config.ts            # Vite config with React and single-file plugin
├─ src/
│  ├─ main.tsx               # React bootstrap
│  ├─ index.css              # Tailwind base styles
│  ├─ App.tsx                # All app logic & UI
│  └─ utils/
│     └─ cn.ts               # tiny className helper (clsx + tailwind-merge)
└─ dist/                     # production build (after `npm run build`)
   └─ index.html             # self-contained bundle
```
---
## Prerequisites
- **Node.js ≥ 18** (which includes npm). Check with:
  ```bash
  node -v
  npm -v
  ```
  If you don’t have Node, install the LTS release from https://nodejs.org/.
- A modern desktop browser with microphone access: Chrome, Edge, Firefox, or Safari. Microphone permission is available on `http://localhost` and on any `https://` origin. Plain `file://` pages are typically blocked from mic access.
---
## Quick start (local)
1. **Clone or copy** this project to a folder on your machine. If you’re starting from scratch, you can scaffold Vite and then replace the source files with the ones from this repo:
   ```bash
   npm create vite@latest spank-web -- --template react-ts
   cd spank-web
   # Replace src/App.tsx, src/index.css, src/main.tsx, etc. with the project files
   ```
2. **Install dependencies**:
   ```bash
   npm install
   ```
3. **Start the development server**:
   ```bash
   npm run dev
   ```
   You’ll see something like:
   ```
   VITE v7.x  ready in 2xx ms
   ➜  Local:   http://localhost:5173/
   ```
   Open that URL in your browser, click **“Start listening”** (or “Allow mic & start”), grant microphone permission, and try a crisp tap on your laptop’s palm rest or desk near the mic. The laptop face should react.
4. **Stop** the dev server with `Ctrl+C` in the terminal.
---
## Development scripts
Defined in `package.json`:
```json
{
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  }
}
```
- `npm run dev` – start Vite with hot module replacement.
- `npm run build` – create an optimized production build (`dist/`).
- `npm run preview` – serve the built `dist/` locally to test the production bundle.
---
## How it works (under the hood)
### Microphone detection pipeline
1. `getUserMedia({ audio: true })` requests a mic stream. A permission prompt appears.
2. An `AudioContext` is created (or resumed on user gesture) with a modest latency hint.
3. A `MediaStreamSource` → `AnalyserNode` chain is built:
   - FFT size 2048, giving time-domain samples at ~48 kHz.
   - Each animation frame (via `requestAnimationFrame`) reads a Float32Array of the waveform.
4. For every frame the app computes:
   - **RMS** (root-mean-square) level: a smooth measure of loudness.
   - **dB** level: `20 * log10(rms)`, clamped to –120 dB minimum.
   - **Attack**: positive derivative of dB (`max(0, dB_now - dB_prev)`).
   - **Sharpness**: proportion of samples exceeding 0.2 × peak absolute sample (a quick proxy for impulsive content).
5. A detection is triggered when:
   - The measured dB exceeds the user threshold **plus** a small dynamic margin.
   - Attack is > (0.5 × threshold window) and sharpness > 0.03.
   - The current time is at least `cooldown` (default 520 ms) after the last trigger.
The detection is tuned to prefer **short, snappy taps** and reject slow presses or continuous speech.
### Threshold & sensitivity model
- The UI exposes **Sensitivity** (0–1). Internally it maps to a threshold in decibels:
  ```ts
  const minDb = -52; // more sensitive end (lower is “harder”)
  const maxDb = -22; // less sensitive end
  thresholdDb = minDb + (maxDb - minDb) * (1 - sensitivity)
  ```
  - Sensitivity = 1 → threshold ≈ –52 dB (very sensitive).
  - Sensitivity = 0 → threshold ≈ –22 dB (needs a much louder tap).
- The default sensitivity is **0.22**, equivalent to about –45 dB, intentionally conservative to avoid false positives.
- **Calibration** runs a ~1.5 s ambient sample, computes mean dB (`ambientDb`), then sets:
  ```
  targetDb = ambientDb + 22 dB
  sensitivity = clamp(1 - (targetDb - minDb)/(maxDb - minDb), 0, 1)
  ```
  This pushes the threshold ~22 dB above room tone, a bit stricter than early prototypes.
### Synth voice
When samples are **off**, a procedural voice is generated:
- **Breath**: 0.22 s pink-noise buffer (looped) for airy texture.
- **Formants**: two band-pass `BiquadFilterNode`s whose center frequencies and Q vary by mood:
  - `shy`      → ~780 Hz (Q 8) & 1960 Hz (Q 10)
  - `brat`     → ~880 Hz (Q 9) & 2350 Hz (Q 12)
  - `sweet`    → ~820 Hz (Q 8) & 2150 Hz (Q 10)
  - `dramatic` → ~920 Hz (Q 10) & 2550 Hz (Q 14)
- **Voice**: triangle wave oscillator with a slight wobble (`±0.8 Hz` at ~5 Hz), base frequency chosen per mood (~205–230 Hz) and multiplied by an intensity factor that follows the measured “oomph” of the tap.
- **Envelopes**:
  - Noise gain: quick attack (0.02 s), exponential decay to 0.001 by 0.32 s.
  - Voice gain: small start (0.001), ramp to an intensity-scaled peak in 0.04 s, then exponential decay by 0.48 s.
- **Post**: subtle chorus via a second detuned triangle, mild `DynamicsCompressorNode` (threshold –24 dB, ratio 3:1) to keep things punchy but not harsh, then master gain controlled by the **Enthusiasm** slider.
All of this runs locally in the browser; no samples are downloaded.
### Custom samples
When you enable **Use samples** and upload a file for a mood, the app:
- Decodes the audio with `AudioContext.decodeAudioData`.
- Stores the resulting `AudioBuffer` in memory for that mood.
- On trigger, selects the uploaded buffer and plays it via an `AudioBufferSourceNode` with:
  - A gentle attack (0.008 s) and release (≈duration + 0.02 s).
  - Random pitch variation (`playbackRate` 0.98–1.04) for a less repetitive feel.
  - Output level scaled by **Enthusiasm**.
- If a particular mood has no buffer, the app gracefully falls back to the synth for that mood.
---
## Using the app
1. **Start**: click “Allow mic & start” (on first launch) or “Start listening.” Grant mic permission.
2. **Tap**: produce a short, crisp tap on the palm rest or desk near the mic. Harder, sharper taps produce stronger reactions.
3. **Test** without tapping: press the **Spacebar** or the **Test (Space)** button.
4. **Tweak**:
   - **Sensitivity** (lower = requires louder taps).
   - **Enthusiasm** (output loudness / intensity).
   - Pick a **Mood** (four presets).
   - Toggle **Screen shake** and **Visual only** as desired.
5. **Calibrate**: hit **Calibrate** in a quiet moment to auto-set sensitivity based on your room.
6. **Stop**: click **Stop** to close the mic stream and suspend the audio context.
**Live meters** show the current dB level, your threshold, and a brief recent history graph.
---
## Calibrating & tuning sensitivity
- Aim for a baseline where normal typing and ambient chatter don’t trigger, but intentional taps do.
- The calibration flow samples background noise for 1.5 s, then sets the threshold about **22 dB** above the measured mean.
- If you still get false triggers:
  - Reduce **Sensitivity** (move slider left) so the threshold rises (needs louder taps). Remember the mapping –22 dB (low sensitivity) to –52 dB (high sensitivity).
  - Increase physical distance between your taps and the mic, or tap more softly.
- If it feels dead:
  - Increase **Sensitivity** a bit.
  - Ensure the correct input device is chosen in your OS/browser.
  - Tap more crisply and closer to the mic.
Detection gating constants (can be edited in `src/App.tsx`) are currently:
- **Cooldown**: 520 ms between triggers.
- **Attack gate**: 0.5 × dynamic window.
- **Sharpness gate**: 0.03.
- **Level gate**: threshold + 1.25 × dynamic window.
These values were chosen to reject slow presses and speech while keeping taps responsive.
---
## Replacing or adding your own sounds
The UI includes a **Custom audio** panel per mood.
- Click **Upload** under a mood (shy / brat / sweet / dramatic) and select a short file (WAV/MP3/AAC).
- A green “loaded” badge appears when decoding succeeds. You can **Play** to audition, or **Clear** to remove.
- Toggle **Use samples** on to replace the synth with your buffers (with subtle random pitch).
- If a mood has no sample, the app automatically uses the synth for that mood.
**Tips for samples**:
- Keep clips short (0.3 – 1.5 s) to feel responsive.
- Normalize peaks around –1 dB to avoid clipping; the app applies envelopes, but starting clean helps.
- Avoid copyrighted material you don’t have rights to use.
- Clips stay **in memory only**; reloading the page clears them.
---
## Building for production
Create a minified, self-contained build:
```bash
npm run build
```
Vite produces a single `dist/index.html` that inlines CSS and JS (thanks to `vite-plugin-singlefile`). Typical output:
```
dist/index.html  ~272 KB   (gzip ~78 KB)
✓ built in ~1.5 s
```
Run a local preview:
```bash
npm run preview
```
Then open the shown URL (usually http://localhost:4173). Microphone access works here because it’s still a `localhost` secure context.
Optionally, serve `dist/` with any static server:
```bash
npx serve dist
# or
python -m http.server 8000 --directory dist
```
---
## Deploying
Because the app is a single static HTML file with no backend, you can deploy `dist/index.html` anywhere that can serve static files:
- **GitHub Pages / Netlify / Vercel / Cloudflare Pages / S3 + CloudFront**: upload the file as `index.html` at the site root.
- Make sure it’s served over **HTTPS** if you expect mic access (browsers block `getUserMedia` on insecure origins except for `localhost`).
- If you want a custom domain, configure DNS as per your host.
**PWA**: Not configured by default. You could add a minimal `manifest.webmanifest` and a service worker if you want installability and offline usage. Be aware that mic permission still requires HTTPS and user gesture.
---
## Customization ideas
- **Adjust detection**: in `src/App.tsx`, change `fftSize`, cooldown, or the gating constants.
- **New moods**: add a key to `Mood` and define new filter pairs and emoji.
- **Haptics**: on mobile, use `navigator.vibrate()` for extra feedback (behind a toggle).
- **Accessibility**: add an option to intensify visual feedback and captions for audio events.
- **Internationalization**: extract UI strings into a locale map.
- **Persistent sample library**: store uploaded samples in IndexedDB (with clear consent + a “clear all” button).
---
## Troubleshooting
**No permission prompt / mic doesn’t work**
- Confirm you’re on `http://localhost` during development or an `https://` site in production.
- Click the lock icon in the address bar → Site settings → Microphone → Allow, then reload.
- Check your OS input device: on Windows (Settings → System → Sound), macOS (System Settings → Sound → Input), or Linux (pavucontrol) make sure the correct mic is selected and that its level moves when you speak.
**It’s too sensitive**
- Slide **Sensitivity** left (lower value) to require louder taps.
- Run **Calibrate** in a quiet room.
- Increase tap distance from the mic or tap less sharply.
**It doesn’t react**
- Increase **Sensitivity** slightly.
- Make sure you click **Start listening** first (AudioContext must be resumed by a user gesture).
- Try the **Test (Space)** button; if you hear sound, audio output is working and detection is the issue.
- Tap more sharply and closer to the mic. Slow presses won’t trigger by design.
**Distortion or clipping**
- Lower **Enthusiasm**.
- Reduce system output volume or turn off additional system-level EQ/boost.
- If using your own samples, normalize them before upload.
**Browser-specific notes**
- Some browsers throttle timers in background tabs; keep the tab in focus for best responsiveness.
- iOS Safari can suspend audio contexts quickly; interacting again will resume.
---
## Privacy & security notes
- **No data collection**: the app does not record, store, or transmit audio. The mic stream is processed in real time and discarded.
- **Samples stay local**: uploaded audio is decoded into an `AudioBuffer` in memory and never uploaded.
- **Permissions**: browsers require a user gesture and explicit mic permission.
- **HTTPS**: microphone access is restricted to secure contexts (HTTPS or `localhost`). The single-file build works offline but cannot access the mic from a `file://` URL in most browsers; use a local server (`npm run preview`) instead.
---
## License and credits
- **Inspiration**: [taigrr/spank](https://github.com/taigrr/spank) by taigrr.
- **Code**: this project’s source is intended as an educational/demo toy. Add your own license if you fork it (e.g., MIT).
- **Assets**: no external audio assets are bundled; the default sound is synthesized in-browser.
---
### Appendix: Key parameters (for tinkerers)
- **Sensitivity mapping**: `thresholdDb = -52 + 30 * (1 - sensitivity)` (range –52 dB … –22 dB).
- **Default sensitivity**: 0.22 (~–45 dB).
- **Calibration margin**: baseline + 22 dB.
- **Cooldown**: 520 ms.
- **Attack gate**: 0.5 × dynamic window.
- **Sharpness gate**: 0.03.
- **Formant Q / freq**: see `synthMoan()` in `src/App.tsx`.
Enjoy responsibly, and have fun customizing your own playful reactions!