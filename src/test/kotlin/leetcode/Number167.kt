package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test167 {
    @Test
    fun test1() {
        assertContentEquals(intArrayOf(1,2), twoSum(intArrayOf(2,7,11,15), 9))
        assertContentEquals(intArrayOf(1,3), twoSum(intArrayOf(2,3,4), 6))
        assertContentEquals(intArrayOf(1,2), twoSum(intArrayOf(-1,0), -1))
        assertContentEquals(intArrayOf(1,2), twoSum(intArrayOf(0,0,3,4), 0))
    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for(i in numbers.indices) {
            for(j in i+1 until numbers.size) {
                println("{${i}, ${j}}")
                if(numbers[i]+numbers[j] == target)
                    return intArrayOf(i+1, j+1)
            }
        }
        throw Exception("No solution found")
    }
}
