package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test189 {
    @Test
    fun test1() {
        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        rotate(nums, 1)
        assertContentEquals(intArrayOf(7, 1, 2, 3, 4, 5, 6), nums)
    }

    @Test
    fun test2() {

        val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        rotate(nums, 3)
        assertContentEquals(intArrayOf(5, 6, 7, 1, 2, 3, 4), nums)
    }

    @Test
    fun test3() {

        val nums = intArrayOf(-1, -100, 3, 99)
        rotate(nums, 2)
        assertContentEquals(intArrayOf(3, 99, -1, -100), nums)
    }

    @Test
    fun test4() {

        val nums = intArrayOf(-1)
        rotate(nums, 2)
        assertContentEquals(intArrayOf(-1), nums)
    }

}

fun rotate(nums: IntArray, k: Int) : Unit {
    if(k == 0) {
        return
    }
    val rotations = k % nums.size
    val saved = nums.sliceArray((nums.size - rotations) until nums.size)
    for (i in nums.size - 1 downTo rotations) {
        nums[i] = nums[i - rotations]
    }
    for(j in saved.indices) {
        nums[j] = saved[j]
    }

}

fun rotateRecursive(nums: IntArray, k: Int): Unit {
    if(k == 0) {
        return
    }
    val saved = nums.last()
    for (i in nums.size - 1 downTo 1) {
        nums[i] = nums[i - 1]
    }
    nums[0] = saved

    rotate(nums, k - 1)
}
