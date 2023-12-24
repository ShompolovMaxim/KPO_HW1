package org.example.service

import org.example.dao.film.FilmDao
import org.example.entity.Film

class FilmServiceImpl(private val filmDao: FilmDao) : FilmService {
    override fun addFilm(film: Film) {
        filmDao.addFilm(film)
    }

    override fun getFilm(name: String): Film {
        return filmDao.getFilm(name)
    }

    override fun deleteFilm(name: String) {
        filmDao.deleteFilm(name)
    }

    override fun editFilm(oldName: String, newFilm: Film) {
        deleteFilm(oldName)
        addFilm(newFilm)
    }

    override fun getAllFilms(): MutableList<Film> {
        val files: MutableList<Film> = mutableListOf()
        for(film in filmDao.getFilmNames()) {
            files.add(getFilm(film))
        }
        return files
    }
}