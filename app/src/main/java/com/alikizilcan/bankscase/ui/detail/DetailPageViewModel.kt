package com.alikizilcan.bankscase.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.alikizilcan.bankscase.domain.BranchDetailUseCase
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val branchDetailUseCase: BranchDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {

    private var _bankBranch: MutableLiveData<BankBranch> = MutableLiveData()
    val bankBranch: LiveData<BankBranch> = _bankBranch

    var branchId: Int = savedStateHandle["branchId"]!!

    init {
        getBranchById()
    }

    private fun getBranchById() {
        viewModelScope.launch {
            branchDetailUseCase.getBankBranchById(branchId).collect {
                _bankBranch.value = it
            }
        }
    }


}