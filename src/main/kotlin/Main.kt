package connectfour
enum class pplayer(var name1:String,val sign:Char){
    p1("p1",'o'),
    p2("p2",'*')
}
var board1 = mutableListOf(
    mutableListOf<Char>('A','A','A','A','A','A','A','A','A',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
    mutableListOf<Char>(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',),
)
fun turn(n :Int):pplayer{
    if(n%2==0)return pplayer.p1
    else return pplayer.p2
}
class Connect_Four {
    var size = ""
    var W = 0
    var H = 0
    var board = mutableListOf(mutableListOf<Char>(' '),)
    var n1 = mutableListOf<Int>()
    init{
        start()
        inputSize()
        start_Info()
        createBoard()
        temporPrint()
        gameLogic()
        println("Game over!")
    }
    fun start() {
        println("Connect Four")
        println("First player's name:")
        pplayer.p1.name1 = readln()
        println("Second player's name:")
        pplayer.p2.name1 = readln()
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
        println(pplayer.p1.name1 + " vs " + pplayer.p2.name1)
        println("$H X " + W + " board")
    }
    fun createBoard(){
        val a1 = mutableListOf<Char>()
        for ( a  in 0..W){
            a1.add(a,'E')
        }
        for ( a  in 0..W){
            board.add(a,a1)
            n1.add(a,H)
        }
    }
    fun gameLogic(){
        var n =0
        game@ while( true){

            println("${turn(n).name1}'s turn:")
            var putLine = readln()
            if(putLine=="end"){
                break@game
            }
            if(!"\\d+".toRegex().matches(putLine)){
                println("Incorrect column number")
                continue@game}
            if(putLine.isNullOrEmpty())continue@game
            if ( !(putLine.toInt() in 1..W)){
                println("The column number is out of range (1 - $W)")
                continue@game
            }
            if (n1[putLine.toInt()]==0){
                println( "Column $putLine is full")
                continue@game
            }
            board1[putLine.toInt()][n1[putLine.toInt()]]=turn(n).sign
            n1[putLine.toInt()]--

            n++
            temporPrint()
        }
    }
    fun temporPrint(){
        for(b in 1..W){
            print(" $b")
        }
        println()
        for ( a in  1..H){

            for(b in 1..W){
                print("║${board1[b][a]}")
            }
            println("║")
            // println()
        }
        print("╚")
        for(b in 1..W-1){
            print("═╩")
        }
        println("═╝")
    }
}
fun main() {
    val game = Connect_Four()
}