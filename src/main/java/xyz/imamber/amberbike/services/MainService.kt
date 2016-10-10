package xyz.imamber.amberbike.services

import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import org.apache.commons.lang3.RandomUtils
import org.jetbrains.anko.locationManager
import java.util.*

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 16:15
 */
class MainService : Service() {
    internal inner class Binder : android.os.Binder() {
        fun getService(): MainService {
            return this@MainService;
        }
    }

    /// ========== Callback start

    interface Callback {
        fun onLocationChanged(location: Location)
    }

    private val callbacks = setOf<Callback>()

    fun registerCallback(callback: Callback) {
        callbacks + callback
    }

    fun unregisterCallback(callback: Callback) {
        callbacks - callback
    }

    /// ========== Callback end

    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onCreate() {

    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            this@MainService.onLocationChanged(location)
        }

        override fun onProviderDisabled(provider: String?) {
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }
    }

    fun startListenLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 5.0F, locationListener)
    }

    fun stopListenLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }

    fun onLocationChanged(location: Location) {
        callbacks.forEach { callback ->
            callback.onLocationChanged(location)
        }
    }

    /// Location mock start

    val timer: Timer = Timer()

    fun startMockLocationUpdates() {
        timer.cancel()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val location: Location = Location(LocationManager.GPS_PROVIDER).apply {
                    longitude = RandomUtils.nextDouble(116.0, 119.0)
                    latitude = RandomUtils.nextDouble(32.0, 40.0)
                }
                mockLocation(location)
            }
        }, 0, 5000)
    }

    fun stopMockLocationUpdates() {
        timer.cancel()
    }

    fun mockLocation(location: Location) {
        onLocationChanged(location)
    }

    /// Location mock end
}