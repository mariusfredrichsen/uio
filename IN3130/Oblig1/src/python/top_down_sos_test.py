import numpy as np
import pandas as pd

from top_down_sos import TopDownSos


class TestTopDown:
    def test_1(self):
        top_down_sos = TopDownSos([1, 5, 6, 3, 10])

        # Test 1
        k1 = 11
        result1 = top_down_sos.check_sum(k1)
        assert result1 is not None
        assert np.sum(result1) == k1

        # Test 2
        k2 = 2
        result2 = top_down_sos.check_sum(k2)
        assert result2 is None

        # Test 3
        k3 = 14
        result3 = top_down_sos.check_sum(k3)
        assert result3 is not None
        assert np.sum(result3) == k3

    def test_2(self):
        top_down_sos = TopDownSos([2, 4, 6, 8, 10])

        k1 = 11
        assert top_down_sos.check_sum(k1) is None

        k2 = 12
        result2 = top_down_sos.check_sum(k2)
        assert result2 is not None
        assert np.sum(result2) == k2

        k3 = 20
        result3 = top_down_sos.check_sum(k3)
        assert result3 is not None
        assert np.sum(result3) == k3

    def test_3(self):
        top_down_sos = TopDownSos([1])

        k1 = 1
        result1 = top_down_sos.check_sum(k1)
        assert result1 is not None
        assert np.sum(result1) == k1

    def test_4(self):
        top_down_sos = TopDownSos([2])

        k1 = 2
        result1 = top_down_sos.check_sum(k1)
        assert result1 is not None
        assert np.sum(result1) == k1

        k2 = 1
        assert top_down_sos.check_sum(k2) is None

    def test_5(self):
        top_down_sos = TopDownSos([2, 3, 4, 5, 6, 7, 8, 9, 10])

        k1 = 1
        assert top_down_sos.check_sum(k1) is None

        k2 = 11
        result2 = top_down_sos.check_sum(k2)
        assert result2 is not None
        assert np.sum(result2) == k2

        k3 = 29
        result3 = top_down_sos.check_sum(k3)
        assert result3 is not None
        assert np.sum(result3) == k3

    def test_6(self):
        top_down_sos = TopDownSos([7, 7, 7, 7, 7, 7, 7])

        k1 = 7
        result1 = top_down_sos.check_sum(k1)
        assert result1 is not None
        assert np.sum(result1) == k1

        k2 = 21
        result2 = top_down_sos.check_sum(k2)
        assert result2 is not None
        assert np.sum(result2) == k2

        k3 = 2
        assert top_down_sos.check_sum(k3) is None

    def test_7(self):
        top_down_sos = TopDownSos([3, 2, 5, 11, 9, 19, 16])

        k1 = 17
        result1 = top_down_sos.check_sum(k1)
        assert result1 is not None
        assert np.sum(result1) == k1

        k2 = 6
        assert top_down_sos.check_sum(k2) is None
