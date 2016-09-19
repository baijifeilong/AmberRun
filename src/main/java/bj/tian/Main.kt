package bj.tian

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*

class Main : AppCompatActivity() {

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
            }
        }
    }
}