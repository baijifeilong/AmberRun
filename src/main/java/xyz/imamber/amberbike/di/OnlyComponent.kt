package xyz.imamber.amberbike.di

import dagger.Component
import xyz.imamber.amberbike.ui.MainActivity

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:20
 */
@Component(modules = arrayOf(FooModule::class))
interface OnlyComponent {

    fun inject(mainActivity: MainActivity)
}
