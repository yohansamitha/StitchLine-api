package com.stitchline.stitchlineapi.dto

class UserDTO(var email: String, var name: String, var address: String, var mobile: Int, var username: String, var password: String, var nic: String,
              var employeeNumber: String, var post: String) {

    override fun toString(): String {
        return "UserDTO(email='$email', name='$name', address='$address', mobile=$mobile, username='$username', " +
                "password='$password', nic='$nic', employeeNumber='$employeeNumber', post='$post')"
    }
}
