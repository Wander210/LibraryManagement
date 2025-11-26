package com.example.library.library_service.repository

import com.example.library.library_service.entity.BorrowRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BorrowRecordRepository : JpaRepository<BorrowRecord, String> {
    fun findByReaderId(readerId: String): List<BorrowRecord>
}