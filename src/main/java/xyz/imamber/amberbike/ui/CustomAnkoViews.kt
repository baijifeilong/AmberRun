@file:Suppress("NOTHING_TO_INLINE")

package xyz.imamber.amberbike.ui

import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView
import org.osmdroid.views.MapView

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 11:40
 */

inline fun ViewManager.mapView(theme: Int = 0) = mapView(theme) {}

inline fun ViewManager.mapView(theme: Int = 0, init: MapView.() -> Unit) = ankoView({ MapView(it) }, theme, init)

fun View.snack(message: CharSequence) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

fun View.longSnack(message: CharSequence) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
