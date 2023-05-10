package com.info.info_v2_backend.file.application.port.output.announce

import com.info.info_v2_backend.file.domain.announcement.AnnouncementFile

interface SaveAnnouncementFilePort {

    fun save(announcementFile: AnnouncementFile)
}
