package com.info.info_v2_backend.email.domain.content

import com.fasterxml.jackson.databind.ObjectMapper
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter
class EmailDataConverter(
    private val objectMapper: ObjectMapper
): AttributeConverter<Map<String, String>?, String?> {

    override fun convertToDatabaseColumn(attribute: Map<String, String>?): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Map<String, String>? {
        return objectMapper.readValue(dbData, Map::class.java) as Map<String, String>
    }


}