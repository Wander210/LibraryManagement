package com.example.library.library_service.repository

import com.example.library.library_service.entity.BorrowBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BorrowBookRepository : JpaRepository<BorrowBook, String> {
    fun findByBorrowRecordId(borrowRecordId: String): List<BorrowBook>
    fun findByBookId(bookId: String): List<BorrowBook>
}