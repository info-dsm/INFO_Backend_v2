package com.info.info_v2_backend.common.file

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.disk.DiskFileItem
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object FileConvert {

    fun fileToMultipartFileConvert(localFile: File): MultipartFile {
        val fileItem: FileItem = DiskFileItem(
            localFile.name,
            Files.probeContentType(localFile.toPath()),
            false,
            localFile.name,
            localFile.length().toInt(),
            localFile.parentFile
        )
        try {
            IOUtils.copy(FileInputStream(localFile), fileItem.outputStream);
        } catch (ex: IOException) {
            throw BusinessException("파일을 처리하던 중 오류가 발생했습니다.", ErrorCode.BAD_GATEWAY_ERROR)
        }
        return CommonsMultipartFile(fileItem)
    }

    fun multipartFileToFileConvert(file: MultipartFile, path: String): File {
        val localFile: File = File(
            Paths.get(path + file.originalFilename).toAbsolutePath().toString()
        )
        localFile.createNewFile()
        file.transferTo(localFile)
        return localFile
    }

    fun removeLocalFile(path: String) {
        val localFile: File = File(
            Paths.get(path).toAbsolutePath().toString()
        )
        localFile.delete()
    }

}