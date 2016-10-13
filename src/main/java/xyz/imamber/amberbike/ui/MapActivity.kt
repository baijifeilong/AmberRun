package xyz.imamber.amberbike.ui

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.intentFor
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.slf4j.LoggerFactory
import xyz.imamber.amberbike.services.MainService
import xyz.imamber.amberbike.ui.overlays.MyLocationOverlay
import xyz.imamber.amberbike.ui.overlays.PathOverlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:43
 */
class MapActivity : BaseActivity() {

    private val logger by lazy { LoggerFactory.getLogger(MapActivity::class.java) }

    private lateinit var mainService: MainService

    private lateinit var mapView: MapView

    private val myLocationOverlay by lazy { MyLocationOverlay() }
    private val pathOverlay by lazy { PathOverlay() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("MapActivity onCreate +++")

        bindService(intentFor<MainService>(), mainServiceConnection, Context.BIND_AUTO_CREATE)

        frameLayout {
            this@MapActivity.mapView = mapView {
                setBuiltInZoomControls(true)
                setMultiTouchControls(true)

                controller.apply {
                    setZoom(5)
                    setCenter(GeoPoint(32.0, 116.0))
                }

            }
        }

        mapView.overlays.apply {
            add(ScaleBarOverlay(mapView).apply {
                setCentred(true)
            })
            add(myLocationOverlay)
            add(pathOverlay)
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

            if (mainService.currentLocation != null) {
                myLocationOverlay.setPoint(GeoPoint(mainService.currentLocation))
            }
            for (location in mainService.sportLocations) {
                pathOverlay.addPoint(GeoPoint(location))
            }
            mapView.invalidate()
        }
    }

    private val mainServiceCallback = object : MainService.Callback {
        override fun onLocationChanged(location: Location) {
            this@MapActivity.onLocationChanged(location)
        }
    }

    private fun onLocationChanged(location: Location) {
        logger.debug("MapActivity received location update: {}", location)
        myLocationOverlay.setPoint(GeoPoint(location))
        if (mainService.isInSport) {
            pathOverlay.addPoint(GeoPoint(location))
        }
        runOnUiThread {
            mapView.invalidate()
        }
    }
}