package org.example.service

import org.example.entity.Display

interface DisplayService {
    fun addDisplay(display: Display)

    fun getDisplay(time: String): Display

    fun deleteDisplay(time: String)

    fun editDisplay(oldTime: String, newDisplay: Display)

    fun getAllDisplays(): List<Display>

    fun sellTicket(displayTime: String, row: Int, col: Int, customerName: String)

    fun returnTicket(displayTime: String, row: Int, col: Int)
}
