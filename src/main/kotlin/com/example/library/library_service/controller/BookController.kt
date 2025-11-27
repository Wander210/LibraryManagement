package com.example.library.library_service.controller

import com.example.library.library_service.dto.BookDto
import com.example.library.library_service.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {
    
    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: String): ResponseEntity<BookDto> {
        return ResponseEntity.ok(bookService.findBookById(id))
    }
}

