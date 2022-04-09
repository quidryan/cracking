package leetcode

fun main(args: Array<String>) {
    assert( Solution(1).firstBadVersion(1) == 1 )
    assert( Solution(4).firstBadVersion(5) == 4 )
    assert( Solution(3).firstBadVersion(5) == 3 )
    assert( Solution(2).firstBadVersion(5) == 2 )
    assert( Solution(1).firstBadVersion(5) == 1 )
    assert( Solution(10).firstBadVersion(10) == 10 )
    assert( Solution(6).firstBadVersion(10) == 6 )
    assert( Solution(5).firstBadVersion(10) == 5 )
    assert( Solution(4).firstBadVersion(10) == 4 )
    assert( Solution(1702766719).firstBadVersion(2126753390) == 1702766719 )
}

/* The isBadVersion API is defined in the parent class VersionControl.
      fun isBadVersion(version: Int) : Boolean {} */

abstract class VersionControl(open val firstBad: Int) {
    fun isBadVersion(ver: Int) : Boolean {
        return !(ver < firstBad)
    }
    abstract fun firstBadVersion(n: Int) : Int
}

class Solution(override val firstBad: Int): VersionControl(firstBad) {
    override fun firstBadVersion(n: Int) : Int {
        return beforeUs(1, n)
    }

    tailrec fun beforeUs(lower:Int, upper: Int) : Int {
        val middle = (upper-lower)/2 + lower
        val nextToEachOther = lower == upper || lower == upper - 1 // middle will be lower number

        val badVersion = isBadVersion(middle)
        if(nextToEachOther) {
            return if(badVersion) lower else upper
        }
        val newLower = if(badVersion) lower else middle
        val newUpper = if(badVersion) middle else upper
        return beforeUs(newLower, newUpper)
    }
}