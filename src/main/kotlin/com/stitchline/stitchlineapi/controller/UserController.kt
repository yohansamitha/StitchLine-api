package com.stitchline.stitchlineapi.controller

import com.stitchline.stitchlineapi.dto.UserDTO
import com.stitchline.stitchlineapi.services.UserService
import com.stitchline.stitchlineapi.util.StandardResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.bind.ValidationException

@RestController
@CrossOrigin
@RequestMapping("/stitchline/api/v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    //    user get method to validate user
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@RequestHeader("email") email: String, @RequestHeader("password") password: String)
            : ResponseEntity<StandardResponse> {
        val validateUser = userService.validateUser(email, password)
        return ResponseEntity(StandardResponse("202", "user login accepted", validateUser), HttpStatus.ACCEPTED)
    }

    //    user post method to store user in mongodb
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addUser(@RequestBody userDTO: UserDTO): ResponseEntity<StandardResponse> {
        println("user $userDTO")
        val userValidate = userValidate(userDTO)
        if (userValidate == "true") {
            return if (userService.addUser(userDTO)) {
                ResponseEntity(StandardResponse("200", "User save successfully", userDTO), HttpStatus.ACCEPTED)
            } else {
                ResponseEntity(StandardResponse("500", "something went wrong with server side", userDTO), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else {
            throw ValidationException(userValidate)
        }
    }

    //check all request data for null or useless data to validate
    private fun userValidate(userDTO: UserDTO): String {
        return if (!Regex("[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})").containsMatchIn(userDTO.email)) {
            "Invalid email address please make sure email address is correct"
        } else if (userDTO.name.trim().length <= 5) {
            "Invalid name please make sure name is correct"
        } else if (userDTO.post == "Customer" && userDTO.address.trim().length <= 3) {
            "Invalid address please make sure address is correct"
        } else if (countInt(userDTO.mobile) <= 3) {
            "Invalid mobile number please make sure mobile number is correct"
        } else if (userDTO.username.trim().length <= 3) {
            "Invalid username please make sure username is correct"
        } else if (userDTO.password.trim().length <= 3) {
            "Invalid password please make sure password is correct"
        } else if (userDTO.post == "Admin" && userDTO.nic.trim().length <= 3) {
            "Invalid nic please make sure nic is correct"
        } else if (userDTO.post == "Admin" && userDTO.employeeNumber.trim().length <= 3) {
            "Invalid employeeNumber please make sure employeeNumber is correct"
        } else if (userDTO.post.trim().length <= 3) {
            "Invalid post please make sure post is correct"
        } else {
            "true"
        }
    }

    //    count number of digits in integer
    fun countInt(number: Int): Int {
        var count = 0
        var num = number

        while (num != 0) {
            num /= 10
            ++count
        }
        return count
    }
}
