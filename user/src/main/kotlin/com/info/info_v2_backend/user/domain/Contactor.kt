package com.info.info_v2_backend.user.domain

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("contactor")
@OnDelete(action = OnDeleteAction.CASCADE)
class Contactor(
    name: String,
    email: String,
    password: String,
    role: Role,
    rank: String,
    phoneNumber: String?
): User(
    name,
    email,
    password,
    role
) {
    @Column(name = "contactor_rank", nullable = false)
    val rank: String = rank

    @Column(name = "contactor_phone_number", nullable = true, length = 30)
    val phoneNumber: String? = phoneNumber



}