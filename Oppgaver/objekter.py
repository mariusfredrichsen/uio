class Liste:
    def __init__(self):
        self._liste = []

    def append(self, element):
        self._liste.append(element)

    def __contains__(self, element):
        if self._liste.count(element) > 0:
            return True
        return False

    def __getitem__(self, element):
        return self._liste[element]

    def __repr__(self):
        return f"[{self._liste[0]},{self._liste[1]},{self._liste[2]}]"

l = Liste()
l.append(1)
l.append("Hei")
l.append(3)
assert 3 in l
assert 2 not in l
assert l[0] == 1
assert l[1] == "Hei"
print(str(l))  # skal gi [1, "Hei", 3]  eller lignende