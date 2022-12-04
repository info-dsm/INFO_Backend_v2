package com.info.info_v2_backend.company.application.service.businessArea

import com.info.info_v2_backend.company.application.port.input.businessArea.LoadBusinessAreaUsecase
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaTaggedByCompanyNumberPort
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.stereotype.Service

@Service
class LoadBusinessArea(
    private val loadBusinessAreaTaggedByCompanyNumberPort: LoadBusinessAreaTaggedByCompanyNumberPort,
    private val loadBusinessAreaPort: LoadBusinessAreaPort
): LoadBusinessAreaUsecase {


    override fun loadAll(): List<BusinessArea> {
        return loadBusinessAreaPort.loadAll()
    }
}