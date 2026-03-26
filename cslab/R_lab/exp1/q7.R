# Generate 100 random numbers
x <- runif(100)

# Plot the numbers
plot(x,
     type = "o",
     col = "blue",
     main = "100 Random Numbers",
     xlab = "Index",
     ylab = "Random Value")
