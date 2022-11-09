package com.info.info_v2_backend.nonticeQuery

import com.info.info_v2_backend.common.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping
    fun getTest(): User {
        return User("진우")
    }
}