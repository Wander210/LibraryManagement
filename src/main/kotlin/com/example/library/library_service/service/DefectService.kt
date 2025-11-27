package com.example.library.library_service.service

import com.example.library.library_service.dto.DefectDetailDto
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
    fun findAllDefectsForView(): List<DefectDetailDto> =
        defectRepository.findAllDefectsForView()

    fun findAllDefectsByBookId(bookId: String): List<DefectDetailDto> =
        defectRepository.findAllDefectsByBookId(bookId)

    fun findAllDefectsByBorrowRecordId(borrowRecordId: String): List<DefectDetailDto> =
        defectRepository.findAllDefectsByBorrowRecordId(borrowRecordId)

    fun findDefectDetailById(id: String): DefectDetailDto {
        val dto = defectRepository.findDefectDetailById(id)
        val photos = photoUrlRepository.findByDefectId(id).map { it.url }
        return dto.copy(photoUrls = photos)
    }

    @Transactional
    fun updateDefectStatus(defectId: String, newStatus: DefectStatus): DefectDetailDto {
        val defect = defectRepository.findById(defectId)
            .orElseThrow { IllegalArgumentException("Defect not found with id: $defectId") }

        defect.status = newStatus
        defect.resolvedDate = if (newStatus == DefectStatus.REPAIRED) LocalDate.now() else defect.resolvedDate

        defectRepository.save(defect)
        return defectRepository.findDefectDetailById(defectId)
    }
}