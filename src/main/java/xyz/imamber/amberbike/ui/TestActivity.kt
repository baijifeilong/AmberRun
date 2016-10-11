package xyz.imamber.amberbike.ui

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.activeandroid.query.Select
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.jetbrains.anko.*
import org.slf4j.Logger
import xyz.imamber.amberbike.App
import xyz.imamber.amberbike.models.Book
import xyz.imamber.amberbike.services.MainService
import javax.inject.Inject

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 10:11
 */
class TestActivity : BaseActivity() {

    private val ID_LISTEN_LOCATION_UPDATES = 1
    private val ID_MOCK_LOCATION_UPDATES = 2

    @Inject
    lateinit var logger: Logger

    private lateinit var mainService: MainService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.onlyComponent(this).inject(this)

        logger.info("TestActivity onCreate +++")

        bindService(intentFor<MainService>(), mainServiceConnection, Context.BIND_AUTO_CREATE)

        verticalLayout {
            padding = dip(30)

            button("Dagger") {
                onClick {
                    logger.error("Dagger worked")
                    snack("Dagger worked")
                }
            }

            button("Map") {
                onClick {
                    startActivity(intentFor<MapActivity>())
                }
            }

            button("Create new book") {
                onClick {
                    Book().apply {
                        name = RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(3, 7))
                    }.save()
                    toast("Saved")
                }
            }

            button("Show all books") {
                onClick {
                    val books: List<Book> = Select().from(Book::class.java).execute()
                    snack(books.toString())
                }
            }

            button() {
                id = ID_LISTEN_LOCATION_UPDATES
                onClick {
                    if (mainService.isListeningLocationUpdates) {
                        mainService.stopListenLocationUpdates()
                    } else {
                        mainService.startListenLocationUpdates()
                    }
                    updateView()
                }
            }

            button() {
                id = ID_MOCK_LOCATION_UPDATES
                onClick {
                    if (mainService.isMockingLocationUpdates)
                        mainService.stopMockLocationUpdates() else mainService.startMockLocationUpdates()
                    updateView()
                }
            }
        }
    }

    override fun onDestroy() {
        logger.info("TestActivity onDestroy ---")
        mainService.unregisterCallback(mainServiceCallback)
        unbindService(mainServiceConnection)
        super.onDestroy()
    }

    private fun updateView() {
        find<Button>(ID_LISTEN_LOCATION_UPDATES).text = if (mainService.isListeningLocationUpdates)
            "Stop listening location updates" else "Start listening location updates"
        find<Button>(ID_MOCK_LOCATION_UPDATES).text = if (mainService.isMockingLocationUpdates)
            "Stop mocking location updates" else "Start mocking location updates"
    }

    private val mainServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            logger.info("Main service disconnected XXX")
            logger.error("Connect main service failure")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            logger.info("Main Service connected OOO")
            mainService = (service as MainService.Binder).getService()
            mainService.registerCallback(mainServiceCallback)
            updateView()
        }
    }

    private val mainServiceCallback = object : MainService.Callback {
        override fun onLocationChanged(location: Location) {
            this@TestActivity.onLocationChanged(location)
        }
    }

    private fun onLocationChanged(location: Location) {
        logger.info("Test Activity received location update: {}", location)
    }
}