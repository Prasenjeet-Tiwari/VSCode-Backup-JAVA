# generate 50 random numbers from normal distribution
set.seed(123)
x <- rnorm(50)

# window size
w <- 5

# vector to store moving standard deviation
moving_sd <- c()

# calculate moving SD
for(i in 1:(length(x) - w + 1)) {
  moving_sd[i] <- sd(x[i:(i + w - 1)])
}

# print values
print(moving_sd)

# plot
plot(moving_sd, type="l",
     col="blue",
     main="Moving Window Standard Deviation (Window = 5)",
     xlab="Window Position",
     ylab="Standard Deviation")