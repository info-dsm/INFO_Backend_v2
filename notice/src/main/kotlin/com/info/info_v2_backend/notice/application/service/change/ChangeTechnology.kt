package com.info.info_v2_backend.notice.application.service.change

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.change.ChangeTechnologyUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.technology.LoadTechnologyPort
import com.info.info_v2_backend.notice.application.port.output.technology.RemoveTechnologyUsagePort
import com.info.info_v2_backend.notice.application.port.output.technology.SaveTechnologyUsagePort
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import org.springframework.stereotype.Service

@Service
class ChangeTechnology(
    private val saveTechnologyUsagePort: SaveTechnologyUsagePort,
    private val loadNoticePort: LoadNoticePort,
    private val loadTechnologyPort: LoadTechnologyPort,
    private val removeTechnologyUsagePort: RemoveTechnologyUsagePort
): ChangeTechnologyUsecase {
    override fun change(noticeId: String, technologyList: List<String>) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        removeTechnologyUsagePort.removeByNoticeId(notice.id)
        technologyList.map {
            val technology = loadTechnologyPort.load(it)
                ?: return@map

            saveTechnologyUsagePort.save(
                TechnologyUsage(
                    technology,
                    notice
                )
            )
        }

    }


}