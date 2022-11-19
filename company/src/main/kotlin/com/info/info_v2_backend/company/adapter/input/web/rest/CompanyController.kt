package com.info.info_v2_backend.company.adapter.input.web.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.RegisterCompanyRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MaximumCompanyResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MinimumCompanyResponse
import com.info.info_v2_backend.company.application.port.input.*
import com.info.info_v2_backend.company.application.port.input.file.AddCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.input.file.ChangeCompanyFileUsecase
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class CompanyController(
    private val registerCompanyUsecase: RegisterCompanyUsecase,
    private val loadBusinessAreaUsecase: LoadBusinessAreaUsecase,
    private val changeCompanyFileUsecase: ChangeCompanyFileUsecase,
    private val addCompanyFileUsecase: AddCompanyFileUsecase,
    private val removeCompanyFileUsecase: RemoveCompanyFileUsecase,
    private val makeAssociatedUsecase: MakeAssociatedUsecase,
    private val loadCompanyUsecase: LoadCompanyUsecase,
    private val editCompanyUsecase: EditCompanyUsecase
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
        registerCompanyUsecase.register(
            emailCheckCode, request, businessRegisteredCertificate, companyIntroductionFile, companyLogo, companyPhotoList
        )
    }


    @PatchMapping("/{companyNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editCompany(
        @PathVariable companyNumber: String,
        @RequestBody request: EditCompanyRequest
    ) {
        editCompanyUsecase.editCompany(Auth.checkCompanyNumber(companyNumber), request)
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
    ) {
        return changeCompanyFileUsecase.change(businessCertificate, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.BUSINESS_CERTIFICATE)
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
    ) {
        return addCompanyFileUsecase.add(file, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_INTRODUCTION)
    }

    @DeleteMapping("/{companyNumber}/introduction/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeIntroductionFile(
        @PathVariable companyNumber: String,
        @PathVariable fileId: String
    ) {
        return removeCompanyFileUsecase.remove(
            Auth.checkCompanyNumber(companyNumber), fileId, CompanyFileClassificationType.COMPANY_INTRODUCTION
        )
    }

    @PutMapping("/{companyNumber}/logo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeCompanyLogo(@PathVariable companyNumber: String, @RequestPart file: MultipartFile){
        return changeCompanyFileUsecase.change(file, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_LOGO)
    }

    @PutMapping("/{companyNumber}/photo")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCompanyPhoto(@PathVariable companyNumber: String, @RequestPart file: MultipartFile) {
        return addCompanyFileUsecase.add(file, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_PHOTO)
    }

    @DeleteMapping("/{companyNumber}/photo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeCompanyPhoto(@PathVariable companyNumber: String, @RequestParam fileId: String) {
        return removeCompanyFileUsecase.remove(companyNumber, fileId, CompanyFileClassificationType.COMPANY_PHOTO)
    }

    @PostMapping("/associate/{companyNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun makeAssociated(@PathVariable companyNumber: String) {
        if (!Auth.checkIsTeacher()) println("Is Not Teacher")
        makeAssociatedUsecase.makeAssociated(companyNumber)
    }

    @GetMapping("/list")
    fun getMinimumCompanyList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<MinimumCompanyResponse> {
        return loadCompanyUsecase.loadMinimumCompanyList(idx, size)
    }

    @GetMapping("/{companyNumber}")
    fun getMaximumCompany(@PathVariable companyNumber: String): MaximumCompanyResponse {
        return loadCompanyUsecase.loadMaximumCompany(companyNumber)
    }

//    @GetMapping("/{userEmail}/company")
//    fun getMaximumCompanyListByUserEmail(
//        @PathVariable userEmail: String
//    ){
//
//    }

    @GetMapping("/list/{year}")
    fun getRegisteredNoticeCompanyListByYear(
        @PathVariable year: Int,
        @RequestParam idx: Int,
        @RequestParam size: Int
    ): Page<MinimumCompanyResponse> {
        return loadCompanyUsecase.loadMinimumCompanyListByYear(idx, size, year)
    }

    @GetMapping("/dto/{companyNumber}")
    fun getCompanyDto(@PathVariable companyNumber: String): CompanyDto? {
        return loadCompanyUsecase.loadCompanyDto(companyNumber)
    }


    //회시 담당자 수정



}