package com.lospollos.numbersandcolors.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun onResume() {
        super.onResume()
        colorViewModel.onResume(this, colorContainer)
        colorViewModel.isSuccess.observe(this) {
            if (it == ERROR) {
                Toast.makeText(this, getString(R.string.error_toast_text), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        colorViewModel.onPause()
    }

}