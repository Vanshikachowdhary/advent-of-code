package com.vanshika.adventofcode

// when L -> -
// when R -> +
fun main() {
    val result = object {}.javaClass
        .getResource("/InputPuzzle1.txt")!!
        .readText().convertToKv().findPassword()
    print(result)
}

fun String.convertToKv(): List<Pair<Char, Int>> =
    this.trimIndent()
        .split('\n')
        .map {
            it.first() to it.drop(1).toInt()
        }

fun List<Pair<Char, Int>>.findPassword(): Int {
    var currentPosition = 50
    var counter = 0
    this.map { pair ->
        when (pair.first) {
            'L' -> {  //anti-clock
                currentPosition = (currentPosition - pair.second + 100) % 100
                if (currentPosition == 0) counter++
            }

            else -> {   //clock
                currentPosition = (currentPosition + pair.second + 100) % 100
                if (currentPosition == 0) counter++
            }
        }
    }
    return counter
}