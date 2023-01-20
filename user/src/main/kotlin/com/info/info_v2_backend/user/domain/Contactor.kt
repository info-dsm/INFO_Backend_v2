package com.info.info_v2_backend.user.domain

import com.info.info_v2_backend.common.user.Role
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
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
    phoneNumber: String?,
    passwordHint: String?,
    companyNumber: String
): User(
    name,
    email,
    password,
    role,
    passwordHint
) {
    @Column(name = "contactor_rank", nullable = false)
    val rank: String = rank

    @Column(name = "contactor_phone_number", nullable = true, length = 30)
    val phoneNumber: String? = phoneNumber

    @Column(name = "contactor_company_number", nullable = false)
    val companyNumber: String = companyNumber


    fun toContactorResponse(): ContactorResponse {
        return ContactorResponse(
            this.name,
            this.email,
            this.rank,
            this.phoneNumber,
            this.passwordHint,
            this.companyNumber
        )
    }


}