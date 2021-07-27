package com.stitchline.stitchlineapi.services

import com.stitchline.stitchlineapi.dto.UserDTO
import com.stitchline.stitchlineapi.entity.User

interface UserService {
    fun addUser(userDTO: UserDTO): Boolean
    fun validateUser(email: String, password: String): User
}
