# Create categorical vectors for 20 students
Gender <- c("Male", "Female", "Male", "Female", "Male",
            "Female", "Male", "Female", "Male", "Female",
            "Male", "Female", "Male", "Female", "Male",
            "Female", "Male", "Female", "Male", "Female")

Preference <- c("Online", "Offline", "Online", "Online", "Offline",
                "Offline", "Online", "Offline", "Online", "Online",
                "Offline", "Online", "Online", "Offline", "Offline",
                "Online", "Online", "Offline", "Online", "Offline")

# Convert to factors
Gender <- factor(Gender)
Preference <- factor(Preference)

# Joint frequency table
joint_table <- table(Gender, Preference)
cat("Joint Frequency Table:\n")
print(joint_table)

# Marginal distributions
cat("\nMarginal Distribution (Gender):\n")
print(margin.table(joint_table, 1))

cat("\nMarginal Distribution (Preference):\n")
print(margin.table(joint_table, 2))

# Relative frequency table
relative_table <- prop.table(joint_table)
cat("\nRelative Frequency Table:\n")
print(relative_table)