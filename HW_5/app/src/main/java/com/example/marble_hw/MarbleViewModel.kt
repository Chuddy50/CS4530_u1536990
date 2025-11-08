package com.example.marble_hw

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class MarbleViewModel(app: Application) : AndroidViewModel(app), SensorEventListener {

    private val sensorManager = app.getSystemService(Application.SENSOR_SERVICE) as SensorManager

    private val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _position = MutableStateFlow(Pair(0f, 0f))
    val position = _position.asStateFlow()

    private var vx = 0f
    private var vy = 0f

    var maxX = 0f
    var maxY = 0f
    fun registerSensor() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val gx = event.values[0]      // left/right
        val gy = event.values[1]      // forward/back

        val dt = 0.02f
        val scale = 50f              // speed

        vx += dt * (-gx) * scale
        vy += dt * gy * scale        // note: +gy (not -gy)

        vx *= 0.95f
        vy *= 0.95f

        val newX = _position.value.first + vx * dt
        val newY = _position.value.second + vy * dt

        val clampedX = newX.coerceIn(0f, maxX)
        val clampedY = newY.coerceIn(0f, maxY)

        _position.value = Pair(clampedX, clampedY)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
