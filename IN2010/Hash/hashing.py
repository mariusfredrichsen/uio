from collections import Counter
import svgwrite
from svgwrite.shapes import Circle

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

main()