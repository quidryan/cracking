package leetcode

fun main(args: Array<String>) {
    assert( searchInsert(intArrayOf(1, 3, 5, 6), 5) == 2)
    assert( searchInsert(intArrayOf(1, 3, 5, 6), 2) == 1)
    assert( searchInsert(intArrayOf(1, 3, 5, 6), 7) == 4)
    assert( searchInsert(intArrayOf(1, 3, 5, 6), 0) == 0)
    assert( searchInsert(intArrayOf(1, 3), 0) == 0)
}

fun searchInsert(nums: IntArray, target: Int): Int = searchInsertSub(nums, 0, nums.size - 1, target)

fun searchInsertSub(nums: IntArray, left: Int, right: Int, target: Int): Int {
    val middle = (left + right) / 2
    val v = nums[middle]
    if (left == right) {
        if(v<target) {
            return left + 1
        } else {
            return left
        }
    }
    if( left == right - 1) {
        // Down to two numbers
        if(target > v) {
            return searchInsertSub(nums, right, right, target)
        } else {
            return left
        }
    }
    if (v == target) {
        return middle
    }
    if (v < target) {
        // Look at right
        return searchInsertSub(nums, middle + 1, right, target)
    } else {
        return searchInsertSub(nums, left, middle - 1, target)
    }
}
