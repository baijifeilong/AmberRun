package xyz.imamber.amberbike.ui

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import org.jetbrains.anko.*
import org.slf4j.LoggerFactory
import xyz.imamber.amberbike.App
import xyz.imamber.amberbike.models.User
import xyz.imamber.amberbike.services.MainService
import javax.inject.Inject

class MainActivity : BaseActivity() {

    val logger = LoggerFactory.getLogger(MainActivity::class.java)

    @Inject
    lateinit var user: User

    val mainServiceCallback = object : MainService.Callback {
        override fun onLocationChanged(location: Location) {
            this@MainActivity.onLocationChanged(location)
        }
    }

    private val mainServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            (service as MainService.Binder).getService().registerCallback(mainServiceCallback)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.onlyComponent(this).inject(this)
        logger.error("{}", user)

        bindService(intentFor<MainService>(), mainServiceConnection, Context.BIND_AUTO_CREATE)

        verticalLayout {
            padding = dip(30)
            button("Mock location") {
                onClick {
                    toast("Clicked")
                    logger.info("{}", "Clicked me")
                }
            }
            button("Map") {
                onClick {
                    startActivity(intentFor<MapActivity>())
                }
            }
            button("Test") {
                onClick {
                    startActivity(intentFor<TestActivity>())
                }
            }
            button("Snack") {
                onClick {
                    snack("I am snack")
                }
            }
            button("Long Snack") {
                onClick {
                    longSnack("I am long snack")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mainServiceConnection)
    }

    private fun onLocationChanged(location: Location) {

    }
}
