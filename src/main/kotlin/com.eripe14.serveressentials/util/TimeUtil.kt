package com.eripe14.serveressentials.util

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtil {

    // in the future, we can add here more formatters like short one with only time
    private val defaultFormatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")

    fun format(dateTime: LocalDateTime): String {
        return dateTime.format(defaultFormatter)
    }

    fun format(duration: Duration): String {
        val formatDuration: Duration = Duration.ofSeconds(duration.toSeconds())

        return formatDuration.toString()
            .substring(2)
            .replace("(\\d[HMS])(?!$)".toRegex(), "$1 ")
            .lowercase(Locale.getDefault())
    }

}