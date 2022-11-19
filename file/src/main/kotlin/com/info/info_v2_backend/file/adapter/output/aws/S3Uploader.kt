package com.info.info_v2_backend.file.adapter.output.aws

import com.amazonaws.services.s3.AmazonS3Client
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
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@Component
class S3Uploader (
    private val s3Property: S3Property,
    private val s3: AmazonS3Client
): UploadFilePort {

    override fun upload(file: MultipartFile, rootPathName: String, middlePathName: String): FileDto {
        val objectMetadata = ObjectMetadata()
        val bytes: ByteArray = IOUtils.toByteArray(file.inputStream)

        objectMetadata.contentLength = bytes.size.toLong()
        val ext = (file.originalFilename?: file.name).substring((file.originalFilename?:file.name).lastIndexOf(".") + 1)

        var fileType: FileType = FileType.IMAGE
        ImageExt.values().filter { it.extension ==  ext }.map {
            objectMetadata.contentType = it.contentType
        }.ifEmpty {
            DocsExt.values().filter { it.extension == ext }.map {
                objectMetadata.contentType = it.contentType
                fileType = FileType.DOCS
            }
        }.ifEmpty {
            fileType = FileType.UNKNOWN
        }


        val byteArrayInputStream = ByteArrayInputStream(bytes)

        val fileName = "${s3Property.bucketName}/${rootPathName}/${middlePathName}/${file.originalFilename}"

        try {
            s3.putObject(PutObjectRequest(s3Property.bucketName, fileName, byteArrayInputStream, objectMetadata))
        } catch (err: Exception) {
            throw BusinessException(err.message, ErrorCode.BAD_GATEWAY_ERROR)
        }
        return FileDto(
            getFileUrl(fileName),
            fileType,
            ext,
            file.originalFilename.toString()
        )
    }



    fun getFileUrl(fileName: String): String {
        return s3.getResourceUrl(s3Property.bucketName, fileName)
    }



}