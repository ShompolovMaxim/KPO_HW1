package org.example.dao.film

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.commons.io.FileUtils
import org.example.entity.Film
import org.example.dao.exception.FilmAlreadyExistsException
import org.example.dao.exception.FilmNotFoundException
import java.io.File
import java.nio.charset.Charset
import kotlin.io.path.Path


class FileSystemFilmDao : FilmDao {
    companion object {
        private const val DIR_WITH_FILMS = "films"
    }

    override fun addFilm(film: Film) {
        val file = Path(DIR_WITH_FILMS, film.name).toFile()

        if (file.exists()) {
            throw FilmAlreadyExistsException("Film with such name already exists")
        }

        file.writeText(Json.encodeToString(film), Charset.defaultCharset())
    }

    override fun getFilm(name: String): Film {
        val file = Path(DIR_WITH_FILMS, name).toFile()

        if (!file.exists()) {
            throw FilmNotFoundException("There is no film with such name")
        }

        val value = FileUtils.readFileToString(file, Charset.defaultCharset())

        return Json.decodeFromString<Film>(value)
    }

    override fun deleteFilm(name: String) {
        val file = Path(DIR_WITH_FILMS, name).toFile()

        if (!file.exists()) {
            throw FilmNotFoundException("There is no film with such name")
        }

        file.delete()
    }

    override fun getFilmNames(): MutableList<String> {
        val files: MutableList<String> = mutableListOf()
        var passedDirectoryName: Boolean = false;
        File(DIR_WITH_FILMS).walk().forEach {
            if (passedDirectoryName) {
                files.add(it.name)
            }
            passedDirectoryName = true
        }
        return files
    }
}