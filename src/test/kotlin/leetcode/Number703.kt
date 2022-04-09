package leetcode

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.test.assertEquals

internal class Test703 {
    @Test
    fun test1() {
        val kthLargest = KthLargest(3, intArrayOf(4, 5, 8, 2));
        assertEquals(4, kthLargest.add(3))
        assertEquals(5, kthLargest.add(5))
        assertEquals(5, kthLargest.add(10))
        assertEquals(8, kthLargest.add(9))
        assertEquals(8, kthLargest.add(4))
    }
}

class KthLargest(val k: Int, val nums: IntArray) {

    val list = PriorityQueue<Int>(k)

    init {
        nums.forEach {
            add(it)
        }
    }

    fun add(v: Int): Int {
        list.offer(v)
        if(list.size > k) {
            list.poll()
        }
        return list.peek()
    }

}