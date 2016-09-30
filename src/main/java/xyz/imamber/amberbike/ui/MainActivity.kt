package xyz.imamber.amberbike.ui

import android.os.Bundle
import org.jetbrains.anko.*
import org.slf4j.LoggerFactory
import xyz.imamber.amberbike.App
import xyz.imamber.amberbike.models.User
import javax.inject.Inject

class MainActivity : BaseActivity() {

    val logger = LoggerFactory.getLogger(MainActivity::class.java)

    @Inject
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.onlyComponent(this).inject(this)
        logger.error("{}", user)

        verticalLayout {
            padding = dip(30)
            button("Mock location") {
                onClick {
                    toast("Clicked")
                    logger.info("{}", "Clicked me")
                }
            }
        }
    }

}
