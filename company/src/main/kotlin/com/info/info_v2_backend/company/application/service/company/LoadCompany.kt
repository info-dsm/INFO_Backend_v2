package com.info.info_v2_backend.company.application.service.company

import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.CompanyIntroductionResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MaximumCompanyResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MinimumCompanyResponse
import com.info.info_v2_backend.company.application.port.input.company.LoadCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.preference.LoadCompanyClassificationPort
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SearchCompanyPort
import com.info.info_v2_backend.company.application.port.output.employment.LoadEmploymentPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import com.info.info_v2_backend.company.application.port.output.user.LoadUserPort
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.classification.CompanyClassification
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class LoadCompany(
    private val loadCompanyPort: LoadCompanyPort,
    private val companyFilePort: CompanyFilePort,
    private val loadUserPort: LoadUserPort,
    private val loadEmploymentPort: LoadEmploymentPort,
    private val searchCompanyDocumentPort: SearchCompanyPort,
    private val loadCompanyClassificationPort: LoadCompanyClassificationPort
): LoadCompanyUsecase {

    override fun loadMinimumCompanyList(idx: Int, size: Int): Page<MinimumCompanyResponse> {
        val companyList = loadCompanyPort.loadAllCompanyList(idx, size).map {
            return@map it.toMinimumCompanyResponse(
                getCompanyIntroductionResponse(
                    it
                ),
                loadEmploymentPort.loadEmploymentList(it.companyNumber).size
            )

        }
        return companyList
    }

    override fun loadCustomizedMinimumCompanyList(
        idx: Int,
        size: Int,
        userEmail: String
    ): Page<MinimumCompanyResponse> {
        return loadCompanyPort.loadCompanyListByCompanyClassification(idx, size,
            loadCompanyClassificationPort.loadByUseremail(userEmail)?.companyClassification?: CompanyClassification.values()[(0..1).random()]
        ).map {
            it.toMinimumCompanyResponse(
                getCompanyIntroductionResponse(it),
                loadEmploymentPort.loadEmploymentList(it.companyNumber).size?: 0
            )
        }
    }

    override fun loadMinimumCompanyListByYear(idx: Int, size: Int, year: Int): Page<MinimumCompanyResponse> {
        return loadCompanyPort.loadAllCompanyListByYear(idx, size, year).map {
            it.toMinimumCompanyResponse(
                getCompanyIntroductionResponse(it),
                loadEmploymentPort.loadEmploymentList(it.companyNumber).size?:0
            )
        }
    }

    override fun loadMaximumCompany(companyNumber: String): MaximumCompanyResponse {
        val company = loadCompanyPort.loadCompany(companyNumber)?: throw BusinessException("회사를 조회하지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        val contactor = loadUserPort.loadContactor(companyNumber)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
        return company.toMaximumCompanyResponse(contactor, getCompanyIntroductionResponse(company))
    }

    override fun loadCompanyDto(companyNumber: String): CompanyDto? {
        return loadCompanyPort.loadCompany(companyNumber)?.let {
            company: Company ->
            val information = getCompanyIntroductionResponse(company)
            return@let company.toCompanyDto(information.companyLogo.fileUrl)
        }
    }

    override fun searchCompany(idx: Int, size: Int, query: String): Page<MinimumCompanyResponse> {
        return searchCompanyDocumentPort.search(
            idx, size, query
        ).map {
            company: Company ->
            val company = loadCompanyPort.loadCompany(company.companyNumber)?: let {
                throw BusinessException(errorCode = ErrorCode.BAD_GATEWAY_ERROR)
            }
            return@map company.toMinimumCompanyResponse(
                getCompanyIntroductionResponse(company),
                loadEmploymentPort.loadEmploymentList(company.companyNumber).size?:0
            )
        }
    }

    override fun loadCompanyThumbnailList(companyNumber: String): MutableList<String> {
        val thumbnailList: MutableList<String> = companyFilePort.loadCompanyPhotoFile(companyNumber).map {
            it.fileUrl
        }.toMutableList()
        return thumbnailList
    }

    private fun getCompanyIntroductionResponse(company: Company): CompanyIntroductionResponse {
        val companyFileList = companyFilePort.loadCompanyPhotoFile(company.companyNumber)
        val introductionResponse = CompanyIntroductionResponse(
            company.companyIntroduction.introduction,
            CompanyFileResponse(company.companyNumber, CompanyFileClassificationType.BUSINESS_CERTIFICATE),
            ArrayList(),
            CompanyFileResponse(company.companyNumber, CompanyFileClassificationType.COMPANY_LOGO),
            ArrayList()
        )

        companyFileList.map {
            introductionResponse.addFile(it)
            println("${it.companyNumber}, ${it.fileName}, ${it.companyFileClassificationType}")
        }

        return introductionResponse
    }

}
