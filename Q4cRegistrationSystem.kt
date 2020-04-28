import java.lang.Exception
import kotlin.math.pow

//  data structures, hashing, implementation, *1500
fun main() {
    val map = Q4cDictionary<String, Int>(12345678)//(1234567)
    for (i in 0 until readLine()!!.toInt()) {
        val id = readLine()!!
        if (map.containsKey(id)) {
            map[id] = map[id] + 1
            val newId: String = id + map[id]
            println(newId)
        } else {
            println("OK")
            map.putIfAbsent(id, 0)
        }
    }
}

class Q4cDictionary<K, V>(private val size: Int) {
    private val storage = arrayOfNulls<ArrayList<Q4cRecord>>(size)
    fun containsKey(id: K): Boolean {
        val index = getIndex(id)
        if (storage[index] != null) {
            return storage[index]!!.any() { it.data == id.toString() }
        }
        return false
    }

    fun putIfAbsent(id: K, count: V) {
        val index = getIndex(id)
        if (containsKey(id)) {
            storage[index]!!.first() { it.data == id.toString() }.count += 1
        } else {
            if(storage[index] == null){
                storage[index] = arrayListOf(Q4cRecord(id.toString()))
            }else{
                storage[index]!!.add(Q4cRecord(id.toString()))
            }
        }
    }

    operator fun get(id: K): Int {
        val index = getIndex(id)
        if (storage[index] != null) {
            return storage[index]!!.first() { it.data == id }.count
        }
        return -1   // means error
    }

    operator fun set(id: K, v: V) {
        val index = getIndex(id)
        if (!containsKey(id)) {
            throw Exception("no such element")
        } else {
            storage[index]!!.first() { it.data == id }.count = v as Int
        }
    }

    // implementation of map
    private fun getIndex(id: K): Int { // are all lowercase Latin letters.
        val chars = id.toString().toCharArray()
        var index: Long = 0
        for (i in 1..chars.size) {
            index += toInt(chars[i - 1]).toDouble().pow(i).toInt()
        }
        return (index % size).toInt()
    }

    private fun toInt(char: Char): Int {
        return char.toInt() - 96
    }

    private fun toChar(int: Int): Char {
        return (int + 96).toChar()
    }
}

// make your own hash map here
// it is a chained hashmap so list is used

class Q4cRecord(val data: String, var count: Int = 0) {
}