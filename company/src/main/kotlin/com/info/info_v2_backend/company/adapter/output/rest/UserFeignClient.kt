package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.company.application.port.output.user.LoadUserPort
import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.common.user.StudentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICE")
interface UserFeignClient: LoadUserPort {

    @GetMapping("/contactor")
    override fun loadContactor(@RequestParam companyNumber: String): ContactorDto?

    @GetMapping("/student")
    override fun load(@RequestParam studentEmail: String): StudentDto?

}
