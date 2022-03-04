package com.lospollos.numbersandcolors.viewmodel

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.numbersandcolors.data.RotationHelper

class ColorViewModel : ViewModel() {

    val isSuccess = MutableLiveData<Int>()

    private val rotationHelper = RotationHelper()

    fun onResume(context: Context, container: ConstraintLayout) {
        isSuccess.value = rotationHelper.initializeSensor(context, container)
    }

    fun onPause() {
        rotationHelper.closeSensor()
    }

}