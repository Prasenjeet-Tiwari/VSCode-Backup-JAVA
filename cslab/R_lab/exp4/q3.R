# generate two datasets
set.seed(123)
data1 <- rnorm(100)
data2 <- rnorm(100)

# KS test
result <- ks.test(data1, data2)

# print result
print(result)