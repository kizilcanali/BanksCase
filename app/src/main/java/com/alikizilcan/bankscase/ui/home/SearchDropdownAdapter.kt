package com.alikizilcan.bankscase.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alikizilcan.bankscase.R

class SearchDropdownAdapter(private val mContext: Context, private val citiesList: List<String>) :
    ArrayAdapter<String>(mContext, 0, citiesList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dropdownItemView = convertView
        if (dropdownItemView == null) {
            dropdownItemView =
                LayoutInflater.from(mContext).inflate(R.layout.item_search_dropdown, parent, false)
        }
        val item = citiesList[position]
        val cityName: TextView = dropdownItemView!!.findViewById(R.id.cityNameText)
        cityName.text = item

        return dropdownItemView

    }


}