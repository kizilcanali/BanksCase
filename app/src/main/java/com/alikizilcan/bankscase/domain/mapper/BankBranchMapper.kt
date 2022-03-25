package com.alikizilcan.bankscase.domain.mapper

import com.alikizilcan.bankscase.data.local.model.BankBranchEntity
import com.alikizilcan.bankscase.domain.model.BankBranch
import javax.inject.Inject

class BankBranchMapper @Inject constructor(){
    fun mapFromBankBranchEntity(entity: BankBranchEntity) : BankBranch{
        return BankBranch(
            id = entity.id,
            city = entity.city.orEmpty(),
            district = entity.district.orEmpty(),
            branch = entity.branch.orEmpty(),
            type = entity.type.orEmpty(),
            bankCode = entity.bankCode.orEmpty(),
            addressName = entity.addressName.orEmpty(),
            address = entity.address.orEmpty(),
            zipCode = entity.zipCode.orEmpty(),
            onOffLine = entity.onOffLine.orEmpty(),
            onOffSite = entity.onOffSite.orEmpty(),
            regionCoordinator = entity.regionalCoordinator.orEmpty(),
            nearestATM = entity.nearestATM.orEmpty()
        )
    }
}