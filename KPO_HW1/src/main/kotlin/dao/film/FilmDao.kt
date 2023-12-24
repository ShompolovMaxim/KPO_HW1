package org.example.dao.film

import org.example.entity.Film

interface FilmDao {
    fun addFilm(film: Film)

    fun getFilm(name: String): Film

    fun deleteFilm(name: String)

    fun getFilmNames(): MutableList<String>
}