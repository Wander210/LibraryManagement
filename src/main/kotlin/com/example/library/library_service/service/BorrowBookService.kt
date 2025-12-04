package com.example.library.library_service.service

import com.example.library.library_service.dto.BorrowBookDto
import com.example.library.library_service.dto.ReturnBookDto
import com.example.library.library_service.repository.BorrowBookRepository
import com.example.library.library_service.repository.PhotoUrlRepository
import org.springframework.stereotype.Service

@Service
class BorrowBookService(
    private val borrowBookRepository: BorrowBookRepository,
    private val photoUrlRepository: PhotoUrlRepository
) {
    fun findByBookId(bookId: String): List<BorrowBookDto> {
        try {
            val borrowBooks = borrowBookRepository.findByBookId(bookId)
            val photos = photoUrlRepository.findByBookId(bookId).map { it.url }
            return borrowBooks.map { it.copy(photoUrls = photos) }
        } catch (e: Exception) {
            println("Error in BorrowBookService.findByBookId: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    fun findByReaderId(readerId: String): List<ReturnBookDto> {
        return borrowBookRepository.findByReaderId(readerId)
    }
}

