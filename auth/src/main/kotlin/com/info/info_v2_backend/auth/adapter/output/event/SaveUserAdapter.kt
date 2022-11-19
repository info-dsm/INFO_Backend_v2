package com.info.info_v2_backend.auth.adapter.output.event

import com.info.info_v2_backend.auth.application.port.output.SaveUserPort
import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class SaveUserAdapter(
    private val teacherSender: KafkaTemplate<String, SaveTeacherDto>,
    private val studentSender: KafkaTemplate<String, SaveStudentDto>
): SaveUserPort {

    override fun saveTeacherPort(teacher: SaveTeacherDto) {
        teacherSender.send(KafkaTopics.SAVE_TEACHER, teacher)
    }

    override fun saveStudentPort(student: SaveStudentDto) {
        studentSender.send(KafkaTopics.SAVE_STUDENT, student)
    }


}