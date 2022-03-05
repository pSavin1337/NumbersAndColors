package com.lospollos.numbersandcolors.view.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import com.lospollos.numbersandcolors.Constants.ERROR
import com.lospollos.numbersandcolors.R
import com.lospollos.numbersandcolors.viewmodel.ColorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var colorContainer: ConstraintLayout
    private lateinit var colorViewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        colorContainer = findViewById(R.id.color_container)
        colorViewModel = ViewModelProvider(this)[ColorViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        colorViewModel.onResume(this)
        colorViewModel.isSuccess.observe(this) {
            if (it == ERROR) {
                Toast.makeText(this, getString(R.string.error_toast_text), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        colorViewModel.colorVal.observe(this) { colorVal ->
            colorContainer.background = Color.rgb(colorVal, colorVal, colorVal).toDrawable()
        }
    }

    override fun onPause() {
        super.onPause()
        colorViewModel.onPause()
    }

}