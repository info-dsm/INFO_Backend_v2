package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.security.HeaderProperty
import com.info.info_v2_backend.company.application.port.input.introduction.RemoveIntroductionFileUsecase
import com.info.info_v2_backend.company.application.port.output.file.FilePort
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
class RemoveIntroductionFile(
    private val filePort: FilePort
): RemoveIntroductionFileUsecase {

    override fun remove(fileId: String) {
        filePort.remove(
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
                HeaderProperty.COMPANY_NUMBER
            )?: throw BusinessException("토큰이 존재하지 않습니다.", ErrorCode.TOKEN_NEED_ERROR),
            fileId
        )
    }

}