Perfect! Here's **exactly what you need** to let users upload and use their own sounds. I'll provide both the frontend and backend changes:

---

## 📁 **Updated Project Structure**
```
window-slap/
├── frontend/
│   ├── index.html        # Modified (add upload UI)
│   ├── styles.css        # Modified (add upload styles)
│   └── app.js           # Modified (handle custom sounds)
├── backend/
│   ├── server.js        # Modified (handle file uploads)
│   ├── uploads/         # NEW - stores uploaded sounds
│   └── package.json
```

---

## 🔧 **Step 1: Update Backend for File Uploads**

### **Updated `backend/server.js`**
```javascript
const express = require('express');
const cors = require('cors');
const multer = require('multer');
const path = require('path');
const fs = require('fs');

const app = express();
const PORT = process.env.PORT || 3000;

// Create uploads directory
const uploadsDir = path.join(__dirname, 'uploads');
if (!fs.existsSync(uploadsDir)) {
    fs.mkdirSync(uploadsDir, { recursive: true });
}

// Configure multer for file uploads
const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, uploadsDir);
    },
    filename: (req, file, cb) => {
        // Generate unique filename
        const uniqueName = `${Date.now()}-${Math.random().toString(36).substr(2, 9)}${path.extname(file.originalname)}`;
        cb(null, uniqueName);
    }
});

const upload = multer({
    storage: storage,
    limits: { fileSize: 10 * 1024 * 1024 }, // 10MB limit
    fileFilter: (req, file, cb) => {
        const allowedTypes = /mp3|wav|ogg|m4a/;
        const extname = allowedTypes.test(path.extname(file.originalname).toLowerCase());
        const mimetype = allowedTypes.test(file.mimetype);
        
        if (extname && mimetype) {
            return cb(null, true);
        } else {
            cb(new Error('Only audio files are allowed (mp3, wav, ogg, m4a)'));
        }
    }
});

// Middleware
app.use(cors());
app.use(express.json());
app.use('/uploads', express.static(uploadsDir)); // Serve uploaded files

// In-memory user sounds storage
const userSounds = new Map(); // userId -> {soundUrl, filename}

// Generate unique user ID
const generateUserId = () => {
    return Math.random().toString(36).substr(2, 9);
};

// API Routes
app.get('/api/health', (req, res) => {
    res.json({ status: 'ok', timestamp: Date.now() });
});

// Upload custom sound
app.post('/api/upload-sound', upload.single('soundFile'), (req, res) => {
    try {
        if (!req.file) {
            return res.status(400).json({ error: 'No file uploaded' });
        }

        const userId = req.body.userId || generateUserId();
        const soundUrl = `/uploads/${req.file.filename}`;
        
        // Store user's sound
        userSounds.set(userId, {
            soundUrl,
            filename: req.file.filename,
            originalName: req.file.originalname,
            uploadedAt: Date.now()
        });

        res.json({
            success: true,
            userId,
            soundUrl,
            filename: req.file.originalname,
            message: 'Sound uploaded successfully'
        });
    } catch (error) {
        console.error('Upload error:', error);
        res.status(500).json({ error: 'Failed to upload sound' });
    }
});

// Get user's custom sound
app.get('/api/user-sound/:userId', (req, res) => {
    const userId = req.params.userId;
    const soundData = userSounds.get(userId);
    
    if (soundData) {
        res.json({
            success: true,
            soundUrl: soundData.soundUrl,
            filename: soundData.originalName
        });
    } else {
        res.json({
            success: false,
            message: 'No custom sound found'
        });
    }
});

// Delete custom sound
app.delete('/api/user-sound/:userId', (req, res) => {
    const userId = req.params.userId;
    const soundData = userSounds.get(userId);
    
    if (soundData) {
        // Delete file from disk
        const filePath = path.join(uploadsDir, soundData.filename);
        if (fs.existsSync(filePath)) {
            fs.unlinkSync(filePath);
        }
        
        // Remove from memory
        userSounds.delete(userId);
        
        res.json({ success: true, message: 'Sound deleted successfully' });
    } else {
        res.status(404).json({ error: 'Sound not found' });
    }
});

// Static files
app.use(express.static(path.join(__dirname, '../frontend')));

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
```

### **Update `backend/package.json`**
```json
{
  "name": "window-slap-backend",
  "version": "1.0.0",
  "dependencies": {
    "express": "^4.18.2",
    "cors": "^2.8.5",
    "multer": "^1.4.5-lts.1"
  }
}
```

Then install multer:
```bash
cd backend
npm install multer
```

---

## 🎨 **Step 2: Update Frontend**

### **Add to `frontend/index.html` (in settings panel)**
```html
<!-- Add this in settings panel after existing setting groups -->
<div class="setting-group">
    <label class="setting-label">Custom Sound</label>
    <div class="custom-sound-controls">
        <div class="file-upload">
            <input type="file" id="soundUpload" accept="audio/*" style="display: none;">
            <button class="btn-secondary" id="chooseFileBtn">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/>
                    <polyline points="17 8 12 3 7 8"/>
                    <line x1="12" y1="3" x2="12" y2="15"/>
                </svg>
                Choose Audio File
            </button>
            <span class="file-name" id="fileName">No file chosen</span>
        </div>
        <div class="upload-progress" id="uploadProgress" style="display: none;">
            <div class="progress-bar">
                <div class="progress-fill" id="progressFill"></div>
            </div>
            <span class="progress-text" id="progressText">Uploading...</span>
        </div>
        <div class="current-sound" id="currentSoundInfo" style="display: none;">
            <div class="sound-preview">
                <audio controls id="soundPreview" style="width: 100%;"></audio>
                <button class="btn-icon small" id="deleteSound" title="Delete sound">
                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M3 6h18M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                    </svg>
                </button>
            </div>
        </div>
        <div class="audio-formats">
            <small>Supported: MP3, WAV, OGG, M4A (Max 10MB)</small>
        </div>
    </div>
</div>
```

### **Add to `frontend/styles.css`**
```css
/* Custom Sound Upload Styles */
.custom-sound-controls {
    display: flex;
    flex-direction: column;
    gap: 12px;
}

.file-upload {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 12px;
    background: var(--bg);
    border-radius: var(--radius-md);
    border: 1px dashed var(--border);
}

.btn-secondary {
    padding: 10px 16px;
    background: var(--card);
    border: 1px solid var(--border);
    border-radius: var(--radius-md);
    color: var(--fg);
    font-family: var(--font-display);
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: all var(--transition-fast);
    display: flex;
    align-items: center;
    gap: 8px;
}

.btn-secondary:hover {
    background: var(--card-hover);
    border-color: var(--accent);
}

.btn-secondary:active {
    transform: scale(0.98);
}

.file-name {
    font-size: 13px;
    color: var(--fg-muted);
    flex: 1;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.upload-progress {
    padding: 12px;
    background: var(--bg);
    border-radius: var(--radius-md);
}

.progress-bar {
    height: 8px;
    background: var(--border);
    border-radius: 4px;
    overflow: hidden;
    margin-bottom: 8px;
}

.progress-fill {
    height: 100%;
    width: 0%;
    background: linear-gradient(90deg, var(--accent), #00ff88);
    border-radius: 4px;
    transition: width 300ms ease-out;
}

.progress-text {
    font-size: 12px;
    color: var(--fg-muted);
    display: block;
    text-align: center;
}

.current-sound {
    padding: 16px;
    background: linear-gradient(135deg, var(--accent-dim) 0%, transparent 100%);
    border-radius: var(--radius-md);
    border: 1px solid var(--accent);
}

.sound-preview {
    display: flex;
    align-items: center;
    gap: 12px;
}

.sound-preview audio {
    flex: 1;
    outline: none;
}

.sound-preview audio::-webkit-media-controls-panel {
    background: var(--bg);
}

.sound-preview audio::-webkit-media-controls-current-time-display,
.sound-preview audio::-webkit-media-controls-time-remaining-display {
    color: var(--fg);
}

.btn-icon.small {
    width: 32px;
    height: 32px;
    background: var(--bg);
    border: 1px solid var(--border);
}

.btn-icon.small:hover {
    background: var(--warning);
    border-color: var(--warning);
    color: var(--bg);
}

.audio-formats {
    text-align: center;
    font-size: 11px;
    color: var(--fg-muted);
    opacity: 0.7;
}
```

### **Update `frontend/app.js`**
```javascript
// Add these variables to the WindowSlapApp class:
class WindowSlapApp {
    constructor() {
        // ... existing code ...
        
        this.customSound = {
            url: null,
            name: null,
            audioElement: null
        };
        
        // ... existing code ...
    }
    
    init() {
        // ... existing code ...
        
        // Bind custom sound events
        this.bindCustomSoundEvents();
        
        // Load custom sound if exists
        this.loadCustomSound();
    }
    
    bindCustomSoundEvents() {
        // File upload
        const fileInput = document.getElementById('soundUpload');
        const chooseBtn = document.getElementById('chooseFileBtn');
        const deleteBtn = document.getElementById('deleteSound');
        
        chooseBtn.addEventListener('click', () => fileInput.click());
        fileInput.addEventListener('change', (e) => this.handleFileUpload(e));
        
        if (deleteBtn) {
            deleteBtn.addEventListener('click', () => this.deleteCustomSound());
        }
    }
    
    async handleFileUpload(event) {
        const file = event.target.files[0];
        if (!file) return;
        
        // Validate file
        const validTypes = ['audio/mpeg', 'audio/wav', 'audio/ogg', 'audio/mp4', 'audio/x-m4a'];
        const maxSize = 10 * 1024 * 1024; // 10MB
        
        if (!validTypes.includes(file.type) && !file.name.match(/\.(mp3|wav|ogg|m4a)$/i)) {
            this.showToast('Please upload a valid audio file (MP3, WAV, OGG, M4A)', 'error');
            return;
        }
        
        if (file.size > maxSize) {
            this.showToast('File too large. Maximum size is 10MB', 'error');
            return;
        }
        
        // Update UI
        document.getElementById('fileName').textContent = file.name;
        
        // Show progress
        const progressEl = document.getElementById('uploadProgress');
        const progressFill = document.getElementById('progressFill');
        const progressText = document.getElementById('progressText');
        
        progressEl.style.display = 'block';
        progressText.textContent = 'Uploading...';
        
        // Upload to backend
        try {
            const formData = new FormData();
            formData.append('soundFile', file);
            formData.append('userId', this.getUserId());
            
            // Simulate progress (in real app, use XMLHttpRequest for actual progress)
            let progress = 0;
            const progressInterval = setInterval(() => {
                progress += Math.random() * 10;
                if (progress > 90) clearInterval(progressInterval);
                progressFill.style.width = `${progress}%`;
            }, 100);
            
            const response = await fetch('/api/upload-sound', {
                method: 'POST',
                body: formData
            });
            
            clearInterval(progressInterval);
            progressFill.style.width = '100%';
            
            if (!response.ok) throw new Error('Upload failed');
            
            const data = await response.json();
            
            // Update custom sound
            this.customSound = {
                url: data.soundUrl,
                name: data.filename,
                audioElement: null
            };
            
            // Save to localStorage
            this.saveCustomSound();
            
            // Switch to custom sound mode
            this.settings.soundPack = 'custom';
            this.updateSoundPackButtons();
            
            // Update UI
            progressText.textContent = 'Upload complete!';
            this.showCustomSoundInfo();
            this.showToast('Custom sound uploaded successfully!', 'success');
            
            // Hide progress after delay
            setTimeout(() => {
                progressEl.style.display = 'none';
                progressFill.style.width = '0%';
            }, 2000);
            
        } catch (error) {
            console.error('Upload error:', error);
            progressText.textContent = 'Upload failed';
            this.showToast('Failed to upload sound. Please try again.', 'error');
        }
    }
    
    showCustomSoundInfo() {
        const infoEl = document.getElementById('currentSoundInfo');
        const previewEl = document.getElementById('soundPreview');
        
        if (this.customSound.url) {
            infoEl.style.display = 'block';
            previewEl.src = this.customSound.url;
            previewEl.load();
        }
    }
    
    async deleteCustomSound() {
        if (!this.customSound.url) return;
        
        if (confirm('Delete custom sound?')) {
            try {
                const response = await fetch(`/api/user-sound/${this.getUserId()}`, {
                    method: 'DELETE'
                });
                
                if (response.ok) {
                    this.customSound = { url: null, name: null, audioElement: null };
                    localStorage.removeItem('windowSlapCustomSound');
                    
                    // Reset to glass sound pack
                    this.settings.soundPack = 'glass';
                    this.updateSoundPackButtons();
                    
                    // Update UI
                    document.getElementById('currentSoundInfo').style.display = 'none';
                    document.getElementById('fileName').textContent = 'No file chosen';
                    document.getElementById('soundUpload').value = '';
                    
                    this.showToast('Custom sound deleted', 'success');
                }
            } catch (error) {
                console.error('Delete error:', error);
            }
        }
    }
    
    loadCustomSound() {
        try {
            const saved = localStorage.getItem('windowSlapCustomSound');
            if (saved) {
                this.customSound = JSON.parse(saved);
                this.showCustomSoundInfo();
            }
        } catch (e) {
            console.warn('Failed to load custom sound:', e);
        }
    }
    
    saveCustomSound() {
        localStorage.setItem('windowSlapCustomSound', JSON.stringify(this.customSound));
    }
    
    getUserId() {
        let userId = localStorage.getItem('windowSlapUserId');
        if (!userId) {
            userId = `user_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
            localStorage.setItem('windowSlapUserId', userId);
        }
        return userId;
    }
    
    updateSoundPackButtons() {
        document.querySelectorAll('.pack-btn').forEach(btn => {
            btn.classList.remove('active');
            if (btn.dataset.pack === this.settings.soundPack) {
                btn.classList.add('active');
            }
        });
        this.saveState();
    }
    
    // MODIFY THIS FUNCTION TO HANDLE CUSTOM SOUNDS
    playSound(intensity) {
        if (!this.audioContext) return;
        
        const ctx = this.audioContext;
        const now = ctx.currentTime;
        const volume = this.settings.volume * Math.min(1, intensity / 50);
        
        // NEW: Handle custom sound
        if (this.settings.soundPack === 'custom' && this.customSound.url) {
            this.playCustomSound(intensity);
        } else {
            // Existing synth sounds
            switch (this.settings.soundPack) {
                case 'glass':
                    this.playGlassSound(ctx, now, volume, intensity);
                    break;
                case 'wood':
                    this.playWoodSound(ctx, now, volume, intensity);
                    break;
                case 'metal':
                    this.playMetalSound(ctx, now, volume, intensity);
                    break;
                case 'silly':
                    this.playSillySound(ctx, now, volume, intensity);
                    break;
            }
        }
    }
    
    playCustomSound(intensity) {
        if (!this.customSound.url) return;
        
        // Create audio element if not exists
        if (!this.customSound.audioElement) {
            this.customSound.audioElement = new Audio(this.customSound.url);
        }
        
        const audio = this.customSound.audioElement.cloneNode();
        const volume = this.settings.volume * (intensity / 100);
        const playbackRate = 0.5 + (intensity / 200); // Speed up slightly with intensity
        
        audio.volume = Math.min(1, volume);
        audio.playbackRate = Math.max(0.5, Math.min(2, playbackRate));
        audio.currentTime = 0;
        
        // Try to play
        audio.play().catch(e => {
            console.warn('Audio play failed:', e);
            // Fall back to synth sound
            this.playGlassSound(this.audioContext, this.audioContext.currentTime, volume, intensity);
        });
        
        // Clean up old audio elements periodically
        setTimeout(() => {
            if (audio.src) audio.src = '';
        }, 5000);
    }
    
    // ... rest of existing code ...
}
```

---

## 🚀 **Step 3: Updated Docker Deployment**

### **Update `Dockerfile`**
```dockerfile
FROM node:20-alpine

WORKDIR /app

# Install dependencies
COPY backend/package*.json ./
RUN npm ci --only=production

# Create uploads directory
RUN mkdir -p uploads

# Copy backend files
COPY backend/server.js .
COPY backend/package.json .

# Copy frontend
COPY frontend/ ./public/

# Expose port
EXPOSE 3000

# Start server
CMD ["node", "server.js"]
```

### **Update `docker-compose.yml`**
```yaml
version: '3.8'

services:
  web:
    build: .
    ports:
      - "3000:3000"
    environment:
      - NODE_ENV=production
      - PORT=3000
    volumes:
      - ./uploads:/app/uploads  # Persist uploaded files
    restart: unless-stopped
```

---

## 🎮 **How Users Use It**

1. **User clicks "Choose Audio File"** in Settings
2. **Selects MP3/WAV/OGG/M4A file** (max 10MB)
3. **File uploads** → shows progress bar
4. **Sound appears in preview** with play/delete buttons
5. **Automatically switches to "Custom" sound pack**
6. **Every slap now plays their uploaded sound** with pitch/volume based on intensity

---

## 🛠 **To Run:**
```bash
# 1. Install backend dependencies
cd backend
npm install

# 2. Start server
npm run dev

# 3. OR with Docker
docker-compose up --build
```

**Features:**
- ✅ Users can upload any audio file
- ✅ Preview uploaded sounds
- ✅ Delete sounds when needed
- ✅ File persistence (Docker volume)
- ✅ Fallback to synth if upload fails
- ✅ Sound changes pitch/volume with slap intensity

The uploaded sounds will be stored in `backend/uploads/` locally, or in Docker volumes when deployed!