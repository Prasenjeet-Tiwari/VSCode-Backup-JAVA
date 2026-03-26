"first pytho n programs lets make it a better one"
print("welcome to lambda varient 1.1")
NAME_=input("PLEASE PROVIDE US WITH YOUR NAME: ")
PHONE_=input("PLEASE PROVIDE US WITH YOUR PHONE NUMBER: ")
COUNTER_=int(input("COULD YOU SPECIFY HOW MANY DATAS YOU WISH TO ENTER: "))
count=1
list_1=[]
def data_():
    stuname=input("PLEASE PROVIDE US WITH YOUR NAME: ")
    sturollno=input("PLEASE PROVIDE US WITH YOUR rollno: ")
    commit_=input(("Would you like to save the data just enter(y/n)"))
    p=[stuname,sturollno]
    if commit_=="y":
       list_1.append(p)
    else:
        print("this data has not been saved")
        trounle=input("wish to reenturn the data(y/n):")
        if trounle=="y":
            data_()
        
while count<=COUNTER_:
    data_()
    count+=1
print(list_1)