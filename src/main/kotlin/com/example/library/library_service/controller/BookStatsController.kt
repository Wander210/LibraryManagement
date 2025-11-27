package com.example.library.library_service.controller

import com.example.library.library_service.dto.BookStatsDto
import com.example.library.library_service.service.BookStatsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/book-stats")
class BookStatsController(private val bookStatsService: BookStatsService) {
    @GetMapping()
    fun listBookStatsApi(
        @RequestParam("startDate", required = true) startDate: String,
        @RequestParam("endDate", required = true) endDate: String
    ): ResponseEntity<List<BookStatsDto>> {
        return ResponseEntity.ok(bookStatsService.getBookStatistics(startDate, endDate))
    }
}