import heapq as h

def urgh(arr):
    if len(arr) > 1:
        i = len(arr)//2
        sl = []
        for j in range(i):
            h.heappush(sl, h.heappop(arr))
        print(h.heappop(arr))
        urgh(arr)
        urgh(sl)

    elif len(arr) == 1:
        print(h.heappop(arr))
        
urgh([0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15])