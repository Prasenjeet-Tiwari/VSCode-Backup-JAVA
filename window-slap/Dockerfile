# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copy backend
COPY backend/package*.json ./backend/
WORKDIR /app/backend
RUN npm ci --only=production

# Production stage
FROM node:20-alpine

WORKDIR /app

# Copy backend files
COPY backend/package*.json ./
COPY backend/server.js ./

# Install production dependencies
RUN npm ci --only=production

# Copy frontend
COPY frontend/ ./public/

# Environment
ENV NODE_ENV=production
ENV PORT=3000

# Expose port
EXPOSE 3000

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:3000/api/health || exit 1

# Start server
CMD ["node", "server.js"]