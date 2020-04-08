// hashing, implementation you may implement hashing later
fun main() {
    val playersNum = readLine()!!.toLong()
    val records = mutableListOf<Record>()
    val players = mutableListOf<Player>()
    for (i in 0 until playersNum) {
        val input = readLine()!!.split(" ")
        val name: String = input[0]
        var score: Long = input[1].toLong()
        if (players.any() { it.name == name }) {
            val player = players.first() { it.name == name }
            player.score += score
            score = player.score
        } else {
            players.add(Player(name, score))
        }
        records.add(Record(name, score, i))
    }
//    for (player in players){
//        println(player.name + " ${player.score}")
//    }
//    for(record in records){
//        println(record.name+" ${record.score} ${record.turn}")
//    }
    players.sortByDescending { it.score } // you may implement other sort algorithm
    val score = players[0].score
    val final = players.filter { it.score == score }
    if (final.size > 1) {
        for (k in 0 until records.size) {
            if (records[k].score >= score&&final.any(){records[k].name == it.name} ) {
                println(records[k].name)
                return
            }
        }
    } else {
        println(players[0].name)
    }
}

// Classes
class Player(val name: String, var score: Long) {
}

class Record(val name: String, val score: Long, val turn: Long) {
}