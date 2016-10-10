package xyz.imamber.amberbike.ui

import android.os.Bundle
import org.jetbrains.anko.frameLayout
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:43
 */
class MapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            mapView {
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
                }
            }
        }
    }
}