package com.example.library.library_service.dto

import java.time.LocalDateTime

data class BorrowBookDto @JvmOverloads constructor (
    val id: String,
    val borrowTime: LocalDateTime,
    val returnTime: LocalDateTime?,
    val daysOverdue : Int,

    val dueTime : LocalDateTime,

    val readerName: String,
    val librarianName: String,

    val bookId : String,
    val bookTitle : String,
    val availableCopies: Int,
    var totalOfCopies: Int,
    val photoUrls: List<String> = emptyList()
)