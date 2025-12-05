package com.example.library.library_service.service

import com.example.library.library_service.dto.CreateDefectRequest
import com.example.library.library_service.dto.DefectDetailDto
import com.example.library.library_service.entity.Defect
import com.example.library.library_service.entity.DefectStatus
import com.example.library.library_service.entity.PhotoUrl
import com.example.library.library_service.repository.BorrowBookRepository
import com.example.library.library_service.repository.DefectRepository
import com.example.library.library_service.repository.PhotoUrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.UUID

@Service
class DefectService (
    private val defectRepository: DefectRepository,
    private val photoUrlRepository: PhotoUrlRepository,
    private val borrowBookRepository: BorrowBookRepository
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

    @Transactional
    fun createDefect(request: CreateDefectRequest): DefectDetailDto {
        // Tìm BorrowBook từ borrowRecordId và bookId
        val borrowBook = borrowBookRepository.findAll().firstOrNull { 
            it.borrowRecord?.id == request.borrowRecordId && it.book?.id == request.bookId 
        } ?: throw IllegalArgumentException("BorrowBook not found for borrowRecordId: ${request.borrowRecordId} and bookId: ${request.bookId}")

        // Tạo Defect mới
        val defectId = UUID.randomUUID().toString()
        val defect = Defect(
            id = defectId,
            status = request.status,
            severity = request.severity,
            defectReason = request.description,
            defectFee = java.math.BigDecimal.ZERO // Có thể tính sau
        )

        // Lưu Defect
        defectRepository.save(defect)

        // Tạo PhotoUrl cho mỗi imageUrl
        request.imageUrls.forEach { url ->
            val photoUrl = PhotoUrl(
                id = UUID.randomUUID().toString(),
                url = url
            )
            photoUrl.defect = defect
            photoUrlRepository.save(photoUrl)
        }

        // Link Defect với BorrowBook (sẽ được cập nhật trong ReturnRecordService khi tạo return record)
        // borrowBook.defect = defect
        // borrowBookRepository.save(borrowBook)

        return defectRepository.findDefectDetailById(defectId)
    }
}