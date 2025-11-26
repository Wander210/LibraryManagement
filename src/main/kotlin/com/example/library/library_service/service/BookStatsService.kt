package com.example.library.library_service.service

import com.example.library.library_service.dto.BookDto
import com.example.library.library_service.repository.BookRepository
import com.example.library.library_service.repository.PhotoUrlRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookStatsService(
    private val bookRepository: BookRepository,
    private val photoUrlRepository: PhotoUrlRepository) {
    fun getBookStatistics(startDate: String, endDate: String) =
        bookRepository.findBookStatistics(
            LocalDate.parse(startDate).atStartOfDay(),
            LocalDate.parse(endDate).atTime(23, 59, 59)
        )
    fun findBookById(id: String): BookDto {
        val book = bookRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Book not found with id: $id") }
        val photos = photoUrlRepository.findByBookId(id).map { it.url }
        return BookDto(
            id = book.id,
            title = book.title,
            category = book.category,
            author = book.author,
            publisher = book.publisher,
            publicationYear = book.publicationYear,
            description = book.description,
            availableCopies = book.availableCopies,
            totalOfCopies = book.totalOfCopies,
            createdAt = book.createdAt,
            photoUrls = photos)
    }
}