package com.example.library.library_service.repository

import com.example.library.library_service.dto.BorrowBookDto
import com.example.library.library_service.entity.BorrowBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BorrowBookRepository : JpaRepository<BorrowBook, String> {
    @Query("""
        SELECT new com.example.library.library_service.dto.BorrowBookDto(
            bb.id, br.borrowTime, bb.returnTime, bb.daysOverdue,
            br.dueTime,
            r.fullName, l.fullName,
            b.id, b.title, b.availableCopies, b.totalOfCopies)
        FROM BorrowBook bb
        JOIN bb.borrowRecord br
        JOIN br.reader r
        JOIN br.librarian l
        JOIN bb.book b
        WHERE b.id = :bookId
        ORDER BY br.borrowTime DESC
    """
    )
    fun findByBookId(bookId: String): List<BorrowBookDto>
}