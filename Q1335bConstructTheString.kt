fun main() {
    for (i in 0 until readLine()!!.toInt()) {
        val input = readLine()!!.split(" ")
        val n = input[0].toInt()
        val a = input[1].toInt()
        val b = input[2].toInt()
        val maxCharIndex = if (a == b) {
            26
        } else {
            b
        }
        var charIndex = 1
        for (j in 0 until n) {
            print(toChar(charIndex))
            charIndex++
            if (charIndex > maxCharIndex) // back to a
                charIndex = 1
        }
        println()
    }
}

fun toInt(c: Char): Int {
    return c.toInt() - 96
}

fun toChar(i: Int): Char {
    return (i + 96).toChar()
}