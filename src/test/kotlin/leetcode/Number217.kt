package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Test217 {
    @Test
    fun test1() {
        assertTrue(  containsDuplicate(intArrayOf(1,2,3,1)) )
        assertFalse(  containsDuplicate(intArrayOf(1,2,3,4)) )
        assertTrue(  containsDuplicate(intArrayOf(1,1,1,3,3,4,3,2,4,2)) )
    }

}

fun containsDuplicate(nums: IntArray): Boolean {
    return nums.toSet().size != nums.size
}