package x.chestnut.code.snippet.kotlin.base

import android.util.Log
import android.view.View
import android.widget.TextView
import x.chestnut.code.snippet.base.CoroutineBaseFragment
import java.io.BufferedReader
import java.lang.NumberFormatException
import java.util.*

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/20 20:09
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class KotlinBaseFragment : CoroutineBaseFragment() {

    private val question = "How are you"
    private val answer: String = "I am ok"

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        println("B" in setOf("A", "Bob"))
    }

    fun readNumber(reader: BufferedReader): Int? {
        return try {
            val line = reader.readLine()
            Integer.parseInt(line)
        } catch (e: NumberFormatException) {
            null
        } finally {
            reader.close()
        }
    }
}
open class Person(val name: String, val age: Int)
class Boy(name: String, age: Int) : Person(name, age) {
    fun man() = "i am Boy"
}
class Girl(name: String, age: Int) : Person(name, age) {
    fun girl() = "i am Girl"
}
enum class Color {
    RED, YELLOW, GREEN
}
enum class Color2(var r: Int, var g: Int, var b: Int) {
    RED(255,0,0),
    YELLOW(255,255,0),
    GREEN(0,255,255);

    fun rgb() = (r * 256 + g) * 256 + b
}
fun getMnemonic(color: Color) =
        when (color) {
            Color.GREEN -> "GREEN"
            Color.RED -> "RED"
            Color.YELLOW -> "YELLOW"
        }
fun getMnemonic2(color: Color) =
        when (color) {
            Color.GREEN, Color.RED -> "SELECT"
            else -> "ELSE"
        }
fun mix(color1: Color, color2: Color) =
        when (setOf(color1, color2)) {
            setOf(Color.GREEN, Color.RED) -> "SELECT"
            else -> "ELSE"
        }
fun mix2(color1: Color, color2: Color) =
        when {
            color1 == Color.GREEN -> "xxx"
            (color1 == Color.GREEN || color2 == Color.RED) -> "AAA"
            else -> "ELSE"
        }