# Generate 100 random normal values
set.seed(123)
x <- rnorm(100, mean = 50, sd = 10)

# Kolmogorov-Smirnov test
ks.test(x, "pnorm", mean(x), sd(x))

# Simulated regression data
x_val <- rnorm(100)
y_val <- 2*x_val + rnorm(100)

# Linear regression model
model <- lm(y_val ~ x_val)

# Model summary
summary(model)

# Plot regression
plot(x_val, y_val)
abline(model, col="blue")