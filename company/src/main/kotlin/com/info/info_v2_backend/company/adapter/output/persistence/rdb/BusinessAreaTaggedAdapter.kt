package com.info.info_v2_backend.company.adapter.output.persistence.rdb

import com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository.BusinessAreaTaggedRepository
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaTaggedByCompanyNumberPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaTaggedPort
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import org.springframework.stereotype.Service
import java.sql.SQLIntegrityConstraintViolationException

@Service
class BusinessAreaTaggedAdapter(
    private val businessAreaTaggedRepository: BusinessAreaTaggedRepository
): SaveBusinessAreaTaggedPort, LoadBusinessAreaTaggedByCompanyNumberPort {

    override fun saveBusinessAreaTagged(businessAreaTagged: BusinessAreaTagged) {
        try {
            businessAreaTaggedRepository.save(businessAreaTagged)
        } catch (e: SQLIntegrityConstraintViolationException) {
            return
        }
    }

    override fun load(companyNumber: String): List<BusinessAreaTagged> {
        return businessAreaTaggedRepository.selectAllByCompanyNumberJPQL(companyNumber)
    }

}
