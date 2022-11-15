package com.info.info_v2_backend.company.adapter.input.web.rest

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.introduction.AddIntroductionFileUsecase
import com.info.info_v2_backend.company.application.port.input.ChangeBusinessCertificatePort
import com.info.info_v2_backend.company.application.port.input.LoadBusinessAreaUsecase
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class CompanyController(
    private val registerCompanyPort: RegisterCompanyUsecase,
    private val loadBusinessAreaUsecase: LoadBusinessAreaUsecase,
    private val changeBusinessCertificatePort: ChangeBusinessCertificatePort,
    private val addIntroductionFileUsecase: AddIntroductionFileUsecase
) {


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCompany(
        @RequestParam emailCheckCode: String,
        @RequestPart(required = true) request: RegisterCompanyRequest,
        @RequestPart(required = true) businessRegisteredCertificate: MultipartFile,
        @RequestPart(required = true) companyIntroductionFile: List<MultipartFile>,
        @RequestPart(required = true) companyLogo: MultipartFile,
        @RequestPart(required = true) companyPhotoList: List<MultipartFile>
    ) {
        registerCompanyPort.register(
            emailCheckCode, request, businessRegisteredCertificate, companyIntroductionFile, companyLogo, companyPhotoList
        )
    }


    @PatchMapping("/{companyId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editCompany(@PathVariable companyId: String) {

    }

    @GetMapping("/business-area")
    fun getBusinessAreaList(@RequestParam companyNumber: String): List<BusinessArea> {
        return loadBusinessAreaUsecase.loadByCompanyNumber(companyNumber)
    }

    @PatchMapping("/{companyNumber}/certificate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeBusinessCertificate(
        @PathVariable companyNumber: String,
        @RequestPart businessCertificate: MultipartFile
    ): String {
        return changeBusinessCertificatePort.change(businessCertificate, companyNumber)
    }

    @GetMapping("/certificate")
    fun getBusinessCertificate(): List<BusinessArea> {
        return loadBusinessAreaUsecase.loadAll()
    }

    @PutMapping("/{companyNumber}/introduction")
    @ResponseStatus(HttpStatus.CREATED)
    fun addIntroductionFile(
        @PathVariable companyNumber: String,
        @RequestPart file: MultipartFile
    ): String {
        return addIntroductionFileUsecase.add(file, companyNumber)
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

    @GetMapping("/{companyId}")
    fun getMaximumCompany() {

    }

    @GetMapping("/{userEmail}/company")
    fun getMaximumCompanyListByUserEmail(){

    }

    @GetMapping("/list/{year}")
    fun getRegisteredNoticeCompanyListByYear(){

    }







}