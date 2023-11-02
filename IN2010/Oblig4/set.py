class Set:
    GYLDNE_SNITT = 0.61
    
    def __init__(self):
        self.n = 0
        self.N = 1
        self.A = [None] * self.N
    
    def size_ratio(self):
        return self.n / self.N
    
    def ensure_size(self):
        if self.size_ratio() > self.GYLDNE_SNITT:
            self.rehash()
        
    def rehash(self):
        kvs = [(k, v) for k, v in self]
        self.n = 0
        self.N *= 2
        self.A = [None] * self.N
        
        for k, v in kvs:
            self[k] = v
    
    def __iter__(self):
        for bucket in self.A:
            if bucket:
                for kv in bucket:
                    yield kv
    
    def __getitem__(self, k):
        i = hash(k) % self.N
        bucket = self.A[i]
        
        if not bucket:
            return 
        
        for ki, v in bucket:
            if ki == k:
                return v
    
    def __setitem__(self, k, v):
        self.ensure_size()
        
        i = hash(k) % self.N
        if not self.A[i]:
            self.A[i] = []
        
        bucket = self.A[i]
        
        for j in range(len(bucket)):
            ki, _ = bucket[j]
            if ki == k:
                bucket[j] = (k, v)
                return
        
        self.n += 1
        bucket.append((k, v))
    
    def __delitem__(self, k):
        i = hash(k) % self.N
        bucket = self.A[i]
        
        if bucket == None:
            return
        elif bucket == []:
            return
        
        n = len(bucket)
        self.A[i] = [(ki, v) for ki, v in bucket if ki != k]
        self.n += (len(self.A[i])-n)
    
    def size(self):
        return self.n

def contains(set, x):
    if set[x]:
        print('true')
    else:
        print('false')
    
def insert(set, x):
    set[str(x)] = x

def remove(set, x):
    del set[str(x)]

def size(set):
    print(set.size())

from time import time

def main():
    t = time()
    n = int(input())
    set = Set()
    
    for i in range(n):
        inn = input().strip().split()
        if len(inn) == 1:
            command = inn[0]
        else:
            command, num = inn
        
        if command == "contains":
            contains(set, num)
        if command == "insert":
            insert(set, num)
        if command == "remove":
            remove(set, num)
        if command == "size":
            size(set)
    print(time() - t)
main()
        
        