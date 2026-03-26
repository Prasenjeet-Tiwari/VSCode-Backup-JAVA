# Q4 Hypothesis Testing

set.seed(123)

# generate sample data
x <- rnorm(50, mean = 52, sd = 10)

# perform t test
tr <- t.test(x, mu = 50)

print(tr)

# sample mean
m <- mean(x)

# confidence interval
ci <- tr$conf.int

print(ci)

# visualization
hist(x,
     col = "lightblue",
     main = "Sample Distribution",
     xlab = "Values")

abline(v = m, col = "red", lwd = 2)
abline(v = ci, col = "darkgreen", lwd = 2)