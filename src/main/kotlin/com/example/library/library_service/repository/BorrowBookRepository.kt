package com.example.library.library_service.repository

import com.example.library.library_service.dto.BorrowBookDto
import com.example.library.library_service.dto.ReturnBookDto
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

    @Query(
        """
        SELECT new com.example.library.library_service.dto.ReturnBookDto(
            bb.id, br.id, b.id, br.borrowTime, bb.daysOverdue,
            br.dueTime,
            b.title)
        FROM BorrowBook bb
        JOIN bb.borrowRecord br
        JOIN br.reader r
        JOIN bb.book b
        WHERE r.id = :readerId AND bb.returnTime IS NULL
        ORDER BY br.borrowTime DESC
    """
    )
    fun findByReaderId(readerId: String): List<ReturnBookDto>

    @Query("""
        SELECT DISTINCT bb FROM BorrowBook bb
        LEFT JOIN FETCH bb.book
        LEFT JOIN FETCH bb.borrowRecord
        LEFT JOIN FETCH bb.defect
        WHERE bb.returnRecord.id = :returnRecordId
    """)
    fun findByReturnRecordId(returnRecordId: String): List<BorrowBook>
}