package com.info.info_v2_backend.auth.adapter.output.event

import com.info.info_v2_backend.auth.application.port.output.SaveUserPort
import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class SaveUserAdapter(
    private val teacherSender: KafkaTemplate<String, TeacherDto>,
    private val studentSender: KafkaTemplate<String, StudentDto>
): SaveUserPort {

    override fun saveTeacherPort(teacher: TeacherDto) {
        teacherSender.send(KafkaTopics.SAVE_TEACHER, teacher)
    }

    override fun saveStudentPort(student: StudentDto) {
        studentSender.send(KafkaTopics.SAVE_STUDENT, student)
    }


}