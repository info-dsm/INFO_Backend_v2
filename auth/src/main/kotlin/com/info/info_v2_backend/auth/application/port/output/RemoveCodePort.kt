package com.info.info_v2_backend.auth.application.port.output

interface RemoveCodePort {

    fun remove(targetEmail: String)
}