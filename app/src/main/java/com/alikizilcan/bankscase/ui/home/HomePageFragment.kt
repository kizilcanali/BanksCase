package com.alikizilcan.bankscase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.alikizilcan.bankscase.MainActivityViewModel
import com.alikizilcan.bankscase.R
import com.alikizilcan.bankscase.databinding.FragmentHomePageBinding
import com.alikizilcan.bankscase.infra.bases.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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


        val snack = setupSnackbar(view)

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
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        activityViewModel.isConnectionAvailable.observe(viewLifecycleOwner) { isConnected ->
            dependConnectionUISetup(isConnected, snack)
            binding.branchesRecyclerView.isVisible = isConnected
        }

        binding.fetchAllProvinces.setOnClickListener {
            viewModel.getBankBranches()
        }

        bankBranchesListAdapter.itemClickListener = viewModel.itemClickListener
    }



    private fun dependConnectionUISetup(bool: Boolean, snackbar: Snackbar){
        if(!bool){
            snackbar.show()
        }else{
            snackbar.dismiss()
        }
    }

    private fun setupSnackbar(view: View): Snackbar {
        val snackbar = Snackbar.make(
            view,
            requireContext().getString(R.string.no_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setBackgroundTint(requireContext().getColor(R.color.red))
        return snackbar
    }
}