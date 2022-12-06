package com.info.info_v2_backend.common.event

class KafkaTopics {
    companion object {
        const val SAVE_TEACHER = "save-teacher"
        const val SAVE_STUDENT = "save-student"
        const val SAVE_CONTACTOR = "save-contactor"
        const val SEND_EMAIL = "send-email"
        const val REGISTER_COMPANY_FILE = "register-company-file"
        const val UPDATE_LAST_NOTICED_COMPANY = "update-last-noticed-company-year"
        const val UPDATE_NOTICE_APPLIES_COUNT = "add-notice-applies-count"
        const val CHANGE_COMPANY_STATUS = "change-company-status"
    }
}