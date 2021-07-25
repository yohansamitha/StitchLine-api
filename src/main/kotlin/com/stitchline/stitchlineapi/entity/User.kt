package com.stitchline.stitchlineapi.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
class User(email: String, name: String, address: String, mobile: Int, username: String, password: String, nic: String,
           employeeNumber: String, post: String) {
    @Id
    var email = email
    var name = name
    var address = address
    var mobile = mobile
    var username = username
    var password = password
    var nic = nic
    var employeeNumber = employeeNumber
    var post = post

    override fun toString(): String {
        return "User(email='$email', name='$name', address='$address', mobile=$mobile, username='$username', password='$password', nic='$nic', employeeNumber='$employeeNumber', post='$post')"
    }
}
