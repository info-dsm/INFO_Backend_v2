package com.info.info_v2_backend.statistics.business.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.statistics.persistance.entity.statistics.StatInformation
import com.info.info_v2_backend.statistics.persistance.repository.StatInformationRepository
import org.springframework.stereotype.Service


@Service
class StatisticsServiceImpl(
    private val statInformationRepository: StatInformationRepository
): StatisticsService {

    companion object {
        const val FIRST_OUT_YEAR = 2017
    }

    override fun getInfo(generation: Int): StatInformation {
        return statInformationRepository.findById(FIRST_OUT_YEAR + generation - 1).orElse(null)?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR)
    }


}