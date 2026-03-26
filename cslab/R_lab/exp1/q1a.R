# ------------------------------------------------------------
# Store names and marks of students in a data frame
# ------------------------------------------------------------

# Creating vectors for student names and marks
names <- c("Amit", "b", "c", "d")
marks <- c(10, 20, 30, 40)

# Creating a data frame                        
student_data <- data.frame(
  Name = names,
  Marks = marks
)

# ------------------------------------------------------------
# Display the data frame
# ------------------------------------------------------------

student_data

# ------------------------------------------------------------
# Calculate average marks
# ------------------------------------------------------------

average_marks <- mean(student_data$Marks)
average_marks

# ------------------------------------------------------------
# Draw a bar plot to visualize marks of each student
# ------------------------------------------------------------

barplot(
  student_data$Marks,
  names.arg = student_data$Name,
  main = "Marks of Students",
  xlab = "Students",
  ylab = "Marks",
  col = "skyblue"
)
