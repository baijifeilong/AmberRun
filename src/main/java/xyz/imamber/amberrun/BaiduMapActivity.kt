package xyz.imamber.amberrun

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.baidu.mapapi.SDKInitializer

class BaiduMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKInitializer.initialize(applicationContext)
        setContentView(R.layout.activity_baidu_map)
    }
}
