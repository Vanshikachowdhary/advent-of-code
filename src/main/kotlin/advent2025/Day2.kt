package com.vanshika.adventofcode.advent2025


fun main() {
    val sampleInupt =
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
    val input = sampleInupt.split(",").map { it ->
        val (start, end) = it.split("-").map { it.toLong() }
        Pair(start, end)
    }

    val finalInputSample =
        "1-14,46452718-46482242,16-35,92506028-92574540,1515128146-1515174322,56453-79759,74-94,798-971,49-66,601-752,3428-4981,511505-565011,421819-510058,877942-901121,39978-50500,9494916094-9494978970,7432846301-7432888696,204-252,908772-990423,21425-25165,1030-1285,7685-9644,419-568,474396757-474518094,5252506279-5252546898,4399342-4505058,311262290-311393585,1895-2772,110695-150992,567521-773338,277531-375437,284-364,217936-270837,3365257-3426031,29828-36350".split(
            ","
        ).map { it ->
            val (start, end) = it.split("-").map { it.toLong() }
            Pair(start, end)
        }

    println(input.fold(0L) { acc, pair -> acc + pair.countValidPasswordsInRange() })
    println(finalInputSample.fold(0L) { acc, pair -> acc + pair.countValidPasswordsInRange() })
    println(input.fold(0L) { acc, pair -> acc + pair.countValidPasswordsInRangeUpdated() })
    println(finalInputSample.fold(0L) { acc, pair -> acc + pair.countValidPasswordsInRangeUpdated() })

    "23456".chunked(2)
}

fun Pair<Long, Long>.countValidPasswordsInRange(): Long {
    val listOfNumbers = mutableListOf<Long>()
    for (i in this.first..this.second) {
        val s = i.toString()
        if ((s.startsWith('0'))) {
            listOfNumbers.add(i)
        }

        if (s.length % 2 == 0) {
            if (s.substring(0, s.length / 2) == s.substring(s.length / 2)) {
                listOfNumbers.add(i)
            }
        }
    }
    return listOfNumbers.fold(0) { acc, i -> acc + i }
}

fun Pair<Long, Long>.countValidPasswordsInRangeUpdated(): Long {   //
    val sum = mutableListOf<Long>()
    for (i in this.first..this.second) {
        val s = i.toString()
        for (len in 1..s.length / 2) {
            if (s.length % len == 0) {
                val repeatCount = s.length / len
                if (repeatCount >= 2) {
                    val pattern = s.substring(0, len)
                    if (pattern.repeat(repeatCount) == s) {
                        sum.add(i)
                        break
                    }
                }
            }
        }
    }
    return sum.fold(0L) { acc, i -> acc + i }
}

//https://www.baeldung.com/java-microbenchmark-harness

