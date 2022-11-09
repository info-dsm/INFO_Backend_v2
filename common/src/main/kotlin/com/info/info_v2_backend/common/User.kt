package com.info.info_v2_backend.common

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    name: String
) {
    @Id
    val name: String = name
}