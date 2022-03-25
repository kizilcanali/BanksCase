package com.alikizilcan.bankscase.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alikizilcan.bankscase.databinding.FragmentDetailBinding
import com.alikizilcan.bankscase.domain.model.BankBranch
import com.alikizilcan.bankscase.infra.bases.BaseFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPageFragment : BaseFragment() {

    override val viewModel: DetailPageViewModel by viewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        firebaseAnalytics = Firebase.analytics
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.bankBranch.observe(viewLifecycleOwner) {
            logEvents(it)
        }

        binding.navigateBranchButton.setOnClickListener {
            navigateToAddress(viewModel.bankBranch.value!!.address)
        }
    }

    private fun logEvents(item: BankBranch) {
        bundle.putString("adressName", item.addressName)
        bundle.putString("bankCode", item.bankCode)
        bundle.putString("address", item.address)
        bundle.putString("branch", item.branch)
        bundle.putString("city", item.city)
        bundle.putString("district", item.district)

        firebaseAnalytics.logEvent("viewed_page", bundle)
    }

    private fun navigateToAddress(address: String) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        val gmmIntentUri = Uri.parse("google.navigation:q=${Uri.encode(address)}")
        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(context!!.packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}