


################################
# Create data frame
students <- data.frame(
  Name = c("Amit", "Riya", "Karan", "Sneha", "Rahul"),
  Marks = c(85, 92, NA, 76, 88)
)

# Handle missing values (replace NA with 0)
students$Marks[is.na(students$Marks)] <-  0

#na.rm = TRUE ignores na value
# Calculate average marks
avg_marks <- mean(students$Marks, na.rm = TRUE)
print(avg_marks)

# User defined function
result_fun <- function(marks) {
  if (marks >= 85)
    return("Distinction")
  else if (marks >= 40)
    return("Pass")
  else
    return("Fail")
}

# Apply function
students$Result <- sapply(students$Marks, result_fun)

print(students)