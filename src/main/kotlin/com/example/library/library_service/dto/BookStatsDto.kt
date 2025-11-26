package com.example.library.library_service.dto

data class BookStatsDto (
    val bookId: String,
    val bookTitle: String,
    val category: String,
    val borrowCount: Long,
    val currentBorrowed: Long,
    val available: Int
)