package org.example.entity

import kotlinx.serialization.Serializable

@Serializable
class OnlyRectangularHall {
    @Serializable
    private val seatBookers: MutableList<MutableList<String?>> = MutableList(HEIGHT) { MutableList(WIDTH) { null } }

    fun getSeatBooker(row: Int, column: Int): String? {
        if (row < 0 || row >= HEIGHT || column < 0 || column > WIDTH) {
            throw IncorrectSeatException("Row or column out of range")
        }
        return seatBookers[row][column]
    }

    fun setSeatBooker(row: Int, column: Int, status: String?) {
        seatBookers[row][column] = status
    }

    override fun toString(): String {
        var output = ""
        for (row in seatBookers) {
            for (seat in row) {
                output += if (seat == null) "0 " else "1 "
            }
            output += "\n"
        }
        return output
    }

    companion object HeightWidth {
        private const val HEIGHT = 3
        private const val WIDTH = 5
    }
}