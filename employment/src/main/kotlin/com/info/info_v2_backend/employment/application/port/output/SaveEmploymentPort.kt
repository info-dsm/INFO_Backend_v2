package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.employment.domain.Employment

interface SaveEmploymentPort {

    fun saveEmployment(employment: Employment)
}