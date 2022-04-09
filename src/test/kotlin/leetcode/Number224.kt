package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Test224 {
    @Test
    fun test1() {
        assertEquals(2, calculate("1 + 1"))
    }

    @Test
    fun test2() {

        assertEquals(3, calculate(" 2-1 + 2 "))
    }

    @Test
    fun test3() {
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"))
    }

    @Test
    fun test4() {
        assertEquals(9, calculate("1+(4+5+2)-3"))
    }

    @Test
    fun test5() {
        assertEquals(2147483647, calculate("2147483647"))
    }

    @Test
    fun test6() {
        assertEquals(-1, calculate("-2+ 1"))
    }

    @Test
    fun test7() {
        assertEquals(-10, calculate("1-11"))
    }


}

fun calculate(line: String): Int {
    var pendingValue: Int? = null
    var pendingOp: Op? = null
    var idx = 0
    while (idx < line.length) {
        val ch = line[idx]
        when {
            ch == '-' -> pendingOp = Op.MINUS
            ch == '+' -> pendingOp = Op.PLUS
            ch == '*' -> pendingOp = Op.MULTIPLY
            ch == '/' -> pendingOp = Op.DIVIDE
            ch.isDigit() -> {
                // Gather digits
                var peekIdx = idx + 1
                var gather = Character.getNumericValue(ch)
                while(peekIdx < line.length && line[peekIdx].isDigit()) {
                    gather = gather * 10 + Character.getNumericValue(line[peekIdx++])
                    idx++
                }
                if (pendingOp == null) {
                    pendingValue = gather
                } else {
                    // Evaluate
                    pendingValue = evaluate(pendingValue, pendingOp, gather)
                    pendingOp = null
                }
            }
            ch == '(' -> {
                // Scan from end for other parents
                var openParens = 1
                var reverseIdx = idx+1
                while (openParens > 0) {
                    if(line[reverseIdx] == '(') {
                        openParens++
                    }
                    if(line[reverseIdx] == ')') {
                        openParens--
                    }
                    reverseIdx++
                }
                val substring = line.substring(idx + 1, reverseIdx-1)
                val subAnswer = calculate(substring)

                if(pendingOp != null) {
                    pendingValue = evaluate(pendingValue, pendingOp, subAnswer)
                    pendingOp = null
                } else {
                    pendingValue = subAnswer
                }
                idx = reverseIdx-1

            }
        }
        idx++
    }
//    println("${line} -> ${pendingValue!!}")
    return pendingValue!!
}

private fun evaluate(pendingValue: Int?, pendingOp: Op, chInt: Int): Int {
    if (pendingValue == null) {
        if(pendingOp == Op.MINUS) {
            return -1 * chInt
        }
        throw Exception("Bad state, no pendingValue")
    }

    return when (pendingOp) {
        Op.MINUS -> subtract(pendingValue, chInt)
        Op.PLUS -> add(pendingValue, chInt)
        Op.MULTIPLY -> multiply(pendingValue, chInt)
        Op.DIVIDE -> divide(pendingValue, chInt)
    }
}

fun add(left: Int, right: Int): Int = left + right
fun subtract(left: Int, right: Int): Int = left - right
fun multiply(left: Int, right: Int): Int = left * right
fun divide(left: Int, right: Int): Int = left / right

enum class Op {
    PLUS, MINUS, MULTIPLY, DIVIDE
}
