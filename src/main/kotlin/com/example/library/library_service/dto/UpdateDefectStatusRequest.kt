package com.example.library.library_service.dto

import com.example.library.library_service.entity.DefectStatus

data class UpdateDefectStatusRequest(
    val status: DefectStatus
)

