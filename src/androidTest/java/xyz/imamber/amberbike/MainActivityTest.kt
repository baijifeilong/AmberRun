package xyz.imamber.amberbike

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.imamber.amberbike.ui.MainActivity

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/20 10:08
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
open class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testOne() {
        onView(withText("Login")).perform(click()).check(matches(withText("Logon")))
    }
}