# Create data frame
patient_data <- data.frame(
  Name = c("Amit", "Riya", "John", "Sneha", "Rahul"),
  VisitDate = as.Date(c("2026-03-01", "2026-03-05", "2026-03-10", "2026-03-12", "2026-03-15")),
  BP = c(120, NA, 135, NA, 90)   # NA = missing values
)

# Display original data
cat("Original Data:\n")
print(patient_data)

# Replace missing BP values with mean
mean_bp <- mean(patient_data$BP, na.rm = TRUE)
patient_data$BP[is.na(patient_data$BP)] <- mean_bp

# User-defined function to classify BP
classify_bp <- function(bp) {
  if (bp < 90) {
    return("Low")
  } else if (bp <= 120) {
    return("Normal")
  } else {
    return("High")
  }
}

# Apply function to each BP value
patient_data$Category <- sapply(patient_data$BP, classify_bp)

# Display updated data
cat("\nUpdated Data:\n")
print(patient_data)