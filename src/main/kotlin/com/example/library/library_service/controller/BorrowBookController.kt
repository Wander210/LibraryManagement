package com.example.library.library_service.controller

import com.example.library.library_service.dto.BorrowBookDto
import com.example.library.library_service.dto.ReturnBookDto
import com.example.library.library_service.service.BorrowBookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/borrow-book")
class BorrowBookController(private val borrowBookService: BorrowBookService) {
    
    @GetMapping("/book/{bookId}")
    fun getBorrowBooksByBookId(@PathVariable bookId: String): ResponseEntity<List<BorrowBookDto>> {
        try {
            val result = borrowBookService.findByBookId(bookId)
            return ResponseEntity.ok(result)
        } catch (e: Exception) {
            println("Error in BorrowBookController.getBorrowBooksByBookId: ${e.message}")
            e.printStackTrace()
            return ResponseEntity.status(500).build()
        }
    }
    @GetMapping("/reader/{readerId}")
    fun getBorrowBooksByReaderId(@PathVariable readerId: String): ResponseEntity<List<ReturnBookDto>> {
        try {
            val result = borrowBookService.findByReaderId(readerId)
            return ResponseEntity.ok(result)
        } catch (e: Exception) {
            println("Error in BorrowBookController.getBorrowBooksByReaderId: ${e.message}")
            e.printStackTrace()
            return ResponseEntity.status(500).build()
        }
    }
}

