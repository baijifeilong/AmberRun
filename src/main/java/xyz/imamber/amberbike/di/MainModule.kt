package xyz.imamber.amberbike.di

import dagger.Module
import dagger.Provides
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/11 9:40
 */
@Module
class MainModule {

    @Provides
    fun logger(): Logger {
        return LoggerFactory.getLogger("App")
    }
}