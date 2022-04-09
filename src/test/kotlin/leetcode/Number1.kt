package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test1 {
    @Test
    fun test1() {
        assertContentEquals(intArrayOf(0,1), twoSum(intArrayOf(2,7,11,15), 9))
        assertContentEquals(intArrayOf(0,2), twoSum(intArrayOf(2,3,4), 6))
        assertContentEquals(intArrayOf(0,1), twoSum(intArrayOf(-1,0), -1))
        assertContentEquals(intArrayOf(0,1), twoSum(intArrayOf(0,0,3,4), 0))
    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val indexMap = mutableMapOf<Int, Int>()
        for(i in numbers.indices) {
            val requiredNum = target - numbers[i]
            val lookup = indexMap[requiredNum]
            if(lookup != null) {
                return intArrayOf(lookup, i)
            }

            indexMap[numbers[i]] = i
        }
        throw Exception("No solution found")
    }
}
