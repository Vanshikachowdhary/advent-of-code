package com.vanshika.adventofcode.advent2025

// when L -> -
// when R -> +
fun main() {
    val result = object {}.javaClass
        .getResource("/InputDay1.txt")!!
        .readText().convertToKv().findPassword1()
    println(result)

    val result2 = object {}.javaClass
        .getResource("/InputDay1.txt")!!
        .readText().convertToKv().findPassword2()
    println(result2)
}

fun String.convertToKv(): List<Pair<Char, Int>> =
    this.trimIndent()
        .split('\n')
        .map {
            it.first() to it.drop(1).toInt()
        }

fun List<Pair<Char, Int>>.findPassword1(): Int {
    var currentPosition = 50
    var counter = 0
    this.forEach { (direction, rotateBy) ->

        if (direction == 'L') {
            currentPosition = (currentPosition - rotateBy + 100) % 100
        } else {   //clock
            currentPosition = (currentPosition + rotateBy + 100) % 100  // can remove +100 since the current position will be positive and we're adding the rotate by

        }
        if (currentPosition == 0) counter++
    }
    return counter
}

fun List<Pair<Char, Int>>.findPassword2(): Int {
    var currentPosition = 50
    var counter = 0

    this.forEach { (direction,rotateBy)->
        repeat(rotateBy){
            currentPosition = if(direction=='L'){
                (currentPosition -1) % 100
            } else{
                (currentPosition +1) % 100
            }
            if(currentPosition==0)counter++
        }

    }
    return counter
}


