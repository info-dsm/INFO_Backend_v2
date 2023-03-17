package com.info.info_v2_backend.applies.adapter.output.persistence

import com.info.info_v2_backend.applies.adapter.output.persistence.repository.AppliesRepository
import com.info.info_v2_backend.applies.application.port.output.applies.CancelApplyPort
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.stereotype.Service

@Service
class AppliesPersistenceAdapter(
    private val appliesRepository: AppliesRepository
): LoadAppliesPort, CancelApplyPort, SaveAppliesPort {

    override fun save(applies: Applies): Applies {
        return appliesRepository.save(applies)
    }

    override fun loadAppliesList(noticeId: String, status: AppliesStatus?): List<Applies> {
        status?.let {
            return appliesRepository.findByNoticeAndStatus(noticeId, status.name)
        }?: return appliesRepository.findByNotice(noticeId)
    }

    override fun loadApplies(appliesId: String): Applies? {
        return appliesRepository.findById(appliesId).orElse(null)
    }

    override fun loadAppliesByNoticeAndStudentEmail(noticeId: String, studentEmail: String): Applies? {
        return appliesRepository.findByNoticeAndApplicant(noticeId, studentEmail).orElse(null)
    }


    override fun cancelApply(noticeId: String, studentEmail: String) {
        appliesRepository.deleteByIdAndAndApplicant(noticeId, studentEmail)
    }

    override fun loadEveryAppliesByStatus(status: AppliesStatus): List<Applies> {
        return appliesRepository.findByStatus(status)
    }

    override fun loadAppliesByStudentEmail(studentEmail: String): List<Applies> {
        return appliesRepository.findByApplicant(studentEmail)
    }
}