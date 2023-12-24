package org.example

import org.example.dao.display.FileSystemDisplayDao
import org.example.dao.film.FileSystemFilmDao
import org.example.service.DisplayServiceImpl
import org.example.service.FilmServiceImpl
import org.example.service.UserInterfaceService
import org.example.service.UserInterfaceServiceImpl

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val userInterfaceService: UserInterfaceService =
        UserInterfaceServiceImpl(FilmServiceImpl(FileSystemFilmDao()),
            DisplayServiceImpl(FileSystemDisplayDao(), FileSystemFilmDao()))
    while (true) {
        println("Enter 1 to add film")
        println("Enter 2 to get data about film")
        println("Enter 3 to edit film")
        println("Enter 4 to delete film")
        println("Enter 5 to add display")
        println("Enter 6 to get data about display")
        println("Enter 7 to edit display")
        println("Enter 8 to delete display")
        println("Enter 9 to get data about films")
        println("Enter 10 to get data about displays")
        println("Enter 11 to sell ticket for display")
        println("Enter 12 to return ticket for display")
        println("Enter 13 to exit")
        val action: String = readln()
        when (action) {
            "1" -> userInterfaceService.addFilm()
            "2" -> userInterfaceService.printDataAboutFilm()
            "3" -> userInterfaceService.editFilm()
            "4" -> userInterfaceService.deleteFilm()
            "5" -> userInterfaceService.addDisplay()
            "6" -> userInterfaceService.printDataAboutDisplay()
            "7" -> userInterfaceService.editDisplay()
            "8" -> userInterfaceService.deleteDisplay()
            "9" -> userInterfaceService.printDataAboutFilms()
            "10" -> userInterfaceService.printDataAboutDisplays()
            "11" -> userInterfaceService.sellTicket()
            "12" -> userInterfaceService.returnTicket()
            "13" -> break
            else -> userInterfaceService.incorrectAction()
        }
    }
}