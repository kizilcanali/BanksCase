package com.alikizilcan.bankscase.data

import com.alikizilcan.bankscase.data.local.BankBranchesDao
import com.alikizilcan.bankscase.data.local.model.BankBranchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BranchDetailRepository @Inject constructor(private val bankBranchesDao: BankBranchesDao) {

    suspend fun getBranchById(id: Int): Flow<BankBranchEntity> = flow {
        emit(bankBranchesDao.getBranchById(id))
    }
}