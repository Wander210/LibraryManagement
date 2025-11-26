package com.example.library.library_service.dto

import com.example.library.library_service.entity.DefectStatus
import com.example.library.library_service.entity.Severity
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class DefectViewDto(
    val defectId: String,
    val defectStatus: DefectStatus,
    val defectSeverity: Severity,
    val defectFee : BigDecimal,
    val defectReason : String,
    val createAt : LocalDateTime,
    val resolvedDate : LocalDate?,
    val borrowRecordId : String,
    val readerName: String,
    val bookId: String,
    val bookTitle: String
)