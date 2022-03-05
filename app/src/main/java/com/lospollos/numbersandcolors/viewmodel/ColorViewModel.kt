package com.lospollos.numbersandcolors.viewmodel

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.numbersandcolors.data.RotationHelper

class ColorViewModel : ViewModel() {

    val isSuccess = MutableLiveData<Int>()
    val colorVal = MutableLiveData<Float>()

    private val rotationHelper = RotationHelper {
        colorVal.value = it
    }

    fun onResume(context: Context) {
        isSuccess.value = rotationHelper.initializeSensor(context)
    }

    fun onPause() {
        rotationHelper.closeSensor()
    }

}