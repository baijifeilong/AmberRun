package xyz.imamber.amberbike.ui.overlays

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.location.Location
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/11 17:16
 */
class PathOverlay : Overlay() {

    val locations by lazy { mutableListOf<Location>() }
    val pointForReuse by lazy { Point() }
    private val flatPoints by lazy { mutableListOf<Float>() }
    private var projection: Projection? = null

    private val paint by lazy {
        Paint().apply {
            color = Color.BLUE
            strokeWidth = 5.toFloat()
            isAntiAlias = true
        }
    }

    override fun draw(canvas: Canvas, mapView: MapView, shadow: Boolean) {
        if (projection == null) {
            this.projection = mapView.projection
        }
        if (flatPoints.size >= 4) {
            canvas.drawLines(flatPoints.toFloatArray(), paint)
        }
    }

    fun addLocation(location: Location) {
        locations.add(location)
        projection!!.toPixels(GeoPoint(location), pointForReuse)
        flatPoints.add(pointForReuse.x.toFloat())
        flatPoints.add(pointForReuse.y.toFloat())
    }
}