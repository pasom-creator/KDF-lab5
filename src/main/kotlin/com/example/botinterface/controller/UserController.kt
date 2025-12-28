package com.example.botinterface.controller

import com.example.botinterface.model.User
import com.example.botinterface.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{userId}")
    fun listUsers(@PathVariable userId: String): Iterable<User> = userService.getFilesByUserId(
        TODO()
    )
}
