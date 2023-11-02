import random as rnd


SEED = 2010
OPS = [
    'insert',
    'insert',
    'insert',
    'insert',
    'remove',
    'remove',
    'contains',
    'size',
]


rnd.seed(SEED)


def rnd_x():
    return rnd.randint(0, 1000000)


def gen_input(N):
    print(N)
    s = set()
    for _ in range(N):
        cmd = rnd.choice(OPS)
        if cmd == 'insert':
            x = rnd_x()
            s.add(x)
            print(cmd, x)
        elif cmd == 'remove':
            x = rnd_x() if s and rnd.random() < 0.5 else rnd.choice(list(s))
            s.discard(x)
            print(cmd, x)
        elif cmd == 'contains':
            x = rnd_x() if s and rnd.random() < 0.5 else rnd.choice(list(s))
            print(cmd, x)
        elif cmd == 'size':
            print(cmd)


if __name__ == '__main__':
    gen_input(int(input()))
