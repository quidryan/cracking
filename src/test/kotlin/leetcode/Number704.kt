package leetcode

fun main(args: Array<String>) {
    assert( search(intArrayOf(1, 3, 5, 6), 5) == 2)
    assert( search(intArrayOf(1, 3, 5, 6), 2) == -1)
    assert( search(intArrayOf(1, 3, 5, 6), 7) == -1)
    assert( search(intArrayOf(1, 3, 5, 6), 0) == -1)
    assert( search(intArrayOf(1, 3, 5, 6), 1) == 0)
    assert( search(intArrayOf(1, 3), 0) == -1)
    assert( search(intArrayOf(1, 3), 1) == 0)
    assert( search(intArrayOf(1, 3), 3) == 1)
}

fun search(nums: IntArray, target: Int): Int = searchSub(nums, 0, nums.size - 1, target)

fun searchSub(nums: IntArray, left: Int, right: Int, target: Int): Int {
    if(right<left) {
        return -1
    }

    val middle = (right-left)/2 + left
    val v = nums[middle]
    return when {
        target == v -> middle
        target < v -> searchSub(nums, left, middle-1, target)
        else -> searchSub(nums, middle+1, right, target)
    }
}
