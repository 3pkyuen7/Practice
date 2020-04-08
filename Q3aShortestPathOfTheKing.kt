// greedy, shortest paths
fun main() {
    val s = readLine()!!.toCharArray() // sample : a8
    val d = readLine()!!.toCharArray() // sample : h1
    var hori = s[0] - d[0]
    var vert = s[1] - d[1]
    if (hori >= 0 && vert >= 0) { // a8 - h1 = 7 fin, it includes both h and v are 0
        if (hori > vert) {
            println(hori)
        } else {
            println(vert)
        }
    } else if (hori <= 0 && vert >= 0) {
        if (-hori > vert) {
            println(-hori)
        } else {
            println(vert)
        }
    } else if (hori >= 0 && vert <= 0) {
        if (hori > -vert) {
            println(hori)
        } else {
            println(-vert)
        }
    } else { // hori and vert are negative
        if (-hori < -vert) {
            println(-vert)
        } else {
            println(-hori)
        }
    }
    while (hori != 0 || vert != 0) { // add or sub until equals to 0
        //println("$hori $vert")
        if (hori < 0) {
            print('R')
            hori++
        } else if (hori > 0) {
            print('L')
            hori--
        }
        if (vert < 0) {
            print('U')
            vert++
        } else if (vert > 0) {
            print('D')
            vert--
        }
        println()
    }// sample output : 7\n (RD\n)*7
}