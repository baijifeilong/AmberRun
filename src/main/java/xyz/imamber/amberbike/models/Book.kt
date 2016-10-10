package xyz.imamber.amberbike.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * Author: BaiJiFeiLong@gmail.com
 * DateTime: 2016/10/10 14:27
 */
@Table(name = "books", id = "id")
class Book : Model() {

    @Column(name = "name")
    var name: String? = null

    override fun toString(): String {
        return "Book(name=$name)"
    }
}