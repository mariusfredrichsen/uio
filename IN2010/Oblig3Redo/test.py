liste = [[1,2,3], [4,5,6], [7,8,9]]

for v in [i for j in liste for i in j]:
    print(v)

from time import time
    
def wrapper(func):
    # This function shows the execution time of  
    # the function object passed 
    def wrap_func(*args, **kwargs): 
        t1 = time() 
        result = func(*args, **kwargs) 
        t2 = time() 
        print(f'Function "{func.__name__}" executed in {(t2-t1):.4f}s') 
        return result 
    return wrap_func 

@wrapper
def printer():
    for i in range(100):
        print(i)

printer()