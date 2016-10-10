@file:Suppress("NOTHING_TO_INLINE")

package xyz.imamber.amberbike.ui

import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView
import org.osmdroid.views.MapView

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 11:40
 */

public inline fun ViewManager.mapView(theme: Int = 0) = mapView(theme) {}
public inline fun ViewManager.mapView(theme: Int = 0, init: MapView.() -> Unit) = ankoView({ MapView(it) }, theme, init)