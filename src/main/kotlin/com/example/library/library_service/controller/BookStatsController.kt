package com.example.library.library_service.controller

import com.example.library.library_service.dto.BookStatsDto
import com.example.library.library_service.service.BookStatsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/book-stats")
class BookStatsController(private val bookStatsService: BookStatsService) {
    @GetMapping("/list")
    fun listBookStatsApi(
        @RequestParam("startDate", required = true) startDate: String,
        @RequestParam("endDate", required = true) endDate: String
    ): ResponseEntity<List<BookStatsDto>> {
        return ResponseEntity.ok(bookStatsService.getBookStatistics(startDate, endDate))
    }
}