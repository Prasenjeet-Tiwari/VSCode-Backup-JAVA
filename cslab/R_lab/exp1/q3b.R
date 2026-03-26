# ------------------------------------------------------------
# Create a data frame with Name, Age, and BP
# ------------------------------------------------------------

patients <- data.frame(
  Name = c("Amit", "Ravi", "Sita", "Neha", "Rahul"),
  Age  = c(45, 52, 36, 60, 48),
  BP   = c(120, 145, 138, 160, 125)
)

# Display the data frame
patients

# ------------------------------------------------------------
# Filter patients with BP > 130
# ------------------------------------------------------------

high_bp_patients <- patients[patients$BP > 130, ]
high_bp_patients

# ------------------------------------------------------------
# Add a BMI column
# (Assume BMI values are given or calculated separately)
# ------------------------------------------------------------

patients$BMI <- c(22.5, 27.8, 24.1, 30.2, 23.6)

# Display updated data frame
patients
