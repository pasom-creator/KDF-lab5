package com.example.botinterface.repository

import com.example.botinterface.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findFilesByUserId(userId: Long): List<User>
}