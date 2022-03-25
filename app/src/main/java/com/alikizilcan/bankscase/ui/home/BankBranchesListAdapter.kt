package com.alikizilcan.bankscase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.bankscase.databinding.ItemBranchesListBinding
import com.alikizilcan.bankscase.domain.model.BankBranch

class BankBranchesListAdapter : ListAdapter<BankBranch, BankBranchesListAdapter.BankBranchHolder>(DIFF_CALLBACK) {

    var itemClickListener: (BankBranch) -> Unit = {}

    class BankBranchHolder(
        private val binding: ItemBranchesListBinding,
        private val itemClickListener: (BankBranch) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(bankBranch: BankBranch){
            binding.baseModel = bankBranch
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                itemClickListener(bankBranch)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankBranchHolder {
        val binding = ItemBranchesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BankBranchHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: BankBranchHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BankBranch>(){
            override fun areItemsTheSame(oldItem: BankBranch, newItem: BankBranch): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BankBranch, newItem: BankBranch): Boolean =
                oldItem == newItem

        }
    }

}