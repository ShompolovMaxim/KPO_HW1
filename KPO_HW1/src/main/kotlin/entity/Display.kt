package org.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class Display(val filmName: String, val hall: OnlyRectangularHall, val time: String) {
}
