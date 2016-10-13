package xyz.imamber.amberbike.ui.overlays

import android.graphics.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/11 17:16
 */
class PathOverlay : Overlay() {

    private val point by lazy { Point() }
    private val geoPoints by lazy { mutableListOf<GeoPoint>() }
    private val path by lazy { Path() }

    private val paint by lazy {
        Paint().apply {
            color = Color.BLUE
            strokeWidth = 3.toFloat()
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
    }

    override fun draw(canvas: Canvas, mapView: MapView, shadow: Boolean) {
        if (geoPoints.size >= 2) {
            path.reset()
            for (i in geoPoints.indices) {
                mapView.projection.toPixels(geoPoints[i], point)
                if (i == 0) {
                    path.moveTo(point.x.toFloat(), point.y.toFloat())
                } else {
                    path.lineTo(point.x.toFloat(), point.y.toFloat())
                }
            }
            canvas.drawPath(path, paint)
        }
    }

    fun addPoint(geoPoint: GeoPoint) {
        geoPoints.add(geoPoint)
    }
}