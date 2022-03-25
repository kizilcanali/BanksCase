package com.alikizilcan.bankscase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alikizilcan.bankscase.data.local.model.BankBranchEntity

@Dao
interface BankBranchesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBankBranches(bankBranchesList: List<BankBranchEntity>)

    @Query("SELECT * FROM branches WHERE id = :id")
    suspend fun getBranchById(id: Int) : BankBranchEntity

    @Query("SELECT * FROM branches WHERE city = :city")
    suspend fun getBranchesByCity(city: String) : List<BankBranchEntity>

    @Query("SELECT city FROM branches")
    suspend fun getCityNames(): List<String>


}