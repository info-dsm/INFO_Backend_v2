package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.file.application.port.input.applies.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.output.RemoveFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.applies.LoadResumePort
import com.info.info_v2_backend.file.application.port.output.applies.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UploadResume(
    private val saveResumeFilePort: SaveResumeFilePort,
    private val uploadFilePort: UploadFilePort,
    private val removeFilePort: RemoveFilePort,
    private val loadResumePort: LoadResumePort
): UploadResumeUsecase {

    @Async
    override fun uploadResume(resume: MultipartFile, noticeId: String, studentEmail: String) {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(resume, "NOTICE/${noticeId}", "RESUME/${fileId}")
        val resume = Resume(
            fileId,
            dto,
            ResumeNotice(
                noticeId
            ),
            ResumeStudent(
                studentEmail
            )
        )
        loadResumePort.load(noticeId, studentEmail)?.let {
            removeFilePort.remove(it.id)
        }

        saveResumeFilePort.save(resume)
    }
}