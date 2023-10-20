package com.example.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var sensorEventListener: SensorEventListener
    private lateinit var sensorManager: SensorManager
    private lateinit var proxySensor: Sensor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
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

        proxySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!
        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("SENSOR", """onSensorChanged:${event!!.values[0]}""".trimIndent())
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                // do nothing in onAccuracyChanged function
            }

        }
    }

    override fun onResume() {
        super.onResume()
       sensorManager.registerListener(sensorEventListener,proxySensor,1000*1000)
    }



    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }
}