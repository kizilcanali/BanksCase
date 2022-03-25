package com.alikizilcan.bankscase.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branches")
data class BankBranchEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "district") val district: String?,
    @ColumnInfo(name = "branch") val branch: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "bankCode") val bankCode: String?,
    @ColumnInfo(name = "addressName") val addressName: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "zipCode") val zipCode: String?,
    @ColumnInfo(name = "onOffLine") val onOffLine: String?,
    @ColumnInfo(name = "onOffSite") val onOffSite: String?,
    @ColumnInfo(name = "regionalCoordinator") val regionalCoordinator: String?,
    @ColumnInfo(name = "nearestATM") val nearestATM: String?,
)