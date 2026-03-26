# Generate 50 observations from exponential distribution
set.seed(123)   # for reproducibility
data <- rexp(50, rate = 0.5)   # chosen rate = 0.5

# Display first few values
cat("Sample Data:\n")
print(head(data))

# Define likelihood function for exponential distribution
likelihood_function <- function(lambda, data) {
  n <- length(data)
  likelihood <- (lambda^n) * exp(-lambda * sum(data))
  return(likelihood)
}

# Try different rate (lambda) values
lambda_values <- seq(0.1, 2, by = 0.1)

# Compute likelihood for each lambda
likelihoods <- sapply(lambda_values, likelihood_function, data = data)

# Find lambda with maximum likelihood
max_index <- which.max(likelihoods)
best_lambda <- lambda_values[max_index]

# Display results
cat("\nLambda values:\n")
print(lambda_values)

cat("\nLikelihood values:\n")
print(likelihoods)

cat("\nBest lambda (Maximum Likelihood Estimate):\n")
print(best_lambda)