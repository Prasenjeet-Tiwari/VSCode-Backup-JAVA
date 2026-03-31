# Generate 100 random normal values
data <- rnorm(100)

# Histogram
hist(data, probability=TRUE,
     col="lightgreen",
     main="Normal Distribution with Density Curve",
     xlab="Values")

# Density estimate
dens <- density(data)

# Plot density curve
lines(dens, col="blue", lwd=2)