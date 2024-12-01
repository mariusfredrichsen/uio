


a = [9,4,5,3,2,8,0,1]


n = len(a)
for i in range(n-1):
    for j in range(n-i-1):
        if a[j] > a[j+1]:
            a[j], a[j+1] = a[j+1], a[j]

print(a)

#tanken er at man drasser med seg det største tallet helt til høyre i første iterasjon
#dermed trenger man ikke å iterere med den innerste for loopen helt til høyre gitt at tallet på slutten skal være der