# simulate data
set.seed(123)
x <- rnorm(50)
y <- 2*x + rnorm(50)

# perform regression
model <- lm(y ~ x)

# summary
summary(model)

# scatter plot
plot(x, y,
     main="Linear Regression Example",
     xlab="X values",
     ylab="Y values",
     pch=19)

# regression line
abline(model, col="red", lwd=2)