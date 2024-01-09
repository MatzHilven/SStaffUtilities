package com.matzhilven.staffutilities.punishment.severity

import java.time.Duration

data class OffenceLevel(
    val type: OffenceType,
    val duration: Duration,
    val reason: String,
)
