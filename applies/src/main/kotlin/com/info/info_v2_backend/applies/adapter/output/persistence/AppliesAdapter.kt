package com.info.info_v2_backend.applies.adapter.output.persistence

import com.info.info_v2_backend.applies.adapter.output.persistence.repository.AppliesRepository
import com.info.info_v2_backend.applies.application.port.output.save.SaveAppliesPort
import com.info.info_v2_backend.applies.application.port.output.cancel.CancelApplyPort
import com.info.info_v2_backend.applies.application.port.output.load.LoadAppliesPort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AppliesAdapter(
    private val appliesRepository: AppliesRepository
): SaveAppliesPort, CancelApplyPort, LoadAppliesPort {

    override fun save(applies: Applies): Applies {
        return appliesRepository.save(applies)
    }

    override fun cancelApply(noticeId: String, studentEmail: String) {
        appliesRepository.deleteByNoticeAndApplicant(noticeId, studentEmail)
    }

    override fun loadAppliesList(noticeId: String, status: AppliesStatus): List<Applies> {
        return appliesRepository.findByNoticeAndStatus(noticeId, status)
    }

    override fun loadApplies(appliesId: String): Applies? {
        return appliesRepository.findByIdOrNull(appliesId)
    }

    override fun loadAppliesByNoticeAndStudentEmail(noticeId: String, studenEmail: String): Applies? {
        return appliesRepository.findByNoticeAndApplicant(noticeId, studenEmail).orElse(null)
    }


}