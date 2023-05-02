package com.info.info_v2_backend.company.application.service.preference

import com.info.info_v2_backend.company.application.port.input.preference.LoadMyCompanyPreferenceInfoUsecase
import com.info.info_v2_backend.company.application.port.output.preference.LoadCompanyClassificationPort
import org.springframework.stereotype.Service

@Service
class LoadMyCompanyPreferenceInfo(
    private val loadCompanyClassificationPort: LoadCompanyClassificationPort
): LoadMyCompanyPreferenceInfoUsecase {

    override fun load(userEmail: String): String? {
        return loadCompanyClassificationPort.loadByUseremail(
                userEmail
            )?.companyClassification?.meaning
    }
}
