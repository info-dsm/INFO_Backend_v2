package com.info.info_v2_backend.notice.application.service.noticePreference

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.noticePreference.SetNoticePreferenceUsecase
import com.info.info_v2_backend.notice.application.port.output.noticePreference.LoadNoticePreferencePort
import com.info.info_v2_backend.notice.application.port.output.noticePreference.SaveNoticePreferencePort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreference
import org.springframework.stereotype.Service

@Service
class SetNoticePreference(
    private val saveNoticePreferencePort: SaveNoticePreferencePort,
    private val loadSmallClassificationPort: LoadSmallClassificationPort
): SetNoticePreferenceUsecase {

    override fun setNoticePreference(smallClassification: String, userEmail: String) {
        saveNoticePreferencePort.merge(
            NoticePreference(
                loadSmallClassificationPort.loadSmallClassification(smallClassification)?: throw BusinessException(
                    errorCode = ErrorCode.NO_DATA_FOUND_ERROR
                ),
                userEmail
            )
        )
    }

}
