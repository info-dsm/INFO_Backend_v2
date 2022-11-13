package com.info.info_v2_backend.user.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.user.adapter.input.event.configuration.KafkaProperty
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.application.port.out.SaveUserPersistencePort
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class SaveUserEventConsumer(
    private val saveUserPersistencePort: SaveUserPersistencePort
) {

    @KafkaListener(topics = [KafkaTopics.SAVE_STUDENT], groupId = "foo", containerFactory = "teacherDtoChangeListener")
    fun saveStudent(dto: StudentDto) {
        //비밀번호 해싱해야됨.
        //saveUserPersistencePort.saveStudent(dto)
    }
}