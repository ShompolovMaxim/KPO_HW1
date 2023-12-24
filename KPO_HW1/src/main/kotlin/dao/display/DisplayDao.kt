package org.example.dao.display

import org.example.entity.Display

interface DisplayDao {
    fun addDisplay(display: Display)

    fun getDisplay(time: String): Display

    fun deleteDisplay(time: String)

    fun getDisplayTimes(): MutableList<String>
}