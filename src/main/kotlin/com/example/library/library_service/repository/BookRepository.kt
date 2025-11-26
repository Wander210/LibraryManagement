package com.example.library.library_service.repository

import com.example.library.library_service.dto.BookStatsDto
import com.example.library.library_service.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface BookRepository : JpaRepository<Book, String> {

    @Query("""
        SELECT new com.example.library.library_service.dto.BookStatsDto(
            b.id, b.title, b.category,
            COUNT(bb.id),
            SUM(CASE WHEN bb.returnTime IS NULL THEN 1 ELSE 0 END),
            b.availableCopies)
        FROM BorrowBook bb
        JOIN bb.book b
        WHERE bb.createdAt BETWEEN :startDate AND :endDate
        GROUP BY b.id, b.title, b.category, b.availableCopies
        ORDER BY COUNT(bb.id) DESC
    """
    )
    fun findBookStatistics(
        @Param("startDate") startDate: LocalDateTime,
        @Param("endDate") endDate: LocalDateTime
    ): List<BookStatsDto>


}