package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class Test210 {
    @Test
    fun test1() {
        assertContentEquals(intArrayOf(0, 1), findOrder(2, arrayOf(intArrayOf(1, 0))))
    }

    @Test
    fun test2() {
        assertContentEquals(intArrayOf(0), findOrder(1, arrayOf()))
    }

    @Test
    fun test3() {
        assertContentEquals(intArrayOf(), findOrder(2, arrayOf(intArrayOf(0,1), intArrayOf(1, 0))))
    }

    @Test
    fun test4() {
        assertContentEquals(
            intArrayOf(0, 1, 2, 3), findOrder(
                4, arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(2, 0),
                    intArrayOf(3, 1),
                    intArrayOf(3, 2)
                )
            )
        )
    }

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val courseRequisiteMap =
            prerequisites.groupBy({ it[0] }, { it[1] }) // For this class, you must take these classes
        val courseRequiredBy = prerequisites.groupBy({ it[1] }, { it[0] }) // This class is required by

        val courseOrder = mutableListOf<Int>()
        while (courseOrder.size != numCourses) {
            val noRequirementClasses = (0 until numCourses).filter {
                !courseOrder.contains(it) && courseRequisiteMap.get(it)?.subtract(courseOrder).isNullOrEmpty()
            }.toMutableSet()
            if(noRequirementClasses.isEmpty()) {
                // we aren't done, but there are no no requirement classes
                return intArrayOf()
            }
            while (noRequirementClasses.isNotEmpty()) {
                val course = noRequirementClasses.first()
                noRequirementClasses.remove(course)
                courseOrder.add(course)
            }
        }
        return courseOrder.toIntArray()
    }
}
