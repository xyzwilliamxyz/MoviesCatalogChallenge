package com.poc.moviescatalog.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getFormattedDate(date: Date?): String {
        return if (date == null) return ""
        else return SimpleDateFormat("MM/dd/yyyy").format(date)
    }
}