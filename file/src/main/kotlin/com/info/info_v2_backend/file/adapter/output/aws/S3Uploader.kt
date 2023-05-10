package com.info.info_v2_backend.file.adapter.output.aws

import com.info.info_v2_backend.common.file.dto.type.DocsExt
import com.info.info_v2_backend.common.file.dto.type.FileType
import com.info.info_v2_backend.common.file.dto.type.ImageExt
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.adapter.output.aws.configuration.S3Property
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration

@Component
class S3Uploader (
    private val presigner: S3Presigner,
    private val s3Property: S3Property
): UploadFilePort {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun getPresignedUrl(originalFileName: String, contentType: String, rootPathName: String, middlePathName: String, fileId: String): FileDto {
        val fileName = getFileName(rootPathName, "$middlePathName/$fileId", originalFileName)
        val ext = getExt(originalFileName)

        val presignedURL = getGeneratePreSignedUrlRequest(fileName, contentType)

        return FileDto(
            fileId,
            presignedURL,
            getFileType(contentType, ext),
            ext,
            originalFileName
        )
    }

    private fun getExt(originalFileName: String): String {
        return originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
    }

    private fun getFileType(type: String, ext: String): FileType {
        var contentType = type
        var fileType: FileType = FileType.IMAGE
        ImageExt.values().filter { it.extension ==  ext }.map {
            contentType = it.contentType
        }.ifEmpty {
            DocsExt.values().filter { it.extension == ext }.map {
                contentType = it.contentType
                fileType = FileType.DOCS
            }
        }.ifEmpty {
            fileType = FileType.UNKNOWN
        }
        return fileType
    }

    private fun getGeneratePreSignedUrlRequest(fileName: String, contentType: String): String {
        val objectRequest = PutObjectRequest.builder()
            .bucket(s3Property.bucketName)
            .key(fileName)
            .contentType(contentType)
            .build()

        val presignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .putObjectRequest(objectRequest)
            .build()

        val presignedRequest = presigner.presignPutObject(presignRequest)
        val url = presignedRequest.url().toString()

        log.info("url: $url, method: ${presignedRequest.httpRequest().method()}")
        return url
    }



    private fun getFileName(rootPathName: String, middlePathName: String, originalFileName: String): String {
        return "${s3Property.bucketName}/${rootPathName}/${middlePathName}/${originalFileName}"
    }




}
