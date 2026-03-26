set.seed(1)

# Population distribution (exponential)
pop <- rexp(10000)

# Sample means for different sample sizes
mean1 <- replicate(1000, mean(sample(pop, 5)))
mean2 <- replicate(1000, mean(sample(pop, 30)))
mean3 <- replicate(1000, mean(sample(pop, 100)))

# Plot histograms
par(mfrow=c(3,1))

hist(mean1, col="lightblue", main="Sample Size = 5", xlab="Sample Mean")

hist(mean2, col="lightgreen", main="Sample Size = 30", xlab="Sample Mean")

hist(mean3, col="lightpink", main="Sample Size = 100", xlab="Sample Mean")