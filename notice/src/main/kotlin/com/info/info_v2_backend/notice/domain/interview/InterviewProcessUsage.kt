package com.info.info_v2_backend.notice.domain.interview

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class InterviewProcessUsage(
    sequence: Int,
    interviewProcess: InterviewProcess
) {

    @Column(name = "sequence", nullable = false)
    var sequence: Int = sequence
        protected set

    @Column(name = "interview_process", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var interviewProcess = interviewProcess
        protected set

    fun changeInterviewProcess(interviewProcess: InterviewProcess) {
        this.interviewProcess = interviewProcess
    }

    fun pullSequence(sequence: Int) {
        if (this.sequence > sequence) {
            this.sequence = this.sequence - 1
        }
    }

}