package com.alikizilcan.bankscase.domain.mapper

import com.alikizilcan.bankscase.data.local.model.BankBranchEntity
import com.alikizilcan.bankscase.data.remote.model.BankBranchResponse
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.orZero
import javax.inject.Inject

class BankBranchEntityMapper @Inject constructor() {
    fun mapFromBankBranch(bankBranchResponse: BankBranchResponse): BankBranchEntity {
        return BankBranchEntity(
            id = bankBranchResponse.id.orZero(),
            city = bankBranchResponse.city.orEmpty(),
            district = bankBranchResponse.district.orEmpty(),
            branch = bankBranchResponse.branch.orEmpty(),
            type = bankBranchResponse.type.orEmpty(),
            bankCode = bankBranchResponse.bankCode.orEmpty(),
            addressName = bankBranchResponse.addressName.orEmpty(),
            address = bankBranchResponse.address.orEmpty(),
            zipCode = bankBranchResponse.zipCode.orEmpty(),
            onOffLine = bankBranchResponse.onOffLine.orEmpty(),
            onOffSite = bankBranchResponse.onOffSite.orEmpty(),
            regionalCoordinator = bankBranchResponse.regionalCoordinator.orEmpty(),
            nearestATM = bankBranchResponse.nearestATM.orEmpty()
        )
    }
}