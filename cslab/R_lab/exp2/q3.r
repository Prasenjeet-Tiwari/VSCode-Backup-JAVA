# Create data frame
exam <- data.frame(
  Name = c("Appple", "Morty", "Rick", "Henry"),
  Marks = c(22, 57, 99, 92),
  ExamDate = as.Date(c("2024-05-01","2024-05-01",
                       "2024-05-01","2024-05-01"))
)

# Export to CSV
write.csv(exam, "students.csv", row.names = FALSE)

# Import CSV
new_data <- read.csv("students.csv")

# Summary statistics
summary(new_data$Marks)

# Histogram
hist(new_data$Marks,
     main="Histogram of Marks",
     xlab="Marks")