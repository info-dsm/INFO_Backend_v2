package com.info.info_v2_backend.email.domain.content

import com.info.info_v2_backend.common.email.EmailTemplateType
import org.springframework.ui.Model
import javax.persistence.*

@Embeddable
class EmailContent(
    title: String,
    templateType: EmailTemplateType,
    map: Map<String, String>?,
    text: String?
) {
    @Column(name = "email_title", nullable = false, length = 100)
    val title: String = title

    @Column(name = "template_type", nullable = false, length = 2)
    @Enumerated(EnumType.ORDINAL)
    val templateType = templateType

    @Convert(converter = EmailDataConverter::class)
    val model: Map<String, String>? = map

    @Column(name = "email_text", nullable = true)
    var text: String? = text

}