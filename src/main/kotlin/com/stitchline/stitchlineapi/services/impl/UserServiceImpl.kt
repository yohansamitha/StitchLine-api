package com.stitchline.stitchlineapi.services.impl

import com.stitchline.stitchlineapi.dto.UserDTO
import com.stitchline.stitchlineapi.entity.User
import com.stitchline.stitchlineapi.exception.NotFoundException
import com.stitchline.stitchlineapi.repo.UserRepository
import com.stitchline.stitchlineapi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun addUser(userDTO: UserDTO): Boolean {
        val user = User(userDTO.email, userDTO.name, userDTO.address, userDTO.mobile, userDTO.username,
                userDTO.password, userDTO.nic, userDTO.employeeNumber, userDTO.post)
        val save = userRepository.save(user)
        return save == user
    }

    override fun validateUser(email: String, password: String): User {
        val findById = userRepository.findById(email)
        if (findById.isPresent) {
            return findById.get()
        } else {
            throw NotFoundException("User Not Found For this Email")
        }
    }
}
