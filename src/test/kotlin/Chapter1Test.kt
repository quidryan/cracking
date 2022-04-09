import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Chapter1Test {

    private val ut: Chapter1 = Chapter1()

    @Test
    fun onePointOne() {
        assertTrue(ut.OnePointOne("abc"))
        assertFalse(ut.OnePointOne("abca"))
    }

    @Test
    fun onePointOneTwo() {
        assertTrue(ut.OnePointOneTwo("abc"))
        assertFalse(ut.OnePointOneTwo("abca"))
    }

    @Test
    fun onePointThree() {
        assertTrue(ut.OnePointThree("test", "ttes"))
        assertFalse(ut.OnePointThree("test", "ttew"))
        assertTrue(ut.OnePointThree("apple", "plepa"))
        assertFalse(ut.OnePointThree("apple", "test"))
    }

    @Test
    fun onePointFour() {
        assertEquals("The%20web%20is%20big", ut.OnePointFour("The web is big"))
    }

    @Test
    fun onePointFourTwo() {
        assertEquals("The%20web%20is%20big", ut.OnePointFourTwo("The web is big"))
    }

    @Test
    fun onePointFive() {
        assertEquals("a2b1c5a3", ut.OnePointFive("aabcccccaaa"))
        assertEquals("abca", ut.OnePointFive("abca"))
    }

    @Test
    fun onePointFiveTwo() {
        assertEquals("a2b1c5a3", ut.OnePointFiveTwo("aabcccccaaa"))
        assertEquals("abca", ut.OnePointFiveTwo("abca"))
    }
}