package connectfour

class Connect_Four {
    var p1 = ""
    var p2 = ""
    var size = ""
    var W = 0
    var H = 0
    init{
        start()
        inputSize()
        start_Info()
    }
    fun start() {
        println("Connect Four")
        println("First player's name:")
        p1 = readln()
        println("Second player's name:")
        p2 = readln()
    }
    fun inputSize() {
        input@ while (true) {
            println("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)")
            size = readLine()!!

            if (size.isNullOrEmpty()) {
                size = "6x7"
                H = size.first().toString().toInt()
                W = size.last().toString().toInt()
            } else {
                if (!"[\\s]*[\\d]+[\\s]*[Xx]+[\\s]*[\\d]+[\\s]*".toRegex().matches(size)) {
                    println("Invalid input")
                    continue@input
                }
                val parts = size.split("[xX]".toRegex()).map {
                    it.trim().toIntOrNull()
                }
                H = parts[0]!!
                W = parts[1]!!
            }
            if (!(H in 5..9)) {
                println("Board rows should be from 5 to 9")
            } else if (!(W in 5..9)) {
                println("Board columns should be from 5 to 9")
            } else {
                break@input
            }
        }
    }
    fun start_Info() {
        println(p1 + " vs " + p2)
        println("$H X " + W + " board")
    }
}

fun main() {
    val game = Connect_Four()
}