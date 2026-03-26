# read CSV file
data <- read.csv("F:\\Visual Studio Codes\\cslab\\R_lab\\exp4\\marks.csv")

marks <- data$Marks

# quartiles
quartiles <- quantile(marks, probs = seq(0,1,0.25))
print("Quartiles:")
print(quartiles)

# deciles
deciles <- quantile(marks, probs = seq(0,1,0.1))
print("Deciles:")
print(deciles)

# boxplot
boxplot(marks,
        main="Boxplot of Student Marks",
        ylab="Marks",
        col="lightblue")