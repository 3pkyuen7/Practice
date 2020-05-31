fun main() {
    for (i in 0 until readLine()!!.toInt()) {
        readLine()!!.split(" ").also { println(it[0].toInt() + it[1].toInt()) }
    }
}