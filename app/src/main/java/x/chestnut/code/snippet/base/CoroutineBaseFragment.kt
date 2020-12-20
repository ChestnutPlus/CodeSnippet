package x.chestnut.code.snippet.base

import android.nfc.Tag
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ViewLogger
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/19 21:50
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
@AndroidEntryPoint
open class CoroutineBaseFragment : ScrollBaseFragment() {

    protected val mainScope = MainScope()
    private var tvLog: TextView? = null
    @Inject
    lateinit var logger: ViewLogger

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        tvLog = view?.findViewById(R.id.tv_log)
        initTvLog(tvLog)
        return view
    }

    protected fun initTvLog(tvLog: TextView?) {
        tvLog?.let {
            it.movementMethod = ScrollingMovementMethod.getInstance()
            it.setHorizontallyScrolling(true)
            it.isFocusable = true
            it.isClickable = true
        }
    }

    protected fun viewLog(tag: String, msg: String) {
        mainScope.launch(Dispatchers.Main) {
            logger.log(tag, msg, tvLog)
        }
    }

    protected fun viewLog(msg: String) {
        mainScope.launch(Dispatchers.Main) {
            logger.log(msg = msg, printer = tvLog)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}