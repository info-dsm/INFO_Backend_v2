package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper.*
import com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper.vo.NoticeVo
import com.info.info_v2_backend.notice.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.notice.application.port.output.LoadWithConditionPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationUsagePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class LoadNoticeAdapter(
    private val jdbcTemplate: JdbcTemplate,
    private val loadSmallClassificationUsagePort: LoadSmallClassificationUsagePort,
    private val loadSmallClassificationPort: LoadSmallClassificationPort,
    private val loadCompanyPort: LoadCompanyPort
): LoadWithConditionPort {

    override fun loadBeforeEndDateAndStatusNoticeList(
        idx: Int,
        size: Int,
        date: LocalDate,
        status: NoticeWaitingStatus
    ): Page<MinimumNoticeResponse> {
        val page = PageRequest.of(idx, size, Sort.by("created_at").descending())
        val noticeList: List<NoticeVo> = jdbcTemplate.query(
            NoticeQueryBlocks.selectNoticeByDateIsBeforeEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPaging(
                date, status, page
            ),
            JdbcNoticeVoMapper()
        )
        return PageImpl(noticeList.map {
            return@map it.toMinimumNoticeResponse(
                loadSmallClassificationUsagePort.loadAllByNoticeId(it.id).map usage@ {
                usage: RecruitmentSmallClassificationUsage ->
                    return@usage ClassificationResponse(
                        usage.smallClassification.bigClassification.toBigClassificationResponse(),
                        usage.smallClassification.name
                    )
                }.toMutableList(),
                loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
            )
        }, page, count(NoticeQueryBlocks.selectNoticeByDateIsBeforeEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPagingCount(date, status)))
    }

    override fun loadAfterEndDateAndStatusNoticeList(
        idx: Int,
        size: Int,
        date: LocalDate,
        status: NoticeWaitingStatus
    ): Page<MinimumNoticeResponse> {
        val page = PageRequest.of(idx, size, Sort.by("created_at").descending())
        val noticeList: List<NoticeVo> = jdbcTemplate.query(
            NoticeQueryBlocks.selectNoticeByDateIsAfterEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPaging(
                date, status, page
            ),
            JdbcNoticeVoMapper()
        )
        return PageImpl(noticeList.map {
            return@map it.toMinimumNoticeResponse(
                loadSmallClassificationUsagePort.loadAllByNoticeId(it.id).map usage@ {
                        usage: RecruitmentSmallClassificationUsage ->
                    return@usage ClassificationResponse(
                        usage.smallClassification.bigClassification.toBigClassificationResponse(),
                        usage.smallClassification.name
                    )
                }.toMutableList(),
                loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
            )
        }, page, count(NoticeQueryBlocks.selectNoticeByDateIsAfterEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPagingCount(date, status)))
    }

    private fun loadLanguageList(noticeId: String): MutableList<LanguageResponse> {
        val query = "select * from language_usage where notice_id = \"$noticeId\""
        val languageResponseList: MutableList<LanguageResponse> = jdbcTemplate.query(
            query,
            JdbcLanguageResponseDtoMapper()
        )
        return languageResponseList
    }

    private fun loadTechnologyUsageList(noticeId: String): MutableList<TechnologyResponse> {
        val query = "select * from technology_usage where notice_id = \"$noticeId\""
        val technologyResponseList: MutableList<TechnologyResponse> = jdbcTemplate.query(
            query,
            JdbcTechnologyResponseMapper()
        )
        return technologyResponseList
    }

    private fun loadCertificateUsage(noticeId: String): MutableList<CertificateResponse> {
        val query = "select * from certificate_usage where notice_id = \"$noticeId\""
        val certificateResponseList: MutableList<CertificateResponse> = jdbcTemplate.query(
            query,
            JdbcCertificateResponseMapper()
        )
        return certificateResponseList
    }

    private fun loadInterviewProcessMap(noticeId: String): Map<Int, InterviewProcess> {
        val query = "select * from notice_interview_process_map where notice_id = \"$noticeId\""
        val interviewProcessMap: List<Map<Int, InterviewProcess>> = jdbcTemplate.query(
            query,
            JdbcInterviewProcessMapMapper()
        )
        val results: MutableMap<Int, InterviewProcess> = HashMap()
        interviewProcessMap.map {
            map: Map<Int, InterviewProcess> ->
            {
                map.keys.map {
                    results.put(it, map.getValue(it))
                }
            }
        }
        println(results)

        return results
    }

    fun count(query: String?): Long {
        query?.let {
            return jdbcTemplate.queryForObject(query, Long::class.java)!!
        }
        return jdbcTemplate.queryForObject("SELECT count(*) FROM notice", Long::class.java)!!
    }


}