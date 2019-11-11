package com.example.tasknotification.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nickevan on 15,October,2019
 */

fun convertDateToDayTitle(date: Date): String {
    val pattern = "dd MMM YYY"
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}

fun convertDateToTimeMarker(date: Date): String {
    val pattern = "HH:mm a"
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}

fun convertDateToTime(date: Date): String {
    val pattern = "HH:mm"
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}

fun convertDateToDateTime(date: Date): String {
    val pattern = "dd MMM YYYY, HH:mm a"
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}

fun convertDateToDateTime2(date: Date): String {
    val pattern = "HH:mm a, dd/MM/YYYY"
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}