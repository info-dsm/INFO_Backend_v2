package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import com.info.info_v2_backend.notice.domain.support.MealSupport
import com.info.info_v2_backend.notice.domain.support.Pay
import com.info.info_v2_backend.notice.domain.support.Welfare
import com.info.info_v2_backend.notice.domain.support.WorkTime
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.time.LocalDate

class NoticeMapper: RowMapper<Notice> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Notice? {
        try {
            val notice = Notice(
                rs.getString("id"),
                NoticeCompany(
                    rs.getString("company_number")
                ),
                RecruitmentBigClassification(
                    rs.getString("notice_big_classification")
                ),
                RecruitmentSmallClassification(
                    rs.getString("notice_small_classification"),
                    RecruitmentBigClassification(
                        rs.getString("notice_big_classification")
                    )
                ),
                rs.getString("detail_business_description"),
                rs.getInt("number_of_emplyee"),
                rs.getInt("recruitment_business_grade_cut_line"),
                WorkTime(
                    rs.getInt("until_commute_start_time")
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
                rs.getBoolean("notice_is_conclude"),
                rs.getDate("created_at").toLocalDate(),
                rs.getDate("updated_at").toLocalDate()
            )
            return notice
        } catch (e: Exception) {
            return null
        }
    }


}