package com.alikizilcan.bankscase.data


import com.alikizilcan.bankscase.data.local.BankBranchesDao
import com.alikizilcan.bankscase.data.local.model.BankBranchEntity
import com.alikizilcan.bankscase.data.remote.BanksApi
import javax.inject.Inject

class BankDataSource @Inject constructor(
    private val banksApi: BanksApi,
    private val bankBranchesDao: BankBranchesDao
) {

    suspend fun getBankBranches() = banksApi.getBankBranches()

    suspend fun insertBankBranches(bankBranchesList: List<BankBranchEntity>) =
        bankBranchesDao.insertBankBranches(bankBranchesList)

    suspend fun getBranchesByCity(city: String) = bankBranchesDao.getBranchesByCity(city)

    suspend fun getCityNames() = bankBranchesDao.getCityNames()

}