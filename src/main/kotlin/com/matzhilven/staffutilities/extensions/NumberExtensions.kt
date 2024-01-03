package com.matzhilven.staffutilities.extensions

import java.text.NumberFormat
import java.util.*

fun <T : Number> T.format() = NumberFormat.getNumberInstance(Locale.US).format(this)!!
