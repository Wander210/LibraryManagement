package com.example.library.library_service.service

import com.example.library.library_service.repository.BookRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookStatsService(private val bookRepository: BookRepository) {
    fun getBookStatistics(startDate: String, endDate: String) =
        bookRepository.findBookStatistics(
            LocalDate.parse(startDate).atStartOfDay(),
            LocalDate.parse(endDate).atTime(23, 59, 59)
        )
}