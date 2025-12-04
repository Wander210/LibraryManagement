package com.example.library.library_service.controller

import com.example.library.library_service.entity.User
import com.example.library.library_service.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = ["*"])
class UserController(private val userService: UserService) {
    @GetMapping("/phone/{phone}")
    fun getReaderByPhone(@PathVariable phone: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.findByPhone(phone))
    }

    @GetMapping("/id/{id}")
    fun getReaderById(@PathVariable id: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.findById(id))
    }
}