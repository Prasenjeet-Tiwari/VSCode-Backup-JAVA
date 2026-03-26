# ------------------------------------------------------------
# Generate 100 normally distributed random numbers
# and plot them
# ------------------------------------------------------------

x <- rnorm(100)

plot(
  x,
  type = "o",
  main = "Plot of 100 Normally Distributed Random Numbers",
  xlab = "Index",
  ylab = "Value"
)
