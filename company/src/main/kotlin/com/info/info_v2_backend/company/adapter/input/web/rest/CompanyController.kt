package com.info.info_v2_backend.company.adapter.input.web.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.RegisterCompanyRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MaximumCompanyResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MinimumCompanyResponse
import com.info.info_v2_backend.company.application.port.input.*
import com.info.info_v2_backend.company.application.port.input.businessArea.AddBusinessAreaUsecase
import com.info.info_v2_backend.company.application.port.input.businessArea.LoadBusinessAreaUsecase
import com.info.info_v2_backend.company.application.port.input.preference.SetCompanyClassificationPreferenceUsecase
import com.info.info_v2_backend.company.application.port.input.file.AddCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.input.file.ChangeCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.input.preference.LoadMyCompanyPreferenceInfoUsecase
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.classification.CompanyClassification
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class CompanyController(
    private val registerCompanyUsecase: RegisterCompanyUsecase,
    private val loadBusinessAreaUsecase: LoadBusinessAreaUsecase,
    private val changeCompanyFileUsecase: ChangeCompanyFileUsecase,
    private val addCompanyFileUsecase: AddCompanyFileUsecase,
    private val removeCompanyFileUsecase: RemoveCompanyFileUsecase,
    private val makeAssociatedUsecase: MakeAssociatedUsecase,
    private val loadCompanyUsecase: LoadCompanyUsecase,
    private val editCompanyUsecase: EditCompanyUsecase,
    private val makeLeadingUsecase: MakeLeadingUsecase,
    private val addBusinessAreaUsecase: AddBusinessAreaUsecase,
    private val countCompanyUsecase: CountCompanyUsecase,
    private val setCompanyClassificationPreferenceUsecase: SetCompanyClassificationPreferenceUsecase,
    private val loadMyCompanyPreferenceInfoUsecase: LoadMyCompanyPreferenceInfoUsecase
) {

    @GetMapping("/count")
    fun getCompanyCount(): Int {
        return countCompanyUsecase.count()
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCompany(
        @RequestBody(required = true) request: RegisterCompanyRequest
    ): PresignedUrlListResponse {
        return registerCompanyUsecase.register(
            request
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
    fun getBusinessAreaList(): List<BusinessArea> {
        return loadBusinessAreaUsecase.loadAll()
    }

    @PutMapping("/business-area")
    fun addBusinessArea(@RequestParam name: String) {
        if (Auth.checkIsTeacher()) return addBusinessAreaUsecase.add(name)
    }

    @PatchMapping("/{companyNumber}/certificate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeBusinessCertificate(
        @PathVariable companyNumber: String,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        return changeCompanyFileUsecase.change(request, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.BUSINESS_CERTIFICATE)
    }


    @PutMapping("/{companyNumber}/introduction")
    @ResponseStatus(HttpStatus.CREATED)
    fun addIntroductionFile(
        @PathVariable companyNumber: String,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        return addCompanyFileUsecase.add(request, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_INTRODUCTION)
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
    fun changeCompanyLogo(
        @PathVariable companyNumber: String,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse{
        return changeCompanyFileUsecase.change(request, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_LOGO)
    }

    @PutMapping("/{companyNumber}/photo")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCompanyPhoto(
        @PathVariable companyNumber: String,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        return addCompanyFileUsecase.add(request, Auth.checkCompanyNumber(companyNumber), CompanyFileClassificationType.COMPANY_PHOTO)
    }

    @DeleteMapping("/{companyNumber}/photo/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeCompanyPhoto(@PathVariable companyNumber: String, @PathVariable fileId: String) {
        return removeCompanyFileUsecase.remove(companyNumber, fileId, CompanyFileClassificationType.COMPANY_PHOTO)
    }

    @PostMapping("/associate/{companyNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun makeAssociated(@PathVariable companyNumber: String) {
        if (!Auth.checkIsTeacher()) println("Is Not Teacher")
        makeAssociatedUsecase.makeAssociated(companyNumber)
    }

    @GetMapping("/custom/preference")
    fun getMyCompanyPreference(): String? {
        Auth.getUserEmail()?.let {
            return loadMyCompanyPreferenceInfoUsecase.load(it)
        }?: return null
    }

    @PostMapping("/custom")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun setCustomizedCompanyClassification(
        @RequestParam classification: CompanyClassification
    ) {
        Auth.getUserEmail()?.let {
            return setCompanyClassificationPreferenceUsecase.set(it, classification)
        }?: throw BusinessException(errorCode = ErrorCode.TOKEN_NEED_ERROR)
    }

    @GetMapping("/custom")
    fun getCustomizedMinimumCompanyList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<MinimumCompanyResponse> {
        Auth.getUserEmail()?.let {
            return loadCompanyUsecase.loadCustomizedMinimumCompanyList(idx, size, it)
        }?: throw BusinessException(errorCode = ErrorCode.TOKEN_NEED_ERROR)
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

    @GetMapping("/list/{year}")
    fun getRegisteredNoticeCompanyListByYear(
        @PathVariable year: Int,
        @RequestParam idx: Int,
        @RequestParam size: Int
    ): Page<MinimumCompanyResponse> {
        return loadCompanyUsecase.loadMinimumCompanyListByYear(idx, size, year)
    }

    @GetMapping("/search")
    fun searchCompany(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam name: String
    ): Page<MinimumCompanyResponse> {
        return loadCompanyUsecase.searchCompany(idx, size, name)
    }

    //회시 담당자 수정

    @PostMapping("/leading/{companyNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun makeLeading(@PathVariable companyNumber: String) {
        return makeLeadingUsecase.makeLeading(companyNumber)
    }

    @DeleteMapping("/leading/{companyNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelLeading(@PathVariable companyNumber: String) {
        return makeLeadingUsecase.cancelLeading(companyNumber)
    }



    //Internal API
    @GetMapping("/dto/{companyNumber}")
    fun getCompanyDto(@PathVariable companyNumber: String): CompanyDto? {
        if (Auth.checkIsSystem()) return loadCompanyUsecase.loadCompanyDto(companyNumber)
        else return null
    }

    @GetMapping("/thumbnail/{companyNumber}")
    fun getCompanyThumbnailList(
        @PathVariable companyNumber: String
    ): MutableList<String> {
        if (Auth.checkIsSystem()) return loadCompanyUsecase.loadCompanyThumbnailList(companyNumber)
        else return ArrayList()
    }

}
