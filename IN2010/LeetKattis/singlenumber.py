class Solution:
    def singleNumber(self, nums) -> int:
        appeared_once = []
        for i in range(len(nums)):
            if nums[i] not in appeared_once:
                appeared_once.append(nums[i])
            else:
                appeared_once.remove(nums[i])
        return appeared_once[0]

