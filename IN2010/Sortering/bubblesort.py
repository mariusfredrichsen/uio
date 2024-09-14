


a = [9,4,5,3,2,8,0,1]
n = len(a)
for i in range(n-1):
    for l in range(n-i-1):
        if a[l] > a[l+1]:
            # her bytter man mellom l og l+1
            a[l], a[l+1] = a[l+1], a[l]

print(a)

#tanken er at man drasser med seg det største tallet helt til høyre i første iterasjon
#dermed trenger man ikke å iterere med den innerste for loopen helt til høyre gitt at tallet på slutten skal være der