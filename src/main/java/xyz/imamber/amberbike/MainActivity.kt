package xyz.imamber.amberbike

import android.annotation.TargetApi
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import org.apache.commons.lang3.RandomUtils
import org.jetbrains.anko.*
import org.slf4j.LoggerFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    val logger = LoggerFactory.getLogger(MainActivity::class.java)
    lateinit var locationManager: LocationManager
    val MOCK_PROVIDER = LocationManager.GPS_PROVIDER

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLocationMock()

        verticalLayout {
            padding = dip(30)
            button("Mock location") {
                onClick {
                    toast("Clicked")
                    logger.info("{}", "Clicked me")
                }
            }
        }
    }

    private fun initLocationMock() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.addTestProvider(MOCK_PROVIDER, false, true, false, false, true, true, true, 0, 5);
        locationManager.setTestProviderEnabled(MOCK_PROVIDER, true)
        locationManager.requestLocationUpdates(MOCK_PROVIDER, 0L, 0F, object : LocationListener {
            override fun onLocationChanged(location: Location) {
                logger.info("Location changed: {}", location)
            }

            override fun onProviderDisabled(provider: String?) {
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }
        })

        Timer().schedule(object : TimerTask() {
            @TargetApi(17)
            override fun run() {
                locationManager.setTestProviderLocation(MOCK_PROVIDER, Location(MOCK_PROVIDER).apply {
                    time = System.currentTimeMillis()
                    longitude = RandomUtils.nextDouble(110.0, 120.0)
                    latitude = RandomUtils.nextDouble(30.0, 40.0)
                    altitude = RandomUtils.nextDouble(10.0, 100.0)
                    accuracy = 3.0f
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                        elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                    }
                })
            }
        }, 0, 5000)
    }
}
