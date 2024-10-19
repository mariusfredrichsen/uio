import numpy as np
import os

from Flow import Flow


def read_graph_from_file(file_path: str) -> np.ndarray:
    with open(file_path, 'r') as f:
        N = int(f.readline().strip())
        graph = np.zeros((N, N), dtype=int)
        for i in range(N):
            graph[i] = list(map(int, f.readline().strip().split()))
    return graph


class TestFlow:
    def test_flow_case1(self):
        graph = read_graph_from_file('../test/resources/assignment/testcase1.txt')
        print(graph)
        flow_solver = Flow(graph)
        max_flow = flow_solver.solve()
        assert max_flow == 24
        assert flow_solver.get_cut() == [0, 2]

    def test_flow_case2(self):
        graph = read_graph_from_file('../test/resources/assignment/testcase2.txt')
        flow_solver = Flow(graph)
        max_flow = flow_solver.solve()
        assert max_flow == 40
        assert flow_solver.get_cut() == [0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12]

    def test_flow_case3(self):
        graph = read_graph_from_file('../test/resources/assignment/testcase3.txt')
        flow_solver = Flow(graph)
        max_flow = flow_solver.solve()
        assert max_flow == 98921
        assert flow_solver.get_cut() == [0, 7, 10]

    def test_flow_case4(self):
        graph = read_graph_from_file('../test/resources/assignment/testcase4.txt')
        flow_solver = Flow(graph)
        max_flow = flow_solver.solve()
        assert max_flow == 84
        assert flow_solver.get_cut() == [0, 4, 5, 6, 7, 8, 9, 11, 13, 14, 15, 22, 25, 26, 27, 29, 31, 32, 34, 36, 37]
