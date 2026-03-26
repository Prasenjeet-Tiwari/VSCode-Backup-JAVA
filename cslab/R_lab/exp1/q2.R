# ------------------------------------------------------------
# Create a list containing student details
# ------------------------------------------------------------

student <- list(
  Name = "Rahul",
  Roll_No = 101,
  Marks = c(78, 85, 69, 90, 88),
  Status = "Pass"
)

# Display the complete list
student

# ------------------------------------------------------------
# Access each element of the list
# ------------------------------------------------------------

student$Name
student$Roll_No
student$Marks
student$Status

# ------------------------------------------------------------
# Modify the entire marks vector
# ------------------------------------------------------------

student$Marks <- c(80, 87, 72, 92, 90)
student$Marks

# ------------------------------------------------------------
# Update ONE specific mark
# R uses 1-based indexing
# Example: Update the 3rd subject mark to 75
# ------------------------------------------------------------

###############  student$Marks[3] <- 75
###############  student$Marks

# ------------------------------------------------------------
# Add a new element called GPA
# ------------------------------------------------------------

student$GPA <- 8.6

# Display the final updated list
student
