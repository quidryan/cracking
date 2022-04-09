package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test283 {
    @Test
    fun test1() {
        val nums = intArrayOf(0, 1, 0, 3, 12)
        moveZeroes(nums)
        assertContentEquals(intArrayOf(1,3,12,0,0), nums)
    }

    fun moveZeroes(nums: IntArray): Unit {
        var back = 0
        for(idx in nums.indices) {
            if(nums[idx]!=0) {
                nums[back] = nums[idx]
                back++
            }
        }
        for(idx in back until nums.size) {
            nums[idx] = 0
        }
    }
}
