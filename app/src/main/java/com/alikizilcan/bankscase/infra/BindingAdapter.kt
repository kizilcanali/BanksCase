package com.alikizilcan.bankscase.infra


import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.alikizilcan.bankscase.R


@BindingAdapter("setStateVisibility")
fun setStateVisibility(view: View, state: Boolean){
    view.isVisible = state
}

@BindingAdapter("isNullText", "newText")
fun setNullBranch(view: TextView, response: String?, newText: String?) {
    if (response == "" || response == null) view.text = newText else view.text = response
}

@BindingAdapter("setBgColor")
fun setBackgroundColor(view: TextView, conditionText: String?) {
    val redBg : Drawable? = ResourcesCompat.getDrawable(view.context.resources, R.drawable.info_tag_bg_red, null)
    val grBg : Drawable? = ResourcesCompat.getDrawable(view.context.resources, R.drawable.info_tag_bg_green, null)
    if (conditionText?.contains("Off") == true) {
        view.background = redBg
    } else if (conditionText?.contains("On") == true) {
        view.background = grBg
    }
}