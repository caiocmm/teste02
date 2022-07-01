package com.teste.commons.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.teste.commons.R

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateAnimation(destinationId: Int, params: Bundle? = null) {
    this.navigate(destinationId, params, navOptions)
}

fun NavController.navigateAnimation(directions: NavDirections) {
    this.navigate(directions, navOptions)
}