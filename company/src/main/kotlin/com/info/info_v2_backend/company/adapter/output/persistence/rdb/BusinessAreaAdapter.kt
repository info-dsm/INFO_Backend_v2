package com.info.info_v2_backend.company.adapter.output.persistence.rdb

import com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository.BusinessAreaRepository
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaPort
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.stereotype.Service

@Service
class BusinessAreaAdapter(
    private val businessAreaRepository: BusinessAreaRepository
): SaveBusinessAreaPort, LoadBusinessAreaPort {

    override fun saveBusinessArea(businessArea: BusinessArea): BusinessArea {
        return businessAreaRepository.save(businessArea)
    }

    override fun loadBusinessArea(name: String): BusinessArea? {
        return businessAreaRepository.findById(name).orElse(null)
    }

    override fun loadAll(): List<BusinessArea> {
        return businessAreaRepository.findAll()
    }


}