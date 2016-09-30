package xyz.imamber.amberbike

import android.app.Application
import android.content.Context
import xyz.imamber.amberbike.di.OnlyComponent
import xyz.imamber.amberbike.di.DaggerOnlyComponent

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 10:58
 */
class App : Application() {

    private lateinit var onlyComponent: OnlyComponent

    override fun onCreate() {
        super.onCreate()

        onlyComponent = DaggerOnlyComponent.builder().build()
    }


    companion object {
        fun onlyComponent(context: Context): OnlyComponent {
            return (context.applicationContext as App).onlyComponent
        }
    }
}
