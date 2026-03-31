# Simulate binomial data (n=10, p=0.5)
data <- rbinom(100, size=10, prob=0.5)

# Plot histogram (probability scale)
hist(data, probability=TRUE,
     col="lightblue",
     main="Binomial Distribution (n=10, p=0.5)",
     xlab="Values")

# Theoretical PMF
x <- 0:10
pmf <- dbinom(x, size=10, prob=0.5)

# Overlay PMF
points(x, pmf, col="red", pch=19)
lines(x, pmf, col="red", lwd=2)