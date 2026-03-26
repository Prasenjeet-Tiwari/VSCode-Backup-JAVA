# ------------------------------------------------------------
# Create a numeric vector of 15 random integers between 10 and 100
# sample() directly returns a numeric vector of length 15
# ------------------------------------------------------------

v <- sample(10:100, 15)
v

# ------------------------------------------------------------
# Calculate Mean, Median, Standard Deviation, Maximum and Minimum
# ------------------------------------------------------------

mean_v   <- mean(v)
median_v <- median(v)
sd_v     <- sd(v)
max_v    <- max(v)
min_v    <- min(v)

mean_v
median_v
sd_v
max_v
min_v

# ------------------------------------------------------------
# Replace all values less than 40 with NA
# ------------------------------------------------------------

v[v < 40] <- NA
v

# ------------------------------------------------------------
# Count missing (NA) values
# ------------------------------------------------------------

na_count <- sum(is.na(v))
na_count

# ------------------------------------------------------------
# Min–Max Normalization
# Formula: (x − min) / (max − min)
# na.rm = TRUE ignores NA values
# ------------------------------------------------------------

v_min <- min(v, na.rm = TRUE)
v_max <- max(v, na.rm = TRUE)

v_norm <- (v - v_min) / (v_max - v_min)
v_norm
