# Generate time series data
data <- rnorm(50)

# Moving average (window size = 3)
moving_avg <- filter(data, rep(1/3, 3), sides=1)

# Plot original data
plot(data, type="l", col="blue", lwd=2,
     main="Time Series with Moving Average",
     ylab="Values", xlab="Time")

# Add moving average
lines(moving_avg, col="red", lwd=2)

legend("topleft",
       legend=c("Original Data", "Moving Average"),
       col=c("blue", "red"),
       lwd=2)