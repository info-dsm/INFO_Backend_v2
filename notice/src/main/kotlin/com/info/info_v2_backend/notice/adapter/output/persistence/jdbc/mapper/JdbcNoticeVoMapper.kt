package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper.vo.NoticeVo
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import com.info.info_v2_backend.notice.domain.support.MealSupport
import com.info.info_v2_backend.notice.domain.support.Pay
import com.info.info_v2_backend.notice.domain.support.Welfare
import com.info.info_v2_backend.notice.domain.support.WorkTime
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class JdbcNoticeVoMapper(
): RowMapper<NoticeVo> {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun mapRow(rs: ResultSet, rowNum: Int): NoticeVo? {
        try {
            val notice = NoticeVo(
                rs.getString("id"),
                NoticeCompany(
                    rs.getString("company_number"),
                    rs.getString("company_name")
                ),
                rs.getString("detail_business_description"),
                rs.getInt("number_of_emplyee"),
                rs.getInt("recruitment_business_grade_cut_line"),
                WorkTime(
                    rs.getInt("commute_start_time"),
                    rs.getInt("commute_end_time"),
                    rs.getBoolean("is_flexible")
                ),
                Pay(
                    rs.getLong("field_training_pay"),
                    rs.getLong("year_pay_start"),
                    rs.getLong("year_pay_end")
                ),
                MealSupport(
                    rs.getLong("meal_support_pay"),
                    rs.getBoolean("breakfast_meal_support"),
                    rs.getBoolean("lunch_meal_support"),
                    rs.getBoolean("dinner_meal_support")
                ),
                Welfare(
                    rs.getBoolean("dormitory_support"),
                    rs.getBoolean("self_development_pay"),
                    rs.getBoolean("equipment_support"),
                    rs.getBoolean("youth_tomorrow_chaeum_deduction"),
                    rs.getBoolean("alternative_military_plan"),
                    rs.getString("else_support")
                ),
                NoticeOpenPeriod(
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                ),
                rs.getString("need_documents"),
                rs.getString("notice_other_features"),
                WorkPlace(
                    rs.getBoolean("is_same_with_company_address"),
                    rs.getString("other_place")
                ),
                rs.getBoolean("is_personal_contact"),
                rs.getBoolean("notice_is_delete"),
                NoticeWaitingStatus.valueOf(rs.getString("notice_is_approve")),
                rs.getInt("applicant_count"),
                rs.getDate("created_at").toLocalDate(),
                rs.getDate("updated_at").toLocalDate()
            )
            return notice
        } catch (e: Exception) {
            log.error(e.message)
            return null
        }
    }


}