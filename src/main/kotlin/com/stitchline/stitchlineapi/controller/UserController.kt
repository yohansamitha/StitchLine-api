package com.stitchline.stitchlineapi.controller

import com.stitchline.stitchlineapi.entity.User
import com.stitchline.stitchlineapi.exception.NotFoundException
import com.stitchline.stitchlineapi.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/stitchline/api/v1/user")
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository

    //    user get method to validate user
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@RequestHeader("email") email: String, @RequestHeader("password") password: String)
            : ResponseEntity<User> {
        val findById = userRepository.findById(email)
        if (findById.isPresent) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(findById.get());
        } else {
            throw NotFoundException("User Not Found For this Email")
        }
    }

    //    user post method to store user in mongodb
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addUser(@RequestBody user: User): ResponseEntity<String> {
        val saveUser = userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).body("$saveUser")
    }
}
