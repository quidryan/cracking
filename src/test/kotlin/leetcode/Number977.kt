package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test977 {
    @Test
    fun test1() {
        assertContentEquals(sortedSquares(intArrayOf(-4, -1, 0, 3, 10)), intArrayOf(0, 1, 9, 16, 100))
    }

}

fun sortedSquares(nums: IntArray): IntArray {
    return nums.map { i -> i * i }.sorted().toIntArray()
}