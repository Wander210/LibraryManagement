package com.example.library.library_service.service

import com.example.library.library_service.dto.DefectDetailDto
import com.example.library.library_service.dto.DefectViewDto
import com.example.library.library_service.entity.DefectStatus
import com.example.library.library_service.repository.DefectRepository
import com.example.library.library_service.repository.PhotoUrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DefectService (
    private val defectRepository: DefectRepository,
    private val photoUrlRepository: PhotoUrlRepository
) {
    fun findAllDefectsForView(): List<DefectViewDto> =
        defectRepository.findAllDefectsForView()

    fun findAllDefectsByBookId(bookId: String): List<DefectViewDto> =
        defectRepository.findAllDefectsByBookId(bookId)

    fun findAllDefectsByBorrowRecordId(borrowRecordId: String): List<DefectViewDto> =
        defectRepository.findAllDefectsByBorrowRecordId(borrowRecordId)

    fun findAllDefectsById(id: String): DefectViewDto =
        defectRepository.findAllDefectsById(id)

    fun findDefectDetailById(id: String): DefectDetailDto {
        val dto = defectRepository.findAllDefectsById(id)
        val photos = photoUrlRepository.findByDefectId(id).map { it.url }
        return DefectDetailDto(
            defectId = dto.defectId,
            defectStatus = dto.defectStatus,
            defectSeverity = dto.defectSeverity,
            defectFee = dto.defectFee,
            defectReason = dto.defectReason,
            createAt = dto.createAt,
            resolvedDate = dto.resolvedDate,
            borrowRecordId = dto.borrowRecordId,
            readerName = dto.readerName,
            bookId = dto.bookId,
            bookTitle = dto.bookTitle,
            photoUrls = photos
        )
    }

    @Transactional
    fun updateDefectStatus(defectId: String, newStatus: DefectStatus): DefectViewDto {
        val defect = defectRepository.findById(defectId)
            .orElseThrow { IllegalArgumentException("Defect not found with id: $defectId") }

        defect.status = newStatus
        defect.resolvedDate = if (newStatus == DefectStatus.REPAIRED) LocalDate.now() else defect.resolvedDate

        defectRepository.save(defect)
        return defectRepository.findAllDefectsById(defectId)
    }
}