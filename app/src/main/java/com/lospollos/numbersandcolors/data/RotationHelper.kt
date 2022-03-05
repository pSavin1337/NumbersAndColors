package com.lospollos.numbersandcolors.data

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.lospollos.numbersandcolors.Constants.ERROR
import com.lospollos.numbersandcolors.Constants.SUCCESS
import com.lospollos.numbersandcolors.Constants.X
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin

class RotationHelper(val callback: (colorVal: Float) -> Unit) {

    private lateinit var sensorManager: SensorManager
    private var colorVal = 0.0f

    private val angle = PI / 2
    private val exceptionMessage = "Sensor values is NULL"

    private val sensorEventListener = object : SensorEventListener {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onSensorChanged(event: SensorEvent?) {
            var rotation = asin(
                abs(event?.values?.get(X) ?: throw Exception(exceptionMessage))
            ).toDouble() * 2
            while (rotation > angle) {
                rotation -= angle
            }
            colorVal = (rotation / angle).toFloat()
            callback(colorVal)
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    fun initializeSensor(context: Context): Int {
        sensorManager = context.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        return if (sensor != null) {
            sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_FASTEST
            )
            SUCCESS
        } else {
            ERROR
        }
    }

    fun closeSensor() {
        sensorManager.unregisterListener(sensorEventListener)
    }

}