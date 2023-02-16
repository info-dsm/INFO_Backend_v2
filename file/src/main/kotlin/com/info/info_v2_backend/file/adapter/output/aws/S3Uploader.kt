package com.info.info_v2_backend.file.adapter.output.aws

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.Headers
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.info.info_v2_backend.common.file.dto.type.DocsExt
import com.info.info_v2_backend.common.file.dto.type.FileType
import com.info.info_v2_backend.common.file.dto.type.ImageExt
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.adapter.output.aws.configuration.S3Property
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.util.*

@Component
class S3Uploader (
    private val s3Property: S3Property,
    private val s3: AmazonS3
): UploadFilePort {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun getPresignedUrl(originalFileName: String, contentType: String, rootPathName: String, middlePathName: String): FileDto {
        val fileName = getFileName(rootPathName, middlePathName, originalFileName)
        val ext = getExt(originalFileName)

        val generatePresignedUrlRequest = getGeneratePreSignedUrlRequest("info-dsm", fileName)
        val url = s3.generatePresignedUrl(generatePresignedUrlRequest).toURI()
        log.info("file: $originalFileName URL: $url")
        
        return FileDto(
            url.toString(),
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

    private fun getGeneratePreSignedUrlRequest(bucket: String, fileName: String): GeneratePresignedUrlRequest {
        val generatePresignedUrlRequest = GeneratePresignedUrlRequest(bucket, fileName)
            .withMethod(HttpMethod.PUT)
            .withExpiration(getPreSignedUrlExpiration())
        generatePresignedUrlRequest.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        )
        return generatePresignedUrlRequest
    }

    private fun getPreSignedUrlExpiration(): Date {
        val expiration = Date()
        var expTimeMillis = expiration.time
        expTimeMillis += (1000 * 60 * 2).toLong()
        expiration.time = expTimeMillis
        return expiration
    }



    private fun getFileName(rootPathName: String, middlePathName: String, originalFileName: String): String {
        return "${s3Property.bucketName}/${rootPathName}/${middlePathName}/${originalFileName}"
    }




}