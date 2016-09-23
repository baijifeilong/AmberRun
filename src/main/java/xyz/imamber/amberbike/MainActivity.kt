package xyz.imamber.amberbike

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                textSize = 30f
                onClick {
                    longToast("Clicked")
                    text = "Logon"
                    Snackbar.make(this, "A", Snackbar.LENGTH_LONG).show()
                }
            }
            floatingActionButton {
                onClick {
                    Snackbar.make(this, "B", Snackbar.LENGTH_INDEFINITE).show()
                }
            }
        }
    }
}
