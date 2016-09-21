package xyz.imamber.amberrun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amap.api.maps.MapView
import org.jetbrains.anko.find

class AMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amap)

        val mapView = find<MapView>(R.id.map)
    }
}
