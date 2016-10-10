package xyz.imamber.amberbike.ui

import android.os.Bundle
import com.activeandroid.query.Select
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.jetbrains.anko.*
import xyz.imamber.amberbike.models.Book

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/9/30 10:11
 */
class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)
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
        }
    }
}