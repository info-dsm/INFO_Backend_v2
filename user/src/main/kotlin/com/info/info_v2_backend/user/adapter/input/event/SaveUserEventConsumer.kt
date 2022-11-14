package com.info.info_v2_backend.user.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.application.port.`in`.SaveUserUsecase
import com.info.info_v2_backend.user.application.port.out.SaveUserPort
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class SaveUserEventConsumer(
    private val saveUserUsecase: SaveUserUsecase
) {

    @KafkaListener(topics = [KafkaTopics.SAVE_STUDENT], groupId = "info", containerFactory = "studentDtoChangeListener")
    fun saveStudent(dto: StudentDto) {
        saveUserUsecase.saveStudent(dto)
    }

    @KafkaListener(topics = [KafkaTopics.SAVE_TEACHER], groupId = "info", containerFactory = "teacherDtoChangeListener")
    fun saveTeacher(dto: TeacherDto) {
        saveUserUsecase.saveTeacher(dto)
    }

    @KafkaListener(topics = [KafkaTopics.SAVE_CONTACTOR], groupId = "info", containerFactory = "teacherDtoChangeListener")
    fun saveContactor(dto: ContactorDto) {
        saveUserUsecase.saveContactor(dto)
    }
}