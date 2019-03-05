package com.tidelevel.models

data class TideDay(
    val date: String,
    val levelList: List<TideLevel>
)