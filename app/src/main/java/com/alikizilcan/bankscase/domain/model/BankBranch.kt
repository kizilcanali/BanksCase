package com.alikizilcan.bankscase.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankBranch(
    val id: Int,
    val city: String,
    val district: String,
    val branch: String,
    val type: String,
    val bankCode: String,
    val addressName: String,
    val address: String,
    val zipCode: String,
    val onOffLine: String,
    val onOffSite: String,
    val regionCoordinator: String,
    val nearestATM: String
) : Parcelable