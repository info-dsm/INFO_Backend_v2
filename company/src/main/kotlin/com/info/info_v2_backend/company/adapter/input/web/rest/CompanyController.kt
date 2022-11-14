package com.info.info_v2_backend.company.adapter.input.web.rest

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class CompanyController(
    private val registerCompanyPort: RegisterCompanyUsecase
) {


    @GetMapping("/code")
    fun sendEmailCode() {

    }

    @GetMapping("/check")
    fun checkEmailCode() {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCompany(
        @RequestParam emailCheckCode: String,
        @RequestPart(required = true) request: RegisterCompanyRequest,
        @RequestPart(required = true) businessRegisteredCertificate: MultipartFile,
        @RequestPart(required = true) companyIntroductionFile: List<MultipartFile>,
        @RequestPart(required = true) companyLogo: MultipartFile,
        @RequestPart(required = true) companyPhotoList: List<MultipartFile>
    ) {
        registerCompanyPort.register()
    }

    @GetMapping("/hint")
    fun getHint() {

    }

    @PatchMapping("/{companyId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editCompany(@PathVariable companyId: String) {

    }

    @GetMapping("/business-area")
    fun getBusinessAreaList() {

    }

    @PatchMapping("/{companyId}/certificate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeBusinessCertificate(@PathVariable companyId: String) {

    }

    @GetMapping("/certificate")
    fun getBusinessCertificate() {

    }

    @PutMapping("/{companyId}/introduction")
    @ResponseStatus(HttpStatus.CREATED)
    fun addIntroductionFile(){

    }

    @DeleteMapping("/{companyId}/introduction/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeIntroductionFile() {

    }

    @PutMapping("/{companyId}/logo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeCompanyLogo(){

    }

    @PutMapping("/{companyId}/photo")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCompanyPhoto() {

    }

    @DeleteMapping("/{companyId}/photo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeCompanyPhoto() {

    }

    @PostMapping("/associate/{companyId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun makeAssociated() {

    }

    @GetMapping("/list")
    fun getMinimumCompanyList(){

    }

    @GetMapping
    fun getMaximumCompany() {

    }

    @GetMapping("/{userEmail}")
    fun getMaximumCompanyListByUserEmail(){

    }

    @GetMapping("/list/{year}")
    fun getRegisteredNoticeCompanyListByYear(){

    }







}