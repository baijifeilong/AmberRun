package xyz.imamber.amberbike.ui

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import org.jetbrains.anko.find
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.intentFor
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import org.slf4j.LoggerFactory
import xyz.imamber.amberbike.services.MainService
import xyz.imamber.amberbike.ui.overlays.MyLocationOverlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:43
 */
class MapActivity : BaseActivity() {

    private val logger by lazy { LoggerFactory.getLogger(MapActivity::class.java) }

    private lateinit var mainService: MainService

    private val myLocationOverlay by lazy { MyLocationOverlay() }

    private val ID_MAP_VIEW = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("MapActivity onCreate +++")

        bindService(intentFor<MainService>(), mainServiceConnection, Context.BIND_AUTO_CREATE)

        frameLayout {
            mapView {
                id = ID_MAP_VIEW
                setBuiltInZoomControls(true)
                setMultiTouchControls(true)

                controller.apply {
                    setZoom(11)
                    setCenter(GeoPoint(32.0, 116.0))
                }

                overlays.apply {
                    add(MyLocationNewOverlay(GpsMyLocationProvider(this@MapActivity), this@mapView))
                    add(CompassOverlay(this@MapActivity, InternalCompassOrientationProvider(this@MapActivity), this@mapView))
                    add(RotationGestureOverlay(this@mapView).apply {
                        isEnabled = false
                    })
                    add(ScaleBarOverlay(this@mapView).apply {
                        setCentred(true)
                    })
                    add(myLocationOverlay)
                }
            }
        }
    }

    override fun onDestroy() {
        logger.info("MapActivity onDestroy ---")
        mainService.unregisterCallback(mainServiceCallback)
        unbindService(mainServiceConnection)
        super.onDestroy()
    }

    private val mainServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            logger.info("Main service disconnected XXX")
            logger.error("Connect main service failure")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            logger.info("Main Service connected OOO")
            mainService = (service as MainService.Binder).getService()
            mainService.registerCallback(mainServiceCallback)
        }
    }

    private val mainServiceCallback = object : MainService.Callback {
        override fun onLocationChanged(location: Location) {
            this@MapActivity.onLocationChanged(location)
        }
    }

    private fun onLocationChanged(location: Location) {
        logger.info("MapActivity received location update: {}", location)
        myLocationOverlay.location = location
        runOnUiThread {
            find<MapView>(ID_MAP_VIEW).invalidate()
        }
    }
}