from collections import Counter
import svgwrite
from svgwrite.shapes import Circle
import hypothesis as hyp
import hypothesis.strategies as st

class MyMap:
    GYLDNE_SNITT = 0.61
    
    def __init__(self):
        self.n = 0
        self.N = 1
        self.A = [None] * self.N
    
    def __loadfactor(self):
        return self.n / self.N
    
    def __rehash(self):
        kvs = [(k,v) for k, v in self]
        self.n = 0
        self.N *= 2
        self.A = [None] * self.N
        for k, v in kvs:
            self[k] = v
    
    def ensurecapacity(self):
        if self.__loadfactor() >= self.GYLDNE_SNITT:
            self.__rehash()
    
    def __repr__(self):
        kv_strs = [f'{k} ↦ {v}' for k, v in self]
        return '{' + ', '.join(kv_strs) + '}'
        
class SeparateChainingMap(MyMap):
    def __setitem__(self, k, v):
        self.ensurecapacity()
        
        i = hash(k) % self.N
        
        if self.A[i] == None:
            self.A[i] = []
        
        bucket = self.A[i]
        
        for j in range(len(bucket)):
            kj, vj = bucket[j]
            if kj == k:
                bucket[j] = (k,v)
                return
        
        self.n += 1
        bucket.append((k,v))
    
    def __getitem__(self, k):
        i = hash(k) % self.N
        bucket = self.A[i]
        
        if bucket == None:
            return
        
        for ki, v in bucket:
            if ki == k:
                return v
            
    def __delitem__(self, k):
        i = hash(k) % self.N
        bucket = self.A[i]
        
        if bucket == None:
            return None
        
        self.A[i] = [(ki, v) for ki, v in bucket if ki != k]
        
    def __iter__(self):
        for b in self.A:
            if b:
                for kv in b:
                    yield kv
            
class LinearProbingMap(MyMap):
    def __setitem__(self, k, v):
        self.ensurecapacity()
        i = hash(k) % self.N
            
        while self.A[i]:
            if self.A[i][0] == k:
                self.A[i] = (k,v)
                return
            i = (i + 1) % self.N
        
        self.A[i] = (k,v)
        self.n += 1
    
    def __delitem__(self, k):
        i = hash(k) % self.N
        
        while self.A[i]:
            ki, v = self.A[i]
            if ki == k:
                self.n -= 1
                self.A[i] = None
                self.__fillhole(i)
                return
            i = (i + 1) % self.N
    
    def __fillhole(self, i):
        s = 1
        while self.A[(i + s) % self.N]:
            k, v = self.A[(i + s) % self.N]
            j = hash(k) % self.N
            if not(0 < (j - i) % self.N <= s):
                self.A[i] = (k, v)
                self.A[(i + s) % self.N] = None
                self.__fillhole((i + s) % self.N)
                return
            s += 1
        
    def __iter__(self):
        for e in self.A:
            if e:
                yield e

def hash_string(s, N):
    h = 0
    for c in s:
        h = 31 * h + ord(c)
    return h % N

def hash_distribution(hashfn, N, strings):
    return Counter([hashfn(s, N) for s in strings])

def draw_distribution(dist, N):
    s = int(N**0.5)
    r = 0.5
    svg = svgwrite.Drawing(size=(s, s))
    svg.viewbox(0, 0, s, s)
    m = dist.most_common(1)[0][1]
    for y in range(s):
        for x in range(s):
            k = x + y * s
            opacity = dist[k]/m
            svg.add(Circle((x + r, y + r), r,
                           fill = 'purple',
                           fill_opacity = opacity))
    return svg

def drawhashfunctions(Ns, hashfunctions, strings):
    for N in Ns:
        for hashfn in hashfunctions:
            name = hashfn.__name__ + '_' + str(N) + '.svg'
            dist = hash_distribution(hashfn, N, strings)
            svg = draw_distribution(dist, N)
            svg.saveas(name)
            
def main():
    with open('words.txt', 'r', encoding='utf8') as f:
        words = [line.strip() for line in f]
    drawhashfunctions([100, 400, 1024, 2500, 4096], [hash_string], words)

def test():
    # d = SeparateChainingMap()
    d = LinearProbingMap()
    d['a'] = 0
    print(d)
    d['b'] = 1
    print(d)
    d['c'] = 2
    print(d)
    d['d'] = 3
    print(d)
    del d['a']
    print(d)
    del d['b']
    print(d)
    del d['c']
    print(d)
    del d['d']
    print(d)
    
# main()
# test()

@hyp.given(st.lists(st.integers()))
@hyp.settings(max_examples=1000)
def test_hashmaps(keys):
    ds = SeparateChainingMap()
    dl = LinearProbingMap()
    reference = dict()

    for key in keys:
        ds[key] = 1
        dl[key] = 1
        reference[key] = 1
        ds_ks = set(k for k, v in ds)
        dl_ks = set(k for k, v in dl)
        reference_ks = set(reference.keys())
        assert ds_ks == dl_ks == reference_ks

    for key in keys:
        del ds[key]
        del dl[key]
        if key in reference_ks:
            del reference[key]
        ds_ks = set(k for k, v in ds)
        dl_ks = set(k for k, v in dl)
        reference_ks = set(reference.keys())
        assert ds_ks == dl_ks == reference_ks

test_hashmaps()