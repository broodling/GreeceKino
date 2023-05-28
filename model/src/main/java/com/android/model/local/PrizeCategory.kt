package com.android.model.local

class PrizeCategory(
    val id: Int,
    val divident: Double,
    val winners: Int,
    val distributed: Double,
    val jackpot: Double,
    val fixed: Double,
    val categoryType: Int,
    val gameType: String,
)