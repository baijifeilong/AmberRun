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
import org.slf4j.LoggerFactory
import xyz.imamber.amberbike.App
import java.util.*

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 16:15
 */
class MainService : Service() {

    var logger = LoggerFactory.getLogger(MainService::class.java)

    var isListeningLocationUpdates = false
        get
        private set

    var isMockingLocationUpdates = false
        get
        private set

    internal inner class Binder : android.os.Binder() {
        fun getService(): MainService {
            return this@MainService;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////// Callback start

    interface Callback {
        fun onLocationChanged(location: Location)
    }

    private val callbacks = mutableSetOf<Callback>()

    fun registerCallback(callback: Callback) {
        callbacks.add(callback)
        logger.info("New callback registered: {}", callback)
    }

    fun unregisterCallback(callback: Callback) {
        callbacks.remove(callback)
        logger.info("Callback unregistered: {}", callback)
    }

    ////////// Callback end
    ////////////////////////////////////////////////////////////////////////////////

    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onCreate() {
        App.onlyComponent(this).inject(this)
        logger.info("Main Service created +++")
    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            logger.info("Location changed from: " + location.provider)
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
        isListeningLocationUpdates = true
        logger.info("Listening location updates OOO")
    }

    fun stopListenLocationUpdates() {
        locationManager.removeUpdates(locationListener)
        isListeningLocationUpdates = false
        logger.info("Stop listening location updates XXX")
    }

    fun onLocationChanged(location: Location) {
        logger.info("Location Changed: {}", location)
        callbacks.forEach { callback ->
            callback.onLocationChanged(location)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////// Location mock start

    lateinit var timer: Timer

    fun startMockLocationUpdates() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val location: Location = Location(LocationManager.GPS_PROVIDER).apply {
                    longitude = RandomUtils.nextDouble(116.0, 119.0)
                    latitude = RandomUtils.nextDouble(32.0, 40.0)
                }
                mockLocation(location)
            }
        }, 0, 5000)
        isMockingLocationUpdates = true
        logger.info("Mocking location updates OOO")
    }

    fun stopMockLocationUpdates() {
        timer.cancel()
        isMockingLocationUpdates = false
        logger.info("Stop mocking location updates XXX")
    }

    fun mockLocation(location: Location) {
        logger.info("Location changed from MOCK")
        onLocationChanged(location)
    }

    ////////// Location mock end
    ////////////////////////////////////////////////////////////////////////////////
}