def o(s):
    l=len(s) #length of array
    return len(set([a+b+c
                    for a in s for b in s for c in s])
               )==l*(l+1)*(l+2)//6
M=int(input())
N=3**M #3 to the power of M
i=1 
s=M*[i] #create an array of width M, init with 1's
while i:
    if s[i]-N: #if value at i > 3^M
        s[i]=s[i]+1 #add 1 to the value
        if o(s[:i+1]): #from 0 to i+1
            if i<M-1:
                s[++i]=s[i-1] #copy value up 1 memory spot
            else:
                N=s[-1] #N = value of last item
                print N
    else:
        i=i-1
print(N)