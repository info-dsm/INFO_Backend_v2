package com.info.info_v2_backend.company.adapter.output.event

import com.info.info_v2_backend.company.application.port.output.UploadFilePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileAdapter: UploadFilePort {

    override fun upload(file: MultipartFile): String {
        TODO("Not yet implemented")
    }
}