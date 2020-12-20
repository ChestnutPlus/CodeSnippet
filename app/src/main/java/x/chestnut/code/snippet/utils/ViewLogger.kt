package x.chestnut.code.snippet.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/19 23:03
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
@Singleton
class ViewLogger @Inject constructor() {

    companion object {
        private const val MAX_LINE = 20
    }

    private val loggerTag = "ViewLogger"
    private val logTemp = StringBuilder()
    private val queenLog = LinkedList<String>()
    private val format = SimpleDateFormat("ss:SSS", Locale.CHINA)
    private val date = Date()

    fun log(tag: String = loggerTag,
            msg: String = "null",
            printer: TextView? = null
    ) {
        Log.d(loggerTag, msg)
        if (queenLog.size == MAX_LINE) {
            queenLog.poll()
        }
        date.time = System.currentTimeMillis()
        queenLog.offer("${format.format(date)} $tag: $msg")
        for (str in queenLog) {
            logTemp.append(str).append("\n")
        }
        printer?.let { textView ->
            textView.visibility = View.VISIBLE
            textView.text = logTemp.toString()
            val offset = textView.lineCount * textView.lineHeight
            val result = offset - textView.height
            if (result > 0) {
                //+1 行 是为了让末尾不贴边
                textView.scrollTo(0, result + textView.lineHeight)
            }
        }
        logTemp.clear()
    }
}