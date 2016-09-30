package xyz.imamber.amberbike.ui

import android.os.Bundle
import org.jetbrains.anko.*

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 10:11
 */
class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            padding = dip(30)
            button("One").lparams {
                centerInParent()
            }
        }
    }
}