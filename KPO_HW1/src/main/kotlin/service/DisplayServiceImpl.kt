package org.example.service

import org.example.dao.display.DisplayDao
import org.example.dao.film.FilmDao
import org.example.entity.Display
import org.example.service.exception.SeatAlreadyTakenException
import org.example.service.exception.SeatNotTakenException

class DisplayServiceImpl(private val displayDao: DisplayDao, private val filmDao: FilmDao) : DisplayService {
    override fun addDisplay(display: Display) {
        filmDao.getFilm(display.filmName)
        displayDao.addDisplay(display)
    }

    override fun getDisplay(time: String): Display {
        return displayDao.getDisplay(time)
    }

    override fun deleteDisplay(time: String) {
        displayDao.deleteDisplay(time)
    }

    override fun editDisplay(oldTime: String, newDisplay: Display) {
        filmDao.getFilm(newDisplay.filmName)
        deleteDisplay(oldTime)
        addDisplay(newDisplay)
    }

    override fun getAllDisplays(): List<Display> {
        val displays: MutableList<Display> = mutableListOf()
        for (displayTime in displayDao.getDisplayTimes()) {
            displays.add(getDisplay(displayTime))
        }
        return displays
    }

    override fun sellTicket(displayTime: String, row: Int, col: Int, customerName: String) {
        val display: Display = getDisplay(displayTime)
        if (display.hall.getSeatBooker(row, col) != null) {
            throw SeatAlreadyTakenException("This seat is already taken")
        }
        display.hall.setSeatBooker(row, col, customerName)
        editDisplay(displayTime, display)
    }

    override fun returnTicket(displayTime: String, row: Int, col: Int) {
        val display: Display = getDisplay(displayTime)
        if (display.hall.getSeatBooker(row, col) == null) {
            throw SeatNotTakenException("Ticket was not bought or has already been returned")
        }
        display.hall.setSeatBooker(row, col, null)
        editDisplay(displayTime, display)
    }
}