package com.info.info_v2_backend.statistics.business.service

import com.info.info_v2_backend.statistics.persistance.entity.StatInformation

interface StatisticsService {

    fun getInfo(generation: Int): StatInformation
}