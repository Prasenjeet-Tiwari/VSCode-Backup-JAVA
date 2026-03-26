set.seed(1)

n <- 200

# Simulate coin tosses (0 = Tail, 1 = Head)
toss <- sample(c(0,1), n, replace = TRUE)

# Cumulative mean
cum_mean <- cumsum(toss)/(1:n)

# Cumulative variance
cum_var <- sapply(1:n, function(i) var(toss[1:i]))

par(mfrow=c(1,2))

# Plot cumulative mean
plot(cum_mean, type="l", col="blue",
     main="Convergence of Mean",
     xlab="Number of Tosses",
     ylab="Cumulative Mean")

abline(h=0.5, col="red", lwd=2)

# Plot cumulative variance
plot(cum_var, type="l", col="darkgreen",
     main="Convergence of Variance",
     xlab="Number of Tosses",
     ylab="Cumulative Variance")

abline(h=0.25, col="red", lwd=2)