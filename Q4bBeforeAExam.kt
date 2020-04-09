//constructive algorithms, greedy, 1500
fun main() {
    val input = readLine()!!.split(" ");
    val d = input[0].toInt()
    var h = input[1].toInt()
    val minArray = ArrayList<Int>()
    val difArray = ArrayList<Int>()
    var min: Int = 0
    var max: Int = 0
    var output: String = ""
    for (i in 0 until d) {
        val input1 = readLine()!!.split(" ")
        val int1 = input1[0].toInt() // min of d[i]
        val int2 = input1[1].toInt() // max of d[i]
        minArray.add(int1)
        difArray.add(int2 - int1)
        min += int1
        max += int2
    }
    if (h in min..max) {
        println("YES")
        var totalDiff = h - min
        for (k in 0 until d) {
            when {
                totalDiff == 0 -> {
                    print("${minArray[k]} ")
                }
                difArray[k] > totalDiff -> {
                    print("${minArray[k] + totalDiff} ")
                    totalDiff = 0
                }
                else -> { // totalDiff >= diffArray[k]
                    print("${minArray[k] + difArray[k]} ")
                    totalDiff -= difArray[k]
                }
            }
        }
    } else {
        println("NO")
    }
}