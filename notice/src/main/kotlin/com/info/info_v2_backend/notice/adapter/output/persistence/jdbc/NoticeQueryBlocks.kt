package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc

import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.time.LocalDate

object NoticeQueryBlocks {

    fun selectNoticeByDateIsBeforeEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPaging(date: LocalDate, status: NoticeWaitingStatus, page: Pageable): String {
        val order: Sort.Order = page.sort.toList()[0]
        return "select * from notice " +
                "where notice_is_delete = false " +
                "and \"${date.year}-${StringUtils.leftPad(date.month.value.toString(), 2)}-${date.dayOfMonth}\" <= end_date " +
                "and notice_is_approve = \"${status.name}\" " +
                "order by ${order.property} ${order.direction.name} limit ${page.offset}, ${page.pageSize}"
    }

    fun selectNoticeByDateIsAfterEndDateAndNoticeWaitingStatusOrderByCreatedAtDescendingPaging(date: LocalDate, status: NoticeWaitingStatus, page: Pageable): String {
        val order: Sort.Order = page.sort.toList()[0]
        return "select * from notice as A " +
                "where A.notice_is_delete = false " +
                "and \"${date.year}-${StringUtils.leftPad(date.month.value.toString(), 2)}-${date.dayOfMonth}\" > A.end_date " +
                "and A.notice_is_approve = \"${status.name}\" " +
                "order by ${order.property} ${order.direction.name} limit ${page.offset}, ${page.pageSize}"
    }


}