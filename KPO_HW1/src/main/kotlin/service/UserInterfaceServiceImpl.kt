package org.example.service

import org.example.dao.exception.DisplayNotFoundException
import org.example.dao.exception.FilmAlreadyExistsException
import org.example.dao.exception.FilmNotFoundException
import org.example.dao.exception.TimeIsAlreadyTakenException
import org.example.entity.Display
import org.example.entity.Film
import org.example.entity.OnlyRectangularHall
import org.example.service.exception.SeatAlreadyTakenException
import org.example.service.exception.SeatNotTakenException
import java.io.IOException

class UserInterfaceServiceImpl(private val filmService: FilmService, private val displayService: DisplayService) :
    UserInterfaceService {
    override fun addFilm() {
        print("Enter new film name: ")
        val name: String = readln()
        print("Enter new film description: ")
        val description: String = readln()
        try {
            filmService.addFilm(Film(name, description))
            println("Film added successfully")
        } catch (ex: FilmAlreadyExistsException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun editFilm() {
        print("Enter film name: ")
        val name: String = readln()
        try {
            val film: Film = filmService.getFilm(name)
            println("Film description: ${film.description}")
            print("Enter new film name: ")
            val newName: String = readln()
            print("Enter new film description: ")
            val newDescription: String = readln()
            filmService.editFilm(name, Film(newName, newDescription))
            println("Film edited successfully")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun printDataAboutFilm() {
        print("Enter film name: ")
        val name: String = readln()
        try {
            val film: Film = filmService.getFilm(name)
            println("Film description: ${film.description}")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun deleteFilm() {
        print("Enter film name: ")
        val name: String = readln()
        try {
            filmService.deleteFilm(name)
            println("Film deleted successfully")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun addDisplay() {
        print("Enter new display time: ")
        val time: String = readln()
        print("Enter new display film name: ")
        val filmName: String = readln()
        try {
            displayService.addDisplay(Display(filmName, OnlyRectangularHall(), time))
            println("Display added successfully")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch(ex: TimeIsAlreadyTakenException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun editDisplay() {
        print("Enter display time: ")
        val time: String = readln()
        try {
            val display: Display= displayService.getDisplay(time)
            println("Display film: ${display.filmName}")
            print("Enter new display time: ")
            val newTime: String = readln()
            print("Enter new display film: ")
            val newFilmName: String = readln()
            displayService.editDisplay(time, Display(newFilmName, OnlyRectangularHall(), newTime))
            println("Display edited successfully")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch(ex: TimeIsAlreadyTakenException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun printDataAboutDisplay() {
        print("Enter display time: ")
        val time: String = readln()
        try {
            val display: Display = displayService.getDisplay(time)
            println("Film name: ${display.filmName}")
            println("Seats (0 - free, 1 - taken):")
            println(display.hall.toString())
        } catch (ex: DisplayNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun deleteDisplay() {
        print("Enter display time: ")
        val time: String = readln()
        try {
            displayService.deleteDisplay(time)
            println("Display deleted successfully")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        }
    }

    override fun printDataAboutFilms() {
        try{
            for (film in filmService.getAllFilms()) {
                println("Film name: ${film.name}")
                println("Film description: ${film.description}")
            }
        } catch (ex: IOException) {
            println("No access to data")
        } catch (ex: FilmNotFoundException) {
            println(ex.message)
        }

    }

    override fun printDataAboutDisplays() {
        try {
            for (display in displayService.getAllDisplays()) {
                println("Display time: ${display.time}")
                println("Display film name: ${display.filmName}")
            }
        } catch (ex: IOException) {
            println("No access to data")
        } catch (ex: DisplayNotFoundException) {
            println(ex.message)
        }

    }

    override fun sellTicket() {
        print("Enter display time: ")
        val time: String = readln()
        try{
            print("Enter row: ")
            val row: Int = readln().toInt()
            print("Enter column: ")
            val col: Int = readln().toInt()
            print("Enter customer name: ")
            val name: String = readln()
            displayService.sellTicket(time, row, col, name)
        } catch (ex: NumberFormatException) {
            println("It is not a number")
        } catch (ex: DisplayNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        } catch (ex: SeatAlreadyTakenException) {
            println(ex.message)
        }
    }

    override fun returnTicket() {
        print("Enter display time: ")
        val time: String = readln()
        try{
            print("Enter row: ")
            val row: Int = readln().toInt()
            print("Enter column: ")
            val col: Int = readln().toInt()
            displayService.returnTicket(time, row, col)
        } catch (ex: NumberFormatException) {
            println("It is not a number")
        } catch (ex: DisplayNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println("No access to data")
        } catch (ex: SeatNotTakenException) {
            println(ex.message)
        }
    }

    override fun incorrectAction() {
        println("There is no action for this code")
    }
}