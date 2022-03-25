package com.alikizilcan.bankscase.data.remote.model

import com.google.gson.annotations.SerializedName

data class BankBranchResponse(
    @SerializedName("ID") val id: Int?,
    @SerializedName("dc_SEHIR") val city: String?,
    @SerializedName("dc_ILCE") val district: String?,
    @SerializedName("dc_BANKA_SUBE") val branch: String?,
    @SerializedName("dc_BANKA_TIPI") val type: String?,
    @SerializedName("dc_BANK_KODU") val bankCode: String?,
    @SerializedName("dc_ADRES_ADI") val addressName: String?,
    @SerializedName("dc_ADRES") val address: String?,
    @SerializedName("dc_POSTA_KODU") val zipCode: String?,
    @SerializedName("dc_ON_OFF_LINE") val onOffLine: String?,
    @SerializedName("dc_ON_OFF_SITE") val onOffSite: String?,
    @SerializedName("dc_BOLGE_KOORDINATORLUGU") val regionalCoordinator: String?,
    @SerializedName("dc_EN_YAKIM_ATM") val nearestATM: String?,
)