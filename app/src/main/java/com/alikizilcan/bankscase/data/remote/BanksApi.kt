package com.alikizilcan.bankscase.data.remote

import com.alikizilcan.bankscase.data.remote.model.BankBranchResponse
import retrofit2.http.GET

interface BanksApi {

    @GET("fatiha380/mockjson/main/bankdata")
    suspend fun getBankBranches() : List<BankBranchResponse>

}