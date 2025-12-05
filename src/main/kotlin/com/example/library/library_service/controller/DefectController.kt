package com.example.library.library_service.controller

import com.example.library.library_service.dto.CreateDefectRequest
import com.example.library.library_service.dto.DefectDetailDto
import com.example.library.library_service.dto.UpdateDefectStatusRequest
import com.example.library.library_service.service.DefectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/defect")
@CrossOrigin(origins = ["*"])
class DefectController(private val defectService: DefectService) {
    @GetMapping()
    fun listDefectsApi(): ResponseEntity<List<DefectDetailDto>> {
        return ResponseEntity.ok(defectService.findAllDefectsForView())
    }

    @GetMapping("/book/{bookId}")
    fun listDefectsByBookIdApi(@PathVariable bookId: String): ResponseEntity<List<DefectDetailDto>> {
        return ResponseEntity.ok(defectService.findAllDefectsByBookId(bookId))
    }

    @GetMapping("/borrowRecord/{borrowRecordId}")
    fun listDefectsByBorrowRecordIdApi(@PathVariable borrowRecordId: String): ResponseEntity<List<DefectDetailDto>> {
        return ResponseEntity.ok(defectService.findAllDefectsByBorrowRecordId(borrowRecordId))
    }

    @GetMapping("/id/{id}")
    fun listDefectsByIdApi(@PathVariable id: String): ResponseEntity<DefectDetailDto> {
        return ResponseEntity.ok(defectService.findDefectDetailById(id))
    }

    @PostMapping
    fun createDefect(@RequestBody request: CreateDefectRequest): ResponseEntity<DefectDetailDto> {
        return ResponseEntity.ok(defectService.createDefect(request))
    }

    @PutMapping("/{id}/status")
    fun updateDefectStatus(
        @PathVariable id: String,
        @RequestBody request: UpdateDefectStatusRequest
    ): ResponseEntity<DefectDetailDto> {
        return ResponseEntity.ok(defectService.updateDefectStatus(id, request.status))
    }
}