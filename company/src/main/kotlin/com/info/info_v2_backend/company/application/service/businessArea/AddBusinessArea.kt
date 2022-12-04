package com.info.info_v2_backend.company.application.service.businessArea

import com.info.info_v2_backend.company.application.port.input.businessArea.AddBusinessAreaUsecase
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaPort
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.stereotype.Service

@Service
class AddBusinessArea(
    private val saveBusinessAreaPort: SaveBusinessAreaPort
): AddBusinessAreaUsecase {
    override fun add(name: String) {
        saveBusinessAreaPort.saveBusinessArea(
            BusinessArea(name)
        )
    }
}