package com.example.library.library_service.repository

import com.example.library.library_service.dto.DefectDetailDto
import com.example.library.library_service.entity.Defect
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DefectRepository : JpaRepository<Defect, String> {
    @Query(
        """
      SELECT new com.example.library.library_service.dto.DefectDetailDto(
        d.id, d.status, d.severity, d.defectFee, d.defectReason, d.createdAt, d.resolvedDate,
        br.id,
        u.fullName,
        b.id, b.title
      )
      FROM BorrowBook bb
      JOIN bb.defect d
      JOIN bb.borrowRecord br
      JOIN bb.book b
      JOIN br.reader u
      WHERE d IS NOT NULL
      ORDER BY d.createdAt DESC
      """
    )
    fun findAllDefectsForView(): List<DefectDetailDto>

    @Query(
        """
      SELECT new com.example.library.library_service.dto.DefectDetailDto(
        d.id, d.status, d.severity, d.defectFee, d.defectReason, d.createdAt, d.resolvedDate,
        br.id,
        u.fullName,
        b.id, b.title
      )
      FROM BorrowBook bb
      JOIN bb.defect d
      JOIN bb.borrowRecord br
      JOIN bb.book b
      JOIN br.reader u
      WHERE bb.book.id = :bookId AND d IS NOT NULL
      ORDER BY d.createdAt DESC
      """
    )
    fun findAllDefectsByBookId(bookId: String): List<DefectDetailDto>

    @Query(
        """
      SELECT new com.example.library.library_service.dto.DefectDetailDto(
        d.id, d.status, d.severity, d.defectFee, d.defectReason, d.createdAt, d.resolvedDate,
        br.id,
        u.fullName,
        b.id, b.title
      )
      FROM BorrowBook bb
      JOIN bb.defect d
      JOIN bb.borrowRecord br
      JOIN bb.book b
      JOIN br.reader u
      WHERE bb.borrowRecord.id = :borrowRecordId AND d IS NOT NULL
      ORDER BY d.createdAt DESC
      """
    )
    fun findAllDefectsByBorrowRecordId(borrowRecordId: String): List<DefectDetailDto>

    @Query(
        """
      SELECT new com.example.library.library_service.dto.DefectDetailDto(
        d.id, d.status, d.severity, d.defectFee, d.defectReason, d.createdAt, d.resolvedDate,
        br.id,
        u.fullName,
        b.id, b.title
      )
      FROM BorrowBook bb
      JOIN bb.defect d
      JOIN bb.borrowRecord br
      JOIN bb.book b
      JOIN br.reader u
      WHERE bb.defect.id = :id AND d IS NOT NULL
      ORDER BY d.createdAt DESC
      """
    )
    fun findAllDefectsById(id: String): DefectDetailDto
}