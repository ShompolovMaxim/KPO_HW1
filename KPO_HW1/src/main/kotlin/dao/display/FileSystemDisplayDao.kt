package org.example.dao.display

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.commons.io.FileUtils
import org.example.entity.Display
import org.example.dao.exception.DisplayNotFoundException
import org.example.dao.exception.TimeIsAlreadyTakenException
import java.io.File
import java.nio.charset.Charset
import kotlin.io.path.Path

class FileSystemDisplayDao : DisplayDao {
    companion object {
        private const val DIR_WITH_DISPLAYS = "displays"
    }

    override fun addDisplay(display: Display) {
        val file = Path(DIR_WITH_DISPLAYS, display.time).toFile()

        if (file.exists()) {
            throw TimeIsAlreadyTakenException("There is already a display at this time")
        }

        file.writeText(Json.encodeToString(display), Charset.defaultCharset())
    }

    override fun getDisplay(time: String): Display {
        val file = Path(DIR_WITH_DISPLAYS, time).toFile()

        if (!file.exists()) {
            throw DisplayNotFoundException("There is no display at this time in timetable")
        }

        val value = FileUtils.readFileToString(file, Charset.defaultCharset())

        return Json.decodeFromString<Display>(value)
    }

    override fun deleteDisplay(time: String) {
        val file = Path(DIR_WITH_DISPLAYS, time).toFile()

        if (!file.exists()) {
            throw DisplayNotFoundException("There is no display at this time in timetable")
        }

        file.delete()
    }

    override fun getDisplayTimes(): MutableList<String> {
        val displays: MutableList<String> = mutableListOf()
        var passedDirectoryName: Boolean = false;
        File(DIR_WITH_DISPLAYS).walk().forEach {
            if (passedDirectoryName) {
                displays.add(it.name)
            }
            passedDirectoryName = true
        }
        return displays
    }
}