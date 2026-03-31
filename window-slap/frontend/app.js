// Window Slap App - Frontend Logic
class WindowSlapApp {
  constructor() {
    // State
    this.stats = {
      totalSlaps: 0,
      maxIntensity: 0,
      intensitySum: 0,
      sessionStart: Date.now(),
      history: [],
    };

    this.settings = {
      volume: 0.7,
      sensitivity: 5,
      soundPack: "glass",
      crackEffect: true,
      screenShake: true,
    };

    this.achievements = [
      {
        id: "first_slap",
        name: "First Contact",
        desc: "Slap the window for the first time",
        icon: "👋",
        unlocked: false,
      },
      {
        id: "slap_10",
        name: "Getting Warmer",
        desc: "Slap 10 times",
        icon: "🔥",
        unlocked: false,
      },
      {
        id: "slap_100",
        name: "Slap Master",
        desc: "Slap 100 times",
        icon: "🏆",
        unlocked: false,
      },
      {
        id: "max_intensity",
        name: "Maximum Overdrive",
        desc: "Reach max intensity",
        icon: "💥",
        unlocked: false,
      },
      {
        id: "gentle",
        name: "Gentle Touch",
        desc: "10 slaps under 20 intensity",
        icon: "🪶",
        unlocked: false,
      },
      {
        id: "combo",
        name: "Combo King",
        desc: "5 slaps in 2 seconds",
        icon: "⚡",
        unlocked: false,
      },
    ];

    this.audioContext = null;
    this.lastSlapTime = 0;
    this.comboCount = 0;
    this.gentleCount = 0;

    // DOM Elements
    this.elements = {};

    // Initialize
    this.init();
  }

  async init() {
    this.cacheElements();
    this.loadState();
    this.bindEvents();
    this.initAudio();
    this.startSessionTimer();
    this.updateUI();
    this.renderAchievements();

    // Start animation loop
    this.animate();
  }

  cacheElements() {
    const ids = [
      "windowFrame",
      "windowGlass",
      "slapZone",
      "hintText",
      "rippleContainer",
      "crackOverlay",
      "intensityFill",
      "intensityValue",
      "totalSlaps",
      "maxIntensity",
      "sessionTime",
      "settingsOverlay",
      "settingsBtn",
      "closeSettings",
      "statsOverlay",
      "statsBtn",
      "closeStats",
      "volumeSlider",
      "volumeValue",
      "sensitivitySlider",
      "sensitivityValue",
      "crackEffect",
      "screenShake",
      "resetStats",
      "statTotalSlaps",
      "statAvgIntensity",
      "statMaxIntensity",
      "statSlapRate",
      "historyChart",
      "achievementList",
      "toastContainer",
    ];

    ids.forEach((id) => {
      this.elements[id] = document.getElementById(id);
    });
  }

  bindEvents() {
    // Slap events
    const slapZone = this.elements.slapZone;
    slapZone.addEventListener("mousedown", (e) => this.handleSlap(e));
    slapZone.addEventListener("touchstart", (e) => this.handleSlap(e), {
      passive: false,
    });

    // Settings panel
    this.elements.settingsBtn.addEventListener("click", () =>
      this.togglePanel("settings"),
    );
    this.elements.closeSettings.addEventListener("click", () =>
      this.togglePanel("settings"),
    );
    this.elements.settingsOverlay.addEventListener("click", (e) => {
      if (e.target === this.elements.settingsOverlay) {
        this.togglePanel("settings");
      }
    });

    // Stats panel
    this.elements.statsBtn.addEventListener("click", () =>
      this.togglePanel("stats"),
    );
    this.elements.closeStats.addEventListener("click", () =>
      this.togglePanel("stats"),
    );
    this.elements.statsOverlay.addEventListener("click", (e) => {
      if (e.target === this.elements.statsOverlay) {
        this.togglePanel("stats");
      }
    });

    // Settings controls
    this.elements.volumeSlider.addEventListener("input", (e) => {
      this.settings.volume = e.target.value / 100;
      this.elements.volumeValue.textContent = `${e.target.value}%`;
      this.saveState();
    });

    this.elements.sensitivitySlider.addEventListener("input", (e) => {
      this.settings.sensitivity = parseInt(e.target.value);
      this.elements.sensitivityValue.textContent = e.target.value;
      this.saveState();
    });

    this.elements.crackEffect.addEventListener("change", (e) => {
      this.settings.crackEffect = e.target.checked;
      this.saveState();
    });

    this.elements.screenShake.addEventListener("change", (e) => {
      this.settings.screenShake = e.target.checked;
      this.saveState();
    });

    // Sound pack buttons
    document.querySelectorAll(".pack-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        document
          .querySelectorAll(".pack-btn")
          .forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
        this.settings.soundPack = btn.dataset.pack;
        this.saveState();
      });
    });

    // Reset stats
    this.elements.resetStats.addEventListener("click", () => this.resetStats());

    // Keyboard shortcuts
    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape") {
        this.closeAllPanels();
      }
    });
  }

  async initAudio() {
    // Create audio context on first interaction
    const initContext = () => {
      if (!this.audioContext) {
        this.audioContext = new (
          window.AudioContext || window.webkitAudioContext
        )();
      }
      document.removeEventListener("click", initContext);
      document.removeEventListener("touchstart", initContext);
    };
    document.addEventListener("click", initContext);
    document.addEventListener("touchstart", initContext);
  }

  handleSlap(e) {
    e.preventDefault();

    const rect = this.elements.windowGlass.getBoundingClientRect();
    let x, y, velocity;

    if (e.type === "touchstart") {
      const touch = e.touches[0];
      x = touch.clientX - rect.left;
      y = touch.clientY - rect.top;
      velocity = this.calculateVelocity(touch);
    } else {
      x = e.clientX - rect.left;
      y = e.clientY - rect.top;
      velocity = this.calculateVelocity(e);
    }

    // Calculate intensity based on velocity and sensitivity
    const intensity = Math.min(
      100,
      Math.round(velocity * this.settings.sensitivity * 2),
    );

    // Trigger effects
    this.createRipple(x, y, intensity);
    this.playSound(intensity);

    if (this.settings.crackEffect && intensity > 50) {
      this.createCrack(x, y, intensity);
    }

    if (this.settings.screenShake && intensity > 30) {
      this.shakeWindow();
    }

    // Update stats
    this.updateStats(intensity);

    // Update intensity meter
    this.updateIntensity(intensity);

    // Hide hint
    this.elements.hintText.classList.add("hidden");

    // Check achievements
    this.checkAchievements(intensity);
  }

  calculateVelocity(event) {
    const now = Date.now();
    const dt = now - this.lastSlapTime;
    this.lastSlapTime = now;

    // Combo detection
    if (dt < 400) {
      this.comboCount++;
    } else {
      this.comboCount = 1;
    }

    // Simulate velocity based on timing and combo
    const baseVelocity = Math.min(100, 50 + this.comboCount * 10);
    return baseVelocity + Math.random() * 20;
  }

  createRipple(x, y, intensity) {
    const container = this.elements.rippleContainer;
    const size = 50 + intensity * 2;

    const ripple = document.createElement("div");
    ripple.className = "ripple";
    ripple.style.left = `${x}px`;
    ripple.style.top = `${y}px`;
    ripple.style.width = `${size}px`;
    ripple.style.height = `${size}px`;

    // Color based on intensity
    const hue = 160 - intensity * 0.6; // Green to red
    const inner = document.createElement("div");
    inner.className = "ripple-inner";
    inner.style.borderColor = `hsl(${hue}, 80%, 50%)`;
    ripple.appendChild(inner);

    container.appendChild(ripple);

    // Remove after animation
    setTimeout(() => ripple.remove(), 600);
  }

  createCrack(x, y, intensity) {
    const svg = this.elements.crackOverlay;
    const rect = svg.getBoundingClientRect();

    // Create crack pattern
    const branches = Math.floor(3 + intensity / 20);

    for (let i = 0; i < branches; i++) {
      const path = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path",
      );

      // Generate jagged path
      let d = `M ${x} ${y}`;
      const angle = ((Math.PI * 2) / branches) * i + Math.random() * 0.5;
      const length = 30 + Math.random() * 50;

      let cx = x;
      let cy = y;
      const segments = 3 + Math.floor(Math.random() * 3);

      for (let j = 0; j < segments; j++) {
        const segLen = length / segments;
        const deviation = (Math.random() - 0.5) * 20;
        cx += Math.cos(angle) * segLen + deviation;
        cy += Math.sin(angle) * segLen + deviation;
        d += ` L ${cx} ${cy}`;
      }

      path.setAttribute("d", d);
      path.setAttribute("class", "crack-line");

      // Fade based on intensity
      const opacity = 0.3 + (intensity / 100) * 0.5;
      path.style.stroke = `rgba(255, 255, 255, ${opacity})`;

      svg.appendChild(path);

      // Remove after some time
      setTimeout(
        () => {
          path.style.transition = "opacity 2s";
          path.style.opacity = "0";
          setTimeout(() => path.remove(), 2000);
        },
        3000 + Math.random() * 2000,
      );
    }
  }

  playSound(intensity) {
    if (!this.audioContext) return;

    const ctx = this.audioContext;
    const now = ctx.currentTime;
    const volume = this.settings.volume * Math.min(1, intensity / 50);

    // Different sound packs
    switch (this.settings.soundPack) {
      case "glass":
        this.playGlassSound(ctx, now, volume, intensity);
        break;
      case "wood":
        this.playWoodSound(ctx, now, volume, intensity);
        break;
      case "metal":
        this.playMetalSound(ctx, now, volume, intensity);
        break;
      case "silly":
        this.playSillySound(ctx, now, volume, intensity);
        break;
    }
  }

  playGlassSound(ctx, now, volume, intensity) {
    // Impact
    const impactOsc = ctx.createOscillator();
    const impactGain = ctx.createGain();
    impactOsc.type = "sine";
    impactOsc.frequency.setValueAtTime(800 + intensity * 5, now);
    impactOsc.frequency.exponentialRampToValueAtTime(200, now + 0.1);
    impactGain.gain.setValueAtTime(volume * 0.5, now);
    impactGain.gain.exponentialRampToValueAtTime(0.001, now + 0.1);
    impactOsc.connect(impactGain);
    impactGain.connect(ctx.destination);
    impactOsc.start(now);
    impactOsc.stop(now + 0.1);

    // Ring
    const ringOsc = ctx.createOscillator();
    const ringGain = ctx.createGain();
    ringOsc.type = "sine";
    ringOsc.frequency.setValueAtTime(1200 + Math.random() * 400, now);
    ringGain.gain.setValueAtTime(volume * 0.2, now);
    ringGain.gain.exponentialRampToValueAtTime(0.001, now + 0.5);
    ringOsc.connect(ringGain);
    ringGain.connect(ctx.destination);
    ringOsc.start(now);
    ringOsc.stop(now + 0.5);

    // Noise burst
    this.playNoiseBurst(ctx, now, volume * 0.3, 0.05);
  }

  playWoodSound(ctx, now, volume, intensity) {
    const osc = ctx.createOscillator();
    const gain = ctx.createGain();
    const filter = ctx.createBiquadFilter();

    osc.type = "square";
    osc.frequency.setValueAtTime(100 + intensity * 2, now);
    osc.frequency.exponentialRampToValueAtTime(50, now + 0.08);

    filter.type = "lowpass";
    filter.frequency.setValueAtTime(1000, now);

    gain.gain.setValueAtTime(volume * 0.4, now);
    gain.gain.exponentialRampToValueAtTime(0.001, now + 0.15);

    osc.connect(filter);
    filter.connect(gain);
    gain.connect(ctx.destination);
    osc.start(now);
    osc.stop(now + 0.15);

    this.playNoiseBurst(ctx, now, volume * 0.2, 0.03);
  }

  playMetalSound(ctx, now, volume, intensity) {
    // Metallic clang
    const frequencies = [800, 1200, 1800, 2400];
    frequencies.forEach((freq, i) => {
      const osc = ctx.createOscillator();
      const gain = ctx.createGain();
      osc.type = "sine";
      osc.frequency.setValueAtTime(freq + intensity * 3, now);
      gain.gain.setValueAtTime(volume * 0.15, now);
      gain.gain.exponentialRampToValueAtTime(0.001, now + 0.3 - i * 0.05);
      osc.connect(gain);
      gain.connect(ctx.destination);
      osc.start(now);
      osc.stop(now + 0.3);
    });

    this.playNoiseBurst(ctx, now, volume * 0.15, 0.02);
  }

  playSillySound(ctx, now, volume, intensity) {
    // Boing/squeak sound
    const osc = ctx.createOscillator();
    const gain = ctx.createGain();

    osc.type = "sine";
    osc.frequency.setValueAtTime(200, now);
    osc.frequency.exponentialRampToValueAtTime(800 + intensity * 5, now + 0.05);
    osc.frequency.exponentialRampToValueAtTime(150, now + 0.2);

    gain.gain.setValueAtTime(volume * 0.5, now);
    gain.gain.exponentialRampToValueAtTime(0.001, now + 0.3);

    osc.connect(gain);
    gain.connect(ctx.destination);
    osc.start(now);
    osc.stop(now + 0.3);
  }

  playNoiseBurst(ctx, now, volume, duration) {
    const bufferSize = ctx.sampleRate * duration;
    const buffer = ctx.createBuffer(1, bufferSize, ctx.sampleRate);
    const data = buffer.getChannelData(0);

    for (let i = 0; i < bufferSize; i++) {
      data[i] = (Math.random() * 2 - 1) * (1 - i / bufferSize);
    }

    const source = ctx.createBufferSource();
    const gain = ctx.createGain();
    source.buffer = buffer;
    gain.gain.setValueAtTime(volume, now);
    source.connect(gain);
    gain.connect(ctx.destination);
    source.start(now);
  }

  shakeWindow() {
    this.elements.windowFrame.classList.add("shake");
    setTimeout(() => {
      this.elements.windowFrame.classList.remove("shake");
    }, 200);
  }

  updateStats(intensity) {
    this.stats.totalSlaps++;
    this.stats.intensitySum += intensity;
    this.stats.maxIntensity = Math.max(this.stats.maxIntensity, intensity);
    this.stats.history.push(intensity);

    // Keep history limited
    if (this.stats.history.length > 50) {
      this.stats.history.shift();
    }

    // Track gentle slaps
    if (intensity < 20) {
      this.gentleCount++;
    } else {
      this.gentleCount = 0;
    }

    this.updateUI();
    this.saveState();

    // Send to backend
    this.sendStatsToBackend(intensity);
  }

  updateIntensity(intensity) {
    this.elements.intensityFill.style.width = `${intensity}%`;
    this.elements.intensityValue.textContent = intensity;

    // Color based on intensity
    const hue = 160 - intensity * 0.6;
    this.elements.intensityFill.style.background = `linear-gradient(90deg, hsl(${hue}, 80%, 50%), hsl(${hue + 20}, 80%, 60%))`;
  }

  updateUI() {
    // Quick stats
    this.elements.totalSlaps.textContent = this.stats.totalSlaps;
    this.elements.maxIntensity.textContent = this.stats.maxIntensity;

    // Detailed stats
    this.elements.statTotalSlaps.textContent = this.stats.totalSlaps;
    this.elements.statMaxIntensity.textContent = this.stats.maxIntensity;

    const avgIntensity =
      this.stats.totalSlaps > 0
        ? Math.round(this.stats.intensitySum / this.stats.totalSlaps)
        : 0;
    this.elements.statAvgIntensity.textContent = avgIntensity;

    // Slap rate
    const sessionMinutes = (Date.now() - this.stats.sessionStart) / 60000;
    const slapRate =
      sessionMinutes > 0
        ? Math.round(this.stats.totalSlaps / sessionMinutes)
        : 0;
    this.elements.statSlapRate.textContent = slapRate;

    // History chart
    this.updateHistoryChart();
  }

  updateHistoryChart() {
    const chart = this.elements.historyChart;
    const bars =
      chart.querySelector(".chart-bars") || document.createElement("div");
    bars.className = "chart-bars";

    const displayHistory = this.stats.history.slice(-20);
    const maxVal = Math.max(...displayHistory, 1);

    bars.innerHTML = displayHistory
      .map(
        (val) =>
          `<div class="chart-bar" style="height: ${(val / maxVal) * 100}%"></div>`,
      )
      .join("");

    if (!chart.contains(bars)) {
      chart.appendChild(bars);
    }
  }

  startSessionTimer() {
    setInterval(() => {
      const elapsed = Date.now() - this.stats.sessionStart;
      const minutes = Math.floor(elapsed / 60000);
      const seconds = Math.floor((elapsed % 60000) / 1000);
      this.elements.sessionTime.textContent = `${minutes}:${seconds.toString().padStart(2, "0")}`;
    }, 1000);
  }

  checkAchievements(intensity) {
    // First slap
    if (this.stats.totalSlaps === 1) {
      this.unlockAchievement("first_slap");
    }

    // Slap milestones
    if (this.stats.totalSlaps >= 10) {
      this.unlockAchievement("slap_10");
    }
    if (this.stats.totalSlaps >= 100) {
      this.unlockAchievement("slap_100");
    }

    // Max intensity
    if (intensity >= 95) {
      this.unlockAchievement("max_intensity");
    }

    // Gentle
    if (this.gentleCount >= 10) {
      this.unlockAchievement("gentle");
    }

    // Combo
    if (this.comboCount >= 5) {
      this.unlockAchievement("combo");
    }
  }

  unlockAchievement(id) {
    const achievement = this.achievements.find((a) => a.id === id);
    if (achievement && !achievement.unlocked) {
      achievement.unlocked = true;
      this.showToast(`Achievement Unlocked: ${achievement.name}`, "success");
      this.renderAchievements();
      this.saveState();
    }
  }

  renderAchievements() {
    const list = this.elements.achievementList;
    list.innerHTML = this.achievements
      .map(
        (a) => `
            <div class="achievement-item ${a.unlocked ? "" : "locked"}">
                <div class="achievement-icon">${a.icon}</div>
                <div class="achievement-info">
                    <div class="achievement-name">${a.name}</div>
                    <div class="achievement-desc">${a.desc}</div>
                </div>
            </div>
        `,
      )
      .join("");
  }

  togglePanel(panel) {
    const overlay =
      panel === "settings"
        ? this.elements.settingsOverlay
        : this.elements.statsOverlay;

    overlay.classList.toggle("active");
  }

  closeAllPanels() {
    this.elements.settingsOverlay.classList.remove("active");
    this.elements.statsOverlay.classList.remove("active");
  }

  resetStats() {
    this.stats = {
      totalSlaps: 0,
      maxIntensity: 0,
      intensitySum: 0,
      sessionStart: Date.now(),
      history: [],
    };
    this.achievements.forEach((a) => (a.unlocked = false));
    this.gentleCount = 0;
    this.comboCount = 0;

    // Clear cracks
    const cracks = this.elements.crackOverlay.querySelectorAll(".crack-line");
    cracks.forEach((c) => c.remove());

    this.updateUI();
    this.renderAchievements();
    this.saveState();
    this.showToast("Statistics reset!", "success");
  }

  showToast(message, type = "info") {
    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    toast.textContent = message;
    this.elements.toastContainer.appendChild(toast);

    setTimeout(() => {
      toast.style.opacity = "0";
      toast.style.transform = "translateY(20px)";
      setTimeout(() => toast.remove(), 300);
    }, 3000);
  }

  async sendStatsToBackend(intensity) {
    try {
      await fetch("/api/slap", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          intensity,
          soundPack: this.settings.soundPack,
          timestamp: Date.now(),
        }),
      });
    } catch (e) {
      // Silently fail - backend is optional
    }
  }

  saveState() {
    const state = {
      stats: this.stats,
      settings: this.settings,
      achievements: this.achievements.map((a) => ({
        id: a.id,
        unlocked: a.unlocked,
      })),
    };
    localStorage.setItem("windowSlapState", JSON.stringify(state));
  }

  loadState() {
    try {
      const saved = localStorage.getItem("windowSlapState");
      if (saved) {
        const state = JSON.parse(saved);
        this.stats = { ...this.stats, ...state.stats };
        this.settings = { ...this.settings, ...state.settings };

        // Restore achievements
        if (state.achievements) {
          state.achievements.forEach((saved) => {
            const achievement = this.achievements.find(
              (a) => a.id === saved.id,
            );
            if (achievement) {
              achievement.unlocked = saved.unlocked;
            }
          });
        }

        // Update UI controls
        this.elements.volumeSlider.value = this.settings.volume * 100;
        this.elements.volumeValue.textContent = `${Math.round(this.settings.volume * 100)}%`;
        this.elements.sensitivitySlider.value = this.settings.sensitivity;
        this.elements.sensitivityValue.textContent = this.settings.sensitivity;
        this.elements.crackEffect.checked = this.settings.crackEffect;
        this.elements.screenShake.checked = this.settings.screenShake;

        // Update sound pack button
        document.querySelectorAll(".pack-btn").forEach((btn) => {
          btn.classList.toggle(
            "active",
            btn.dataset.pack === this.settings.soundPack,
          );
        });
      }
    } catch (e) {
      console.warn("Failed to load state:", e);
    }
  }

  animate() {
    // Decay intensity meter
    const currentWidth =
      parseFloat(this.elements.intensityFill.style.width) || 0;
    if (currentWidth > 0) {
      const decayed = Math.max(0, currentWidth - 2);
      this.elements.intensityFill.style.width = `${decayed}%`;
      this.elements.intensityValue.textContent = Math.round(decayed);
    }

    requestAnimationFrame(() => this.animate());
  }
}

// Initialize app
document.addEventListener("DOMContentLoaded", () => {
  new WindowSlapApp();
});
