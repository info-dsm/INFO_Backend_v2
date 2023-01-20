package com.info.info_v2_backend.statistics.presentation

import com.info.info_v2_backend.statistics.business.service.StatisticsService
import com.info.info_v2_backend.statistics.persistance.entity.StatInformation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class StatisticsController(
    private val statisticsService: StatisticsService
) {

    @GetMapping("/{generation}")
    fun statisticsInformation(
        @PathVariable generation: Int
    ): StatInformation {
        return statisticsService.getInfo(generation)
    }

}