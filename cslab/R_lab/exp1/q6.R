# ------------------------------------------------------------
# Calculate the percentage of life spent at the institute
# Formula:
# Percentage = (2025 - StartYear) / (2025 - BirthYear) * 100
# ------------------------------------------------------------

BirthYear <- 2004     # enter your birth year
StartYear <- 2024     # year you joined the institute
CurrentYear <- 2025

percentage <- (CurrentYear - StartYear) /
  (CurrentYear - BirthYear) * 100

percentage
