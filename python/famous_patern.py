a=int(input("please enter a digit:"))
i=1
while i<=a:
    print("#"*i)
    i+=1

#tower setting pattern

b=int(input("enter a odd number for the base of the tower:"))

k=" "
i=1
if b%2!=0:
    q=(b-1)//2
    while i <=b:
        print(k*q,"#"*i,k*q)
        i+=2
        q-=1
else:
    q=(b+1)//2
    while i <=b:
        print(k*q,"#"*i,k*q)
        i+=2
        q-=1
    





        
    