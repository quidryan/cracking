package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test88 {
    @Test
    fun test1() {
        val nums = intArrayOf(1, 2, 3, 0, 0, 0)
        merge(nums, 3, intArrayOf(2, 5, 6), 3)
        assertContentEquals(intArrayOf(1, 2, 2, 3, 5, 6), nums)
    }

    @Test
    fun test2() {
        val nums = intArrayOf(1)
        merge(nums, 1, intArrayOf(), 0)
        assertContentEquals(intArrayOf(1), nums)
    }

    @Test
    fun test3() {
        val nums = intArrayOf(0)
        merge(nums, 0, intArrayOf(1), 1)
        assertContentEquals(intArrayOf(1), nums)
    }

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var ptr1 = m - 1
        var ptr2 = n - 1
        for (idx in (m + n - 1) downTo 0) {
            // Work backwards
            if (ptr1 == -1) {
                // We only have ptr2 values
                nums1[idx] = nums2[ptr2]
                ptr2--
            } else if (ptr2 == -1) {
                // We only have ptr1 values
                nums1[idx] = nums1[ptr1]
                ptr1--
            } else {
                val val1 = nums1[ptr1]
                val val2 = nums2[ptr2]
                if (val1 > val2) {
                    nums1[idx] = val1
                    ptr1--
                } else {
                    nums1[idx] = val2
                    ptr2--
                }
            }
        }
    }
}
