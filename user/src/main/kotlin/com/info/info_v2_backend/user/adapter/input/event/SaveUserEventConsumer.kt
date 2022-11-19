package com.info.info_v2_backend.user.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import com.info.info_v2_backend.user.application.port.input.SaveUserUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class SaveUserEventConsumer(
    private val saveUserUsecase: SaveUserUsecase
) {

    @KafkaListener(topics = [KafkaTopics.SAVE_STUDENT], groupId = "info", containerFactory = "studentDtoChangeListener")
    fun saveStudent(dto: SaveStudentDto) {
        saveUserUsecase.saveStudent(dto)
    }

    @KafkaListener(topics = [KafkaTopics.SAVE_TEACHER], groupId = "info", containerFactory = "teacherDtoChangeListener")
    fun saveTeacher(dto: SaveTeacherDto) {
        saveUserUsecase.saveTeacher(dto)
    }

    @KafkaListener(topics = [KafkaTopics.SAVE_CONTACTOR], groupId = "info", containerFactory = "teacherDtoChangeListener")
    fun saveContactor(dto: SaveContactorDto) {
        saveUserUsecase.saveContactor(dto)
    }
}