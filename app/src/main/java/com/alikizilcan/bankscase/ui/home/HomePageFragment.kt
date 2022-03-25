package com.alikizilcan.bankscase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.alikizilcan.bankscase.databinding.FragmentHomePageBinding
import com.alikizilcan.bankscase.infra.NetworkConnectionLiveData
import com.alikizilcan.bankscase.infra.bases.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomePageFragment : BaseFragment() {

    override val viewModel: HomePageViewModel by viewModels()

    private var _binding: FragmentHomePageBinding? = null
    val binding get() = _binding!!

    private val bankBranchesListAdapter = BankBranchesListAdapter()
    private lateinit var networkLiveData: NetworkConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkLiveData = NetworkConnectionLiveData(requireContext())
    }

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

        binding.branchesRecyclerView.adapter = bankBranchesListAdapter
        viewModel.branchesList.observe(viewLifecycleOwner) {
            bankBranchesListAdapter.submitList(it)
        }

        viewModel.searchCityText.observe(viewLifecycleOwner) {
            if (it == null || it == "TÜM İLLER") {
                viewModel.getBankBranches()
                bankBranchesListAdapter.submitList(viewModel.branchesList.value)
            } else {
                viewModel.getBankBranchesByCity()
                bankBranchesListAdapter.submitList(viewModel.branchesList.value)
            }
        }

        viewModel.cityNames.observe(viewLifecycleOwner) {
            if (!it.contains("TÜM İLLER")) {
                it.add("TÜM İLLER")
            }
            val searchDropdownAdapter = SearchDropdownAdapter(requireContext(), it.toList())
            binding.citiesList.setAdapter(searchDropdownAdapter)
        }

        binding.refreshLayout.setOnRefreshListener {
            refresh()
        }

        networkLiveData.observe(viewLifecycleOwner) { isConnected ->
            binding.netErrorLayout.isVisible = !isConnected
            binding.branchesRecyclerView.isVisible = isConnected
        }
        bankBranchesListAdapter.itemClickListener = viewModel.itemClickListener
    }

    private fun refresh() {
        runBlocking { viewModel.getBankBranches() }
        binding.refreshLayout.isRefreshing = false
    }

}