package strings
import java.lang.StringBuilder

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/27 21:15
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
fun <T> Collection<T>.joinToStringXXX(
        separator: String = ",",
        prefix: String = "",
        postfix: String = "",
): String {
    val result = StringBuilder(prefix)
    for((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
val String.lastChar: Char
    get() = get(length - 1)
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }
fun listABC(a: String, b: String) {
    fun check(check: String) {
        //check the params
    }
    check(a)
    check(b)
}
class ForTest {
}
interface Focusable {
    fun setFocus()
    fun showOff() = println("111")
}
interface Clickable {
    fun setFocus()
    fun showOff() = println("222")
}

open class Btn : Clickable, Focusable {
    override fun setFocus() {
        TODO("Not yet implemented")
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
open class User constructor(val _nickName: String,
                            val _age: Int = 20) {
    val nickName: String
    val age = _age
    init {
        nickName = _nickName
    }
    fun a() {
        println("nickName is $nickName")
    }
}
class TwitterUser private constructor() {}