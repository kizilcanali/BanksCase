package com.alikizilcan.bankscase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.alikizilcan.bankscase.MainActivityViewModel
import com.alikizilcan.bankscase.databinding.FragmentHomePageBinding
import com.alikizilcan.bankscase.infra.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomePageFragment : BaseFragment() {

    private val activityViewModel: MainActivityViewModel by activityViewModels()
    override val viewModel: HomePageViewModel by viewModels()

    private var _binding: FragmentHomePageBinding? = null
    val binding get() = _binding!!

    private val bankBranchesListAdapter = BankBranchesListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val snack = viewModel.setupSnackbar(view, requireContext())

        binding.branchesRecyclerView.adapter = bankBranchesListAdapter

        viewModel.branchesList.observe(viewLifecycleOwner) {
            bankBranchesListAdapter.submitList(it)
        }

        viewModel.searchCityText.observe(viewLifecycleOwner) {
            viewModel.getBankBranchesByCity()
            bankBranchesListAdapter.submitList(viewModel.branchesList.value)
        }

        viewModel.cityNames.observe(viewLifecycleOwner) {
            val searchDropdownAdapter = SearchDropdownAdapter(requireContext(), it.toList())
            binding.citiesList.setAdapter(searchDropdownAdapter)
        }

        binding.refreshLayout.setOnRefreshListener {
            refresh()
        }

        activityViewModel.isConnectionAvailable.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected == false) {
                snack.show()
            } else if (isConnected) {
                snack.dismiss()
                viewModel.hasNoResult.value = false
            }
            binding.branchesRecyclerView.isVisible = isConnected
        }

        binding.fetchAllProvinces.setOnClickListener {
            viewModel.getBankBranches()
        }

        bankBranchesListAdapter.itemClickListener = viewModel.itemClickListener
    }

    private fun refresh() {
        runBlocking {
            if (viewModel.searchCityText.value == null) {
                viewModel.getBankBranches()
            } else {
                viewModel.getBankBranchesByCity()
            }
        }
        binding.refreshLayout.isRefreshing = false
    }
}