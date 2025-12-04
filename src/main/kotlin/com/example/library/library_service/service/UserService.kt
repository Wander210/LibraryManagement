package com.example.library.library_service.service

import com.example.library.library_service.entity.User
import com.example.library.library_service.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByPhone(phone: String): User {
        val user = userRepository.findByPhone(phone) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        if (user.role != "READER") throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a reader")
        return user
    }

    fun findById(id: String): User {
        val user = userRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }
        if (user.role != "READER") throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a reader")
        return user
    }
}