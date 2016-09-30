package xyz.imamber.amberbike.di

import dagger.Module
import dagger.Provides
import xyz.imamber.amberbike.models.User

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 13:22
 */
@Module
internal class FooModule {

    @Provides
    fun user(): User {
        return User("", "")
    }
}
