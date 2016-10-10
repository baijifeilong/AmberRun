package xyz.imamber.amberbike

import android.app.Application
import android.content.Context
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import xyz.imamber.amberbike.di.DaggerOnlyComponent
import xyz.imamber.amberbike.di.OnlyComponent

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 10:58
 */
class App : Application() {

    private lateinit var onlyComponent: OnlyComponent

    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(Configuration.Builder(this)
                .setDatabaseName("main.db").create())
        onlyComponent = DaggerOnlyComponent.builder().build()
    }

    override fun onTerminate() {
        super.onTerminate()
        ActiveAndroid.dispose()
    }

    companion object {
        fun onlyComponent(context: Context): OnlyComponent {
            return (context.applicationContext as App).onlyComponent
        }
    }
}
