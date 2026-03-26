# Create data frame
data <- data.frame(
  RollNo = 1:5,
  Marks = c(45, 67, NA, 30, 90)
)

# Replace missing values with mean
data$Marks[is.na(data$Marks)] <- 
  mean(data$Marks, na.rm = TRUE)

# Basic statistics
mean_marks <- mean(data$Marks)
max_marks <- max(data$Marks)
min_marks <- min(data$Marks)

print(mean_marks)
print(max_marks)
print(min_marks)

# Function for pass/fail
check_pass <- function(m) {
  if (m >= 40)
    return("Pass")
  else
    return("Fail")
}

data$Result <- sapply(data$Marks, check_pass)

print(data)