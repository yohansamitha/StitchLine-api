package com.stitchline.stitchlineapi.repo

import com.stitchline.stitchlineapi.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>
