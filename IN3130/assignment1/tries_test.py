from trie import Trie


class TestTries:

    def test_class1(self):
        trie = Trie()
        trie.insert('interweb')
        trie.insert('internet')
        trie.insert('inter')
        trie.insert('inside')

        assert trie.look_up('interweb')
        assert trie.look_up('inter')
        assert not trie.look_up('in')
        assert trie.print_tree() == '(in(side)(ter(net)(web)))'

    def test_class2(self):
        trie = Trie()
        trie.insert('internet')
        trie.insert('interview')
        trie.insert('internally')
        trie.insert('algorithm')
        trie.insert('all')
        trie.insert('web')
        trie.insert('world')

        assert not trie.look_up('inter')
        assert trie.look_up('all')
        assert not trie.look_up('al')
        assert trie.look_up('algorithm')
        assert not trie.look_up('w')
        assert not trie.look_up('interney')
        assert trie.print_tree() == '((al(gorithm)(l))(inter(n(ally)(et))(view))(w(eb)(orld)))'
