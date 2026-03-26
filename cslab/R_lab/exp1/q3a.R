# ------------------------------------------------------------
# Task 3 (A)
# Create a 3×3 matrix and compute:
# 1. Transpose
# 2. Determinant
# 3. Eigenvalues and Eigenvectors
# ------------------------------------------------------------

# Creating a 3×3 matrix using sequence (column-wise filling)
m <- matrix(1:9, nrow = 3, ncol = 3)
m

# ------------------------------------------------------------
# Another way to create a matrix using explicit values
# byrow = TRUE fills the matrix row-wise  for colum wise use "byrow=FALSE"
# ------------------------------------------------------------

n <- matrix(c(91, 92, 93,94, 95, 96, 97, 98, 99), nrow = 3, ncol = 3, byrow = TRUE)
n

# ------------------------------------------------------------
# Finding the dimension of matrix m
# Output format: (number of rows, number of columns)
# ------------------------------------------------------------


dim(m)

# ------------------------------------------------------------
# Transpose of matrix n
# Rows become columns and columns become rows
# ------------------------------------------------------------

m_t <- t(m)
m_t

# ------------------------------------------------------------
# Determinant of matrix n
# If determinant = 0, the matrix is singular
# ------------------------------------------------------------

m_d <- det(m)
m_d

# ------------------------------------------------------------
# Eigenvalues and Eigenvectors of matrix n
# $values  -> Eigenvalues
# $vectors -> Corresponding eigenvectors (column-wise)
# ------------------------------------------------------------

m_e <- eigen(n)
m_e


# ------------------------------------------------------------
# Taking matrix input from user using for loop
# ------------------------------------------------------------

# rows <- as.integer(readline("Enter number of rows: "))
# cols <- as.integer(readline("Enter number of columns: "))

# Create an empty matrix
# user_matrix <- matrix(0, nrow = rows, ncol = cols)

# Taking input element-wise
# for (i in 1:rows) {
#   for (j in 1:cols) {
#     user_matrix[i, j] <- as.numeric(
#       readline(paste("Enter element [", i, ",", j, "]: "))
#     )
#   }
# }

# Display the user-input matrix
# user_matrix
