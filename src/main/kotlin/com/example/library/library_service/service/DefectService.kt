package com.example.library.library_service.service

import com.example.library.library_service.dto.DefectViewDto
import com.example.library.library_service.entity.DefectStatus
import com.example.library.library_service.repository.DefectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DefectService (
    private val defectRepository: DefectRepository
) {
    fun findAllDefectsForView(): List<DefectViewDto> =
        defectRepository.findAllDefectsForView()

    fun findAllDefectsByBookId(bookId: String): List<DefectViewDto> =
        defectRepository.findAllDefectsByBookId(bookId)

    fun findAllDefectsByBorrowRecordId(borrowRecordId: String): List<DefectViewDto> =
        defectRepository.findAllDefectsByBorrowRecordId(borrowRecordId)

    fun findAllDefectsById(id: String): DefectViewDto =
        defectRepository.findAllDefectsById(id)

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