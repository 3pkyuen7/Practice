import java.lang.Exception
//brute force, games, implementation
fun main() {
    // input : 0 || X || .
    // output : first, second, illegal, the first player won, the second player won or draw.
    var num1 = 0 //The first player draws X
    var num2 = 0 //The second player draws 0
    var spaceNum = 0 // space are '.'
    val table = arrayListOf(readLine()!!.toCharArray())
    var isFull = !table[0].any() { it == '.' }
    table.add(readLine()!!.toCharArray())
    isFull = isFull && !table[1].any() { it == '.' }
    table.add(readLine()!!.toCharArray())
    isFull = isFull && !table[2].any() { it == '.' }
    for (k in table.indices) {
        for (i in table[k].indices) {
            when {
                table[k][i] == 'X' -> {
                    num1++
                }
                table[k][i] == '0' -> {
                    num2++
                }
                table[k][i] == '.' -> {
                    spaceNum++
                }
            }
        }
    }
    //println("num1 : $num1, num2 : $num2")
    if (num1 - num2 != 0 && num1 - num2 != 1) {
        println("illegal")
        return
    }
    val checkChar = arrayOf('X', '0')
    var won = arrayOf(0, 0)
    for (c in 0..1) {
        for (i in 0..2) {
            if (checkChar[c] == table[i][0] && table[i][0] == table[i][1] && table[i][1] == table[i][2]) {
                won[c]++
            }
            if (checkChar[c] == table[0][i] && table[0][i] == table[1][i] && table[1][i] == table[2][i]) {
                won[c]++
            }
        }
        if (checkChar[c] == table[0][0] && table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
            won[c]++
        }
        if (checkChar[c] == table[0][2] && table[0][2] == table[1][1] && table[1][1] == table[2][0]) {
            won[c]++
        }
    }

    if (won[0] >= 1 && won[1] >= 1) {
        println("illegal")
        return
    } else if (won[0] >= 1) {
        if(num1 - num2 != 1){
            println("illegal")
            return
        }
        println("the first player won")
        return
    } else if (won[1] >= 1) {
        if(num1 - num2 != 0){
            println("illegal")
            return
        }
        println("the second player won")
        return
    }

    if (!isFull) {
        if (num1 - num2 == 0) {
            println("first")
        } else if (num1 - num2 == 1) {
            println("second")
        }
        return
    } else {
        println("draw")
    }
}