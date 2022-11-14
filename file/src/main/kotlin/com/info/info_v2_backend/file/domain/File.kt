package com.info.info_v2_backend.file.domain

import com.info.info_v2_backend.common.file.type.FileType
import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.util.UUID
import javax.persistence.*


@Where(clause = "file_is_deleted = false")
@SQLDelete(sql = "UPDATE `file` SET file_is_deleted = true where id = ?")
@Table(name = "file")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "file_type", length = 50)
@Entity
abstract class File(
    fileUrl: String,
    fileType: FileType,
    extension: String,
    fileName: String
): TimeEntity() {

    @Id
    @Column(name = "file_id", nullable = false)
    val id: String = UUID.randomUUID().toString()

    @Column(name = "file_url", nullable = false, length = 500)
    var fileUrl: String = fileUrl
        protected set

    @Column(name = "file_name", nullable = false)
    val fileName: String = fileName

    @Column(name = "file_content_type", nullable = false)
    var fileContentType: FileType = fileType
        protected set

    @Column(name = "file_extension", nullable = false)
    var extension: String = extension
        protected set

    @Column(name = "file_is_deleted")
    var isDeleted: Boolean = false
        protected set

}