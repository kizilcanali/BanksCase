package com.alikizilcan.bankscase.data

import com.alikizilcan.bankscase.data.local.model.BankBranchEntity
import com.alikizilcan.bankscase.data.remote.model.BankBranchResponse
import com.alikizilcan.bankscase.infra.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BanksRepository @Inject constructor(
    private val banksDataSource: BankDataSource
) {

    fun getBankBranches(): Flow<Resource<List<BankBranchResponse>>> = flow {
        emit(banksDataSource.getBankBranches())
    }.map {
        Resource.Success(it)
    }

    suspend fun saveBankBranches(bankBranchesList: List<BankBranchEntity>) =
        banksDataSource.insertBankBranches(bankBranchesList)

    suspend fun getBranchesByCity(city: String) : Flow<List<BankBranchEntity>> = flow {
        emit(banksDataSource.getBranchesByCity(city))
    }

    suspend fun getCityNames(): Flow<List<String>> = flow {
        emit(banksDataSource.getCityNames())
    }

}