package com.info.info_v2_backend.auth.application.port.output.intergration

import com.info.info_v2_backend.auth.application.port.output.LoadContactorPort
import com.info.info_v2_backend.auth.application.port.output.LoadUserDetailsPort

interface UserFeignPort: LoadUserDetailsPort, LoadContactorPort {
}