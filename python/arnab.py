a=list()
a=[
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
]
while True:
    try:
        gap="0"
        o=input("Enter Date : ")
        if "/" in o:
            x,y,z=o.split("/")
            if int(x) <= 12 and int(y) <=31:
                if  len(x)!=2 or len(y)!=2:
                    
                    if len(x)!=2:
                        x=gap+x
                    if len(y)!=2:
                        y=gap+y
            print(z,x,y,sep="-")
        else:
            x,y,z=o.split()
            for i in a:
                if x==i:
                    month= a.index(i)+1
                    v,u=y.split(",")
                    if month<10:
                        month=gap + str(month)
                        month=gap + str(a.index(i)+1)
                    if len(v)!=2:
                        v= gap + v

                    print(z, month, v, sep="-")
    except EOFError:
        print("error occured: ")
        break