package org.example.service

import org.example.entity.Film

interface FilmService {
    fun addFilm(film: Film)

    fun getFilm(name: String): Film

    fun deleteFilm(name: String)

    fun editFilm(oldName: String, newFilm: Film)

    fun getAllFilms(): List<Film>
}