package com.alikizilcan.bankscase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alikizilcan.bankscase.domain.BankBranchesUseCase
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.Resource
import com.alikizilcan.bankscase.infra.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val bankBranchesUseCase: BankBranchesUseCase) :
    BaseViewModel() {

    private val _bankBranchesList: MutableLiveData<List<BankBranch>> = MutableLiveData()
    val branchesList: LiveData<List<BankBranch>> = _bankBranchesList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorState: MutableLiveData<Boolean> = MutableLiveData()
    val errorState: LiveData<Boolean> = _errorState

    val hasNoResult: MutableLiveData<Boolean> = MutableLiveData()


    var cityNames: MutableLiveData<MutableSet<String>> = MutableLiveData()
    var searchCityText: MutableLiveData<String> = MutableLiveData()

    init {
        getBankBranches()
        getCityNames()
    }

    val itemClickListener: (BankBranch) -> Unit = {
        val action = HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(it.id)
        baseNavigation.navigate(action)
    }

    fun getBankBranches() {
        viewModelScope.launch {
            bankBranchesUseCase.getBankBranches().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _bankBranchesList.value = resource.data!!
                        _isLoading.value = false
                        _errorState.value = false
                        hasNoResult.value = false
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        hasNoResult.value = true
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                        hasNoResult.value = false
                    }
                }
            }
        }
    }


    fun getBankBranchesByCity() {
        viewModelScope.launch {
            bankBranchesUseCase.getBranchesByCity(searchCityText.value!!).collect { response ->
                _bankBranchesList.value = response
            }
        }
    }

    private fun getCityNames() {
        viewModelScope.launch {
            bankBranchesUseCase.getCityNames().collect {
                cityNames.value = it.toMutableSet()
            }
        }
    }

    fun refresh() {
        runBlocking {
            if (searchCityText.value == null) {
                getBankBranches()
            } else {
                getBankBranchesByCity()
            }
        }
        hasNoResult.value = false
    }
}