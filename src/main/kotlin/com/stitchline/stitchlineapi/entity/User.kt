package com.stitchline.stitchlineapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
class User(@Id var email: String, var name: String, var address: String, var mobile: Int, var username: String, var password: String, var nic: String,
           var employeeNumber: String, var post: String) {

    override fun toString(): String {
        return "User(email='$email', name='$name', address='$address', mobile=$mobile, username='$username', password='$password', nic='$nic', employeeNumber='$employeeNumber', post='$post')"
    }
}
