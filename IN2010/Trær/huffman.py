




class Node:
    def __init__(self, symbol, freq, left, right):
        self.symbol = symbol
        self.freq = freq
        self.left = left
        self.right = right
    
    def __gt__(self, v):
        return self.freq > v.freq
    
    def __eq__ (self, v):
        return self.freq == v.freq

class PrioQueue:
    def __init__(self):
        self.queue = []
    
    def insert(self, v):
        self.queue.append(v)
        i = len(self.queue)-1
        j = (i - 1) // 2
        while i > 0 and self.queue[i] < self.queue[j]:
            self.queue[i], self.queue[j] = self.queue[j], self.queue[i]
            i = j
            j = (i - 1) // 2
    
    def size(self):
        return len(self.queue)
    
    def remove(self):
        out = self.queue[0]
        self.queue[0] = self.queue.pop()
        i = 0
        while i * 2 + 2 < len(self.queue) - 1:
            left = i * 2 + 1
            right = i * 2 + 2
            j = left if self.queue[left].freq <= self.queue[right].freq else right
            if self.queue[j] > self.queue[i]:
                break
            self.queue[i], self.queue[j] = self.queue[j], self.queue[i]
            i = j
        left = i * 2 + 1
        right = i * 2 + 2
        if left < len(self.queue) - 1 and self.queue[left].freq <= self.queue[i].freq:
            self.queue[i], self.queue[left] = self.queue[left], self.queue[i]
        return out
        
def huffman(C):
    Q = PrioQueue()
    for s in C:
        Q.insert(Node(s, C[s], None, None))
    while Q.size() > 1:
        v1 = Q.remove()
        v2 = Q.remove()
        f = v1.freq + v2.freq
        Q.insert(Node(None, f, v1, v2))
    return Q.remove()

def main():
    sentence = "en fin setning knut og kan knut holtet si noe mer og abdi liker jule treet"
    s_freq = dict()
    
    for l in sentence:
        if l not in s_freq:
            s_freq[l] = 1
        else:
            s_freq[l] += 1
    
    root = huffman(s_freq)
    
    print(root)


main()

#funker ikke as