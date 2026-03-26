set.seed(1)

n<-1000;
lambda<-4;

# Generate Poisson data
x <- rpois(n, 4)

# Empirical probabilities
emp <- table(x)/1000

# Theoretical probabilities
k <- 0:max(x)
theo <- dpois(k, 4)

# Compare
data.frame(k, Empirical = emp[as.character(k)], Theoretical = theo)

par(mfrow=c(1,1))
# Plot
barplot(emp, col="yellow", main="Poisson Distribution")
points(k+1, theo, col="blue", pch=19)





# Summary:
# This program simulates Poisson distributed data with λ = 4 using rpois().
# Empirical probabilities are computed from simulated data using table().
# Theoretical probabilities are calculated using the Poisson pmf with dpois().
# A comparison table is created to observe how simulated probabilities
# approach theoretical values. Finally, a bar plot visualizes the empirical
# distribution and red points show the theoretical probabilities.