package com.example.library.library_service.repository

import com.example.library.library_service.entity.PhotoUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotoUrlRepository : JpaRepository<PhotoUrl, String> {
    fun findByBookId(bookId: String): List<PhotoUrl>
    fun findByDefectId(defectId: String): List<PhotoUrl>
}