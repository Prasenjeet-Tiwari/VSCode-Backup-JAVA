# Generate 100 random values from uniform distribution (0,1)
data <- runif(100)

# Compute cumulative sum
cum_sum <- cumsum(data)

# Plot both on same graph
plot(data, type="l", col="blue", lwd=2,
     ylab="Values", xlab="Index",
     main="Uniform Data and Cumulative Sum")

lines(cum_sum, col="red", lwd=2)

legend("topleft",
       legend=c("Original Data", "Cumulative Sum"),
       col=c("blue", "red"),
       lwd=2)