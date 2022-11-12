package com.info.info_v2_backend.user.domain

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("teacher")
@OnDelete(action = OnDeleteAction.CASCADE)
class Teacher(
    name: String,
    email: String,
    password: String
): User(
    name,
    email,
    password,
    Role.TEACHER
) {

}