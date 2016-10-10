package xyz.imamber.amberbike.services

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 16:55
 */
class A {

    internal inner class B {
        fun aaa(): A {
            return this@A
        }
    }
}
