package com.alikizilcan.bankscase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alikizilcan.bankscase.data.local.model.BankBranchEntity

@Database(entities = [BankBranchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bankBranchesDao(): BankBranchesDao
}