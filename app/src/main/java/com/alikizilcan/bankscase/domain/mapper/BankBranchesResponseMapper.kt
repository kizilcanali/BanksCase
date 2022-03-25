package com.alikizilcan.bankscase.domain.mapper

import com.alikizilcan.bankscase.data.remote.model.BankBranchResponse
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.orZero
import javax.inject.Inject

class BankBranchesResponseMapper @Inject constructor() {
    fun mapFromBanksResponse(bankResponse: BankBranchResponse): BankBranch {
        return BankBranch(
            id = bankResponse.id.orZero(),
            city = bankResponse.city.orEmpty(),
            district = bankResponse.district.orEmpty(),
            branch = bankResponse.branch.orEmpty(),
            type = bankResponse.type.orEmpty(),
            bankCode = bankResponse.bankCode.orEmpty(),
            addressName = bankResponse.addressName.orEmpty(),
            address = bankResponse.address.orEmpty(),
            zipCode = bankResponse.zipCode.orEmpty(),
            onOffLine = bankResponse.onOffLine.orEmpty(),
            onOffSite = bankResponse.onOffSite.orEmpty(),
            regionCoordinator = bankResponse.regionalCoordinator.orEmpty(),
            nearestATM = bankResponse.nearestATM.orEmpty()
        )
    }
}