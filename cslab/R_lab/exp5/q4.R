# Create data frame
StudyHours <- c(2, 3, 4, 5, 6, 3, 4, 5, 6, 7)
StudentType <- c("Hosteller", "DayScholar", "Hosteller", "DayScholar", "Hosteller",
                 "DayScholar", "Hosteller", "DayScholar", "Hosteller", "DayScholar")
Marks <- c(50, 55, 60, 65, 70, 58, 62, 67, 72, 75)

data <- data.frame(
  StudyHours = StudyHours,
  StudentType = factor(StudentType),
  Marks = Marks
)

# Display data
print(data)

# Fit regression model
model <- lm(Marks ~ StudyHours + StudentType, data = data)

# Show summary
summary(model)