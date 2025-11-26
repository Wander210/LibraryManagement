package com.example.library.library_service.repository

import com.example.library.library_service.entity.ReturnRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReturnRecordRepository : JpaRepository<ReturnRecord, String> {
    fun findByReaderId(readerId: String): List<ReturnRecord>
}