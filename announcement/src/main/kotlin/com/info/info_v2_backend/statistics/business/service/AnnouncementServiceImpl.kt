package com.info.info_v2_backend.statistics.business.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.statistics.infra.feign.FileFeignClient
import com.info.info_v2_backend.statistics.persistance.entity.Announcement
import com.info.info_v2_backend.statistics.persistance.entity.AnnouncementType
import com.info.info_v2_backend.statistics.persistance.repository.AnnouncementRepository
import com.info.info_v2_backend.statistics.presentation.dto.request.CreateAnnouncementRequest
import com.info.info_v2_backend.statistics.presentation.dto.response.MaximumAnnouncementResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MinimumAnnouncementResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AnnouncementServiceImpl(
    private val announcementRepository: AnnouncementRepository,
    private val fileFeignClient: FileFeignClient
): AnnouncementService {

    @Transactional
    override fun createAnnouncment(request: CreateAnnouncementRequest): PresignedUrlListResponse {
        val announcement = announcementRepository.save(
            Announcement(
            request.title,
            request.content,
            request.type
        )
        )

        return fileFeignClient.createAnnouncementFile(
            announcement.id?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR),
            request.fileList
        )
    }

    override fun getAnnounceList(idx: Int, size: Int, type: AnnouncementType?): Page<MinimumAnnouncementResponse> {
        type?.let { t: AnnouncementType ->
            return announcementRepository.findAllByType(t, PageRequest.of(idx, size, Sort.by("createdAt").descending())).map {
                a: Announcement ->
                a.toMinimumAnnouncementResponse()
            }
        }?: return announcementRepository.findAll(PageRequest.of(idx, size, Sort.by("createdAt").descending())).map {
            it.toMinimumAnnouncementResponse()
        }
    }

    override fun getAnnounce(id: Long): MaximumAnnouncementResponse {
        return announcementRepository.findByIdOrNull(id)
            ?.toMaximumAnnouncementResponse(
                fileFeignClient.getAnnouncementFileList(id)
            )?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

    override fun getLatestAnnouncement(): MinimumAnnouncementResponse {
        return announcementRepository.findLatestAnnouncement().toMinimumAnnouncementResponse()
    }


}
