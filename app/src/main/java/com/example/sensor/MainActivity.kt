package com.example.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var sensorEventListener: SensorEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService<SensorManager>()
//        if (sensorManager == null) {
//            Toast.makeText(this, "Could not process", Toast.LENGTH_LONG).show()
//            finish()
//        } else {
//            val sensor = sensorManager.getSensorList(Sensor.TYPE_ALL)
//            sensor.forEach {
//                Log.d("SENSOR", """
//                    ${it.name} | ${it.stringType} | ${it.vendor}
//                    """.trimIndent())
//            }
//        }
        val proxySensor = sensorManager?.getSensorList(Sensor.TYPE_PROXIMITY)
        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("SENSOR", """onSensorChanged:${event!!.values[0]}""".trimIndent())
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                // do nothing in onAccuracyChanged function
            }

        }
    }
}