package com.alikizilcan.bankscase.domain

import com.alikizilcan.bankscase.data.BranchDetailRepository
import com.alikizilcan.bankscase.domain.mapper.BankBranchMapper
import com.alikizilcan.bankscase.domain.model.BankBranch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BranchDetailUseCase @Inject constructor(
    private val branchDetailRepository: BranchDetailRepository,
    private val bankBranchMapper: BankBranchMapper
) {

    suspend fun getBankBranchById(id: Int): Flow<BankBranch> {
        return branchDetailRepository.getBranchById(id).map {
            bankBranchMapper.mapFromBankBranchEntity(it)
        }
    }
}