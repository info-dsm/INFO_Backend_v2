package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc

import com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper.NoticeMapper
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.sql.DataSource


@Service
class NoticeJdbcTemplate(
    private val jdbcTemplate: JdbcTemplate
): LoadNoticePort {
    override fun loadNotice(noticeId: String): Notice? {
        val query = "select * from notice where notice_is_delete = false limit 1;"
        val result = jdbcTemplate.query(query, NoticeMapper())
        return result[0]
    }

    override fun loadOnDateAndStatusNoticeList(
        idx: Int,
        size: Int,
        date: LocalDate,
        status: NoticeWaitingStatus,
    ): Page<Notice> {
        val page = PageRequest.of(idx, size, Sort.by("created_at").descending())

        val order: Sort.Order = page.getSort().toList().get(0)
        val notice: List<Notice> = jdbcTemplate.query(
            "select * from notice " +
                    "where notice_is_delete = false " +
                    "and ${Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())} " +
                    "between start_date and end_date " +
                    "and notice_is_approve = ${status.name} " +
                    "order by ${order.property} ${order.direction.name} limit ${page.offset}, ${page.pageSize}",
            NoticeMapper()
        )
        return PageImpl(notice, page, count())
    }

    fun count(): Long {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM notice", Long::class.java)!!
    }


}