package com.example.botinterface.service

import com.example.botinterface.model.User
import com.example.botinterface.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    @Transactional
    fun addUpload(userId: Long, fullName: String, fileId: String): User {
        return userRepository.save(
            User(
                userId = userId,
                fullName = fullName,
                fileId = fileId
            )
        )
    }

    fun getUserById(userId: Long): User? {
        return userRepository.findById(userId).orElse(null)
    }

    fun getFilesByUserId(userId: Long): List<User> {
        return userRepository.findFilesByUserId(userId)
    }
}