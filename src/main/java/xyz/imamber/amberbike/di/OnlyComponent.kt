package xyz.imamber.amberbike.di

import dagger.Component
import xyz.imamber.amberbike.services.MainService
import xyz.imamber.amberbike.ui.MainActivity
import xyz.imamber.amberbike.ui.MapActivity
import xyz.imamber.amberbike.ui.TestActivity

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:20
 */
@Component(modules = arrayOf(
        FooModule::class,
        MainModule::class
))
interface OnlyComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(testActivity: TestActivity)

    fun inject(mapActivity: MapActivity)

    fun inject(mainService: MainService)
}
