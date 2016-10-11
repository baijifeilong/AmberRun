package xyz.imamber.amberbike.ui.overlays

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.location.Location
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/11 16:41
 */
class MyLocationOverlay : Overlay() {

    var location: Location? = null
    private val point by lazy { Point() }

    override fun draw(canvas: Canvas, mapView: MapView, shadow: Boolean) {
        if (location != null) {
            mapView.projection.toPixels(GeoPoint(location), point)
            canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), Paint().apply {
                color = Color.RED
                strokeWidth = 50.toFloat()
                isAntiAlias = true
            })
        }
    }
}