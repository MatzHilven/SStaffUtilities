package com.matzhilven.staffutilities.punishment.severity

import java.time.Duration

data class SeverityLevel(
    val type: SeverityType,
    val duration: Duration,
    val reason: String,
)
