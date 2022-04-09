import java.util.*

class Chapter1 {
    // Implement an algorithm to determine if a string has all unique characters
    // True if there are they are all unique
    fun OnePointOne(input: String): Boolean {
        val charArray = input.lowercase(Locale.getDefault()).toCharArray()
        val charSet = mutableSetOf<Char>()
        for (ch in charArray) {
            if (charSet.contains(ch)) {
                return false
            } else {
                charSet.add(ch)
            }
        }
        return true
    }

    // What if you cannot use additinal data structures?
    fun OnePointOneTwo(input: String): Boolean {
        return input.lowercase(Locale.getDefault()).toSet().size == input.length
    }

    // 1.3 Given two strings, write a method to decide if one is a permutation of the other
    fun OnePointThree(input1: String, input2: String): Boolean {
        return input1.toCharArray().sortedArray().contentEquals(input2.toCharArray().sortedArray())
    }

    // 1.4 Write a method to replace all spaces in a string siw `%20`, using a character array
    fun OnePointFour(input: String): String {
        val escaped = listOf('%', '2', '0')
        return input.toCharArray().flatMap { ch ->
            when (ch) {
                ' ' -> escaped
                else -> listOf(ch)
            }
        }.toCharArray().concatToString()
    }

    // Try to use character array
    fun OnePointFourTwo(input: String): String {
        val spaceCount = input.toCharArray().foldRight(0) { ch, acc ->
            when (ch) {
                ' ' -> acc + 1
                else -> acc
            }
        }

        val charArray = CharArray(input.length + spaceCount * 2)
        var idx = 0;
        for (ch in input) {
            when (ch) {
                ' ' -> {
                    charArray[idx] = '%'
                    charArray[++idx] = '2'
                    charArray[++idx] = '0'
                }
                else -> charArray[idx] = ch
            }
            idx++
        }
        return charArray.concatToString()
    }

    // 1.5 Implement basic string compression using the counts of repeated characters
    fun OnePointFive(input: String): String {
        var count = 0;
        var prevCh = input[0];
        val chArray = StringBuffer()
        for (ch in input) {
            if (ch != prevCh) {
                // Wrap up previous
                chArray.append(prevCh)
                chArray.append(count.toString())
                prevCh = ch
                count = 1
            } else {
                count++
            }
        }
        chArray.append(prevCh)
        if (count > 1) {
            chArray.append(count.toString())
        }
        val compressed = chArray.toString()

        return if (compressed.length > input.length) {
            input
        } else {
            compressed
        }
    }

    // 1.5 With a more functional approach
    data class CompressionChunk(val ch: Char, val count: Int = 1) {
        fun incr() : CompressionChunk = CompressionChunk(ch, count+1)
    }
    data class CompressionProgress(val established: List<CompressionChunk>, val working: CompressionChunk) {
        fun moveOn(ch: Char) : CompressionProgress = CompressionProgress(established.plus(working), CompressionChunk(ch))
        fun assemble() : String = established.plus(working).map { chunk -> chunk.ch + chunk.count.toString() }.joinToString("")
    }

    fun OnePointFiveTwo(input: String) : String {

        val progress = input.substring(1).fold(CompressionProgress(mutableListOf(), CompressionChunk(input[0]))) { prog, ch ->
            when (ch) {
                prog.working.ch -> CompressionProgress(prog.established, prog.working.incr())
                else -> prog.moveOn(ch)
            }
        }
        val compressed = progress.assemble()

        return if(compressed.length < input.length) {
            compressed
        } else {
            input
        }

    }
}