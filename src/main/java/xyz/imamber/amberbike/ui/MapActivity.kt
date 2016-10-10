package xyz.imamber.amberbike.ui

import android.os.Bundle
import org.jetbrains.anko.frameLayout
import org.osmdroid.util.GeoPoint

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
                controller.setZoom(10)
                controller.setCenter(GeoPoint(39.0, 116.0))
            }
        }
    }
}