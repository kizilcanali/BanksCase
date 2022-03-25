package com.alikizilcan.bankscase.domain

import com.alikizilcan.bankscase.data.BanksRepository
import com.alikizilcan.bankscase.data.remote.model.BankBranchResponse
import com.alikizilcan.bankscase.domain.mapper.BankBranchEntityMapper
import com.alikizilcan.bankscase.domain.mapper.BankBranchMapper
import com.alikizilcan.bankscase.domain.mapper.BankBranchesResponseMapper
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.Resource
import com.alikizilcan.bankscase.infra.map
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BankBranchesUseCase @Inject constructor(
    private val banksRepository: BanksRepository,
    private val bankBranchesResponseMapper: BankBranchesResponseMapper,
    private val bankBranchEntityMapper: BankBranchEntityMapper,
    private val bankBranchMapper: BankBranchMapper
) {

    fun getBankBranches(): Flow<Resource<List<BankBranch>>> {
        return banksRepository.getBankBranches().map { resource ->
            resource.map { branchesList ->
                saveBankBranches(branchesList)
                branchesList.map {
                    bankBranchesResponseMapper.mapFromBanksResponse(it)
                }
            }
        }
            .onStart { emit(Resource.Loading) }
            .catch { emit(Resource.Error) }
    }

    private suspend fun saveBankBranches(bankBranchesList: List<BankBranchResponse>) {
        banksRepository.saveBankBranches(bankBranchesList.map {
            bankBranchEntityMapper.mapFromBankBranch(
                it
            )
        })
    }

    suspend fun getBranchesByCity(city: String): Flow<List<BankBranch>> {
        return banksRepository.getBranchesByCity(city).map { branchesList ->
            branchesList.map {
                bankBranchMapper.mapFromBankBranchEntity(it)
            }
        }
    }

    suspend fun getCityNames(): Flow<Set<String>> {
        return banksRepository.getCityNames().map { list ->
            list.toSortedSet()
        }
    }

}