package com.info.info_v2_backend.notice.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.application.port.input.*
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.Path

@RestController
class NoticeController(
    private val createNoticeUsecase: CreateNoticeUsecase,
    private val editNoticeUsecase: EditNoticeUsecase,
    private val loadNoticeUsecase: LoadNoticeUsecase,
    private val removeNoticeUsecase: RemoveNoticeUsecase,
    private val concludeNoticeUsecase: ConcludeNoticeUsecase

){

    @PostMapping
    fun createNotice(
        @RequestBody request: CreateNoticeRequest,
        @RequestParam companyNumber: String
    ){
        return createNoticeUsecase.create(Auth.checkCompanyNumber(companyNumber), request)
    }

    @PatchMapping("/{companyNumber}/{noticeId}")
    fun editNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody request: EditNoticeRequest,
    ) {
        return editNoticeUsecase.edit(noticeId, request, Auth.checkCompanyNumber(companyNumber))
    }

    @DeleteMapping("/{companyNumber}/{noticeId}/conclude")
    fun concludeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        if (!Auth.checkIsTeacher()) throw BusinessException("이 작업은 반드시 선생님만 진행할 수 있습니다.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return concludeNoticeUsecase.conclude(noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}")
    fun removeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        return removeNoticeUsecase.remove(noticeId, companyNumber)
    }

    @GetMapping("/{noticeId}")
    fun getMaximumNotice(@PathVariable noticeId: String): MaximumNoticeResponse {
        return loadNoticeUsecase.loadMaximumNotice(noticeId)
    }

    @GetMapping("/available")
    fun loadAvailableNotice(@RequestParam noticeId: String): NoticeDto? {
        return loadNoticeUsecase.loadAvailableNotice(noticeId)
    }

    @GetMapping("/list")
    fun getMinimumNoticeList(@RequestParam idx: Int, @RequestParam size: Int): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadMinimumNoticeList(idx, size)
    }

}