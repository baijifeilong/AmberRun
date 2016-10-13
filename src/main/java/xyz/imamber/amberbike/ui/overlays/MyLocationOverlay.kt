package xyz.imamber.amberbike.ui.overlays

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/11 16:41
 */
class MyLocationOverlay : Overlay() {

    private var geoPoint: GeoPoint? = null

    private val paint by lazy {
        Paint().apply {
            color = Color.RED
            strokeWidth = 3.toFloat()
            isAntiAlias = true
        }
    }

    fun setPoint(geoPoint: GeoPoint) {
        this.geoPoint = GeoPoint(geoPoint)
    }

    private val point by lazy { Point() }

    override fun draw(canvas: Canvas, mapView: MapView, shadow: Boolean) {
        if (geoPoint != null) {
            mapView.projection.toPixels(geoPoint, point)
            canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), 10.toFloat(), paint)
        }
    }
}