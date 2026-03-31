const express = require("express");
const cors = require("cors");
const rateLimit = require("express-rate-limit");
const path = require("path");

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Rate limiting
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutes
  max: 1000, // limit each IP to 1000 requests per windowMs
  message: { error: "Too many requests, please try again later." },
});
app.use("/api/", limiter);

// In-memory stats storage (use a real database in production)
let globalStats = {
  totalSlaps: 0,
  totalIntensity: 0,
  maxIntensity: 0,
  soundPackUsage: { glass: 0, wood: 0, metal: 0, silly: 0 },
  startTime: Date.now(),
};

// API Routes
app.post("/api/slap", (req, res) => {
  const { intensity, soundPack } = req.body;

  // Validate input
  const validIntensity = Math.max(0, Math.min(100, Number(intensity) || 0));
  const validPack = ["glass", "wood", "metal", "silly"].includes(soundPack)
    ? soundPack
    : "glass";

  // Update stats
  globalStats.totalSlaps++;
  globalStats.totalIntensity += validIntensity;
  globalStats.maxIntensity = Math.max(globalStats.maxIntensity, validIntensity);
  globalStats.soundPackUsage[validPack]++;

  res.json({
    success: true,
    globalTotal: globalStats.totalSlaps,
  });
});

app.get("/api/stats", (req, res) => {
  const avgIntensity =
    globalStats.totalSlaps > 0
      ? Math.round(globalStats.totalIntensity / globalStats.totalSlaps)
      : 0;

  res.json({
    totalSlaps: globalStats.totalSlaps,
    averageIntensity: avgIntensity,
    maxIntensity: globalStats.maxIntensity,
    soundPackUsage: globalStats.soundPackUsage,
    uptime: Math.floor((Date.now() - globalStats.startTime) / 1000),
  });
});

app.get("/api/leaderboard", (req, res) => {
  // In production, this would query a database
  // For now, return mock data
  res.json({
    topIntensity: [
      { name: "Anonymous", intensity: 98, timestamp: Date.now() - 3600000 },
      { name: "Anonymous", intensity: 95, timestamp: Date.now() - 7200000 },
      { name: "Anonymous", intensity: 92, timestamp: Date.now() - 1800000 },
    ],
    topSlappers: [
      { name: "Anonymous", slaps: 1234 },
      { name: "Anonymous", slaps: 987 },
      { name: "Anonymous", slaps: 654 },
    ],
  });
});

// Health check
app.get("/api/health", (req, res) => {
  res.json({ status: "ok", timestamp: Date.now() });
});

// Serve static files in production
app.use(express.static(path.join(__dirname, "../frontend")));

// Catch-all route for SPA
app.get("*", (req, res) => {
  res.sendFile(path.join(__dirname, "../frontend/index.html"));
});

// Error handling
app.use((err, req, res, next) => {
  console.error("Server error:", err);
  res.status(500).json({ error: "Internal server error" });
});

// Start server
app.listen(PORT, "0.0.0.0", () => {
  console.log(`
╔════════════════════════════════════════════╗
║                                            ║
║   Window Slap Server                       ║
║   Running on http://localhost:${PORT}         ║
║                                            ║
║   API Endpoints:                           ║
║   POST /api/slap    - Record a slap        ║
║   GET  /api/stats   - Get global stats     ║
║   GET  /api/health  - Health check         ║
║                                            ║
╚════════════════════════════════════════════╝
    `);
});

module.exports = app;
