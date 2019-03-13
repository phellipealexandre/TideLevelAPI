package com.tidelevel.models

data class TideResponse(
    val region: String,
    val tideDays: List<TideDay>
)