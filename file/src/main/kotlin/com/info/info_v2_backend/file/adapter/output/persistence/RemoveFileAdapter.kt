package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.FileRepository
import com.info.info_v2_backend.file.application.port.output.RemoveFilePort
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class RemoveFileAdapter(
    private val fileRepository: FileRepository
): RemoveFilePort {
    override fun remove(fileId: String) {
        try {
            fileRepository.deleteById(fileId)
        } catch (e: EmptyResultDataAccessException) {
            return
        }
    }
}