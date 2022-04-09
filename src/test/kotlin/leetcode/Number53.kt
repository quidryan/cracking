package leetcode

import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

internal class Test53 {
    @Test
    fun test1() {
        assertEquals(1, maxSubArray(intArrayOf(1)))
        assertEquals(6, maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
        assertEquals(23, maxSubArray(intArrayOf(5, 4, -1, 7, 8)))
    }

}

// Dynamic
fun maxSubArray(nums: IntArray): Int {
    var current = nums[0]
    var maximum = nums[0]

    for(idx in 1 until nums.size) {
        val num = nums[idx]
        current = max(num, current+num)
        maximum = max(maximum, current)
    }
    return maximum
}

// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//A subarray is a contiguous part of an array.
fun maxSubArraySlow(nums: IntArray): Int {
    var maximum = nums[0]
    for (i in nums.indices) {
        var intermediateSum = 0
        for (j in i until nums.size) {
            intermediateSum += nums[j]
            maximum = max(maximum, intermediateSum)

        }
    }
    return maximum
}

fun maxSubArrayDivideAndConqueu(nums: IntArray): Int {
    return maxSumSub(nums, 0, nums.size - 1)
}

fun maxSumSub(nums: IntArray, left: Int, right: Int): Int {
    if (left == right) {
        return nums[left]
    }
    println("Considering ${nums.slice(left..right)}")
    val mid = (left + right) / 2
    val leftSum = maxSumSub(nums, left, mid)
    val rightSum = maxSumSub(nums, mid + 1, right)
    val crossing = maxSumCrossing(nums, left, mid, right)

    return max(leftSum, max(crossing, rightSum))
}

fun maxSumCrossing(nums: IntArray, left: Int, mid: Int, right: Int): Int {
    var runningSum = nums[mid]
    var maxLeft = nums[mid]
    for(idx in mid-1 downTo left) {
        runningSum += nums[idx]
        maxLeft = max(maxLeft, runningSum)
    }

    runningSum = nums[mid+1]
    var maxRight = nums[mid+1]
    for(idx in mid+2 .. right) {
        runningSum += nums[idx]
        maxRight = max(maxRight, runningSum)
    }

    return max( maxLeft+maxRight, max(maxLeft, maxRight))
}