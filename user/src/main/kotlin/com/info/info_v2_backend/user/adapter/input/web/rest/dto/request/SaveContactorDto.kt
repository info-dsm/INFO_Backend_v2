package com.info.info_v2_backend.user.adapter.input.web.rest.dto.request

import com.info.info_v2_backend.user.domain.Contactor
import com.info.info_v2_backend.user.domain.Role

class SaveContactorDto(
    name: String,
    email: String,
    password: String,
    val rank: String,
    val phoneNumber: String?,
    val passwordHint: String?,
    val companyNumber: String
): SaveUserDto(
    name,
    email,
    password
) {

    fun toContactor(): Contactor {
        return Contactor(
            this.name,
            this.email,
            this.password,
            Role.CONTACTOR,
            this.rank,
            this.phoneNumber,
            this.passwordHint,
            this.companyNumber
        )
    }

}