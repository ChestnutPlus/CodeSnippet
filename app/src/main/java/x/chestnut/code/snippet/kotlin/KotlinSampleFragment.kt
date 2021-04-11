package x.chestnut.code.snippet.kotlin

import android.provider.Contacts
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.jetpack.hilt.HiltSampleFragment
import x.chestnut.code.snippet.kotlin.base.KotlinBaseFragment
import x.chestnut.code.snippet.kotlin.coroutine.KotlinCoroutineFragment
import x.chestnut.code.snippet.kotlin.flow.KotlinFlowFragment
import x.chestnut.code.snippet.other.OtherExampleFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/11/29 16:57
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class KotlinSampleFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        addView("KotlinBase") { startFragment(KotlinBaseFragment()) }
        addView("Coroutine") { startFragment(KotlinCoroutineFragment()) }
        addView("Flow") { startFragment(KotlinFlowFragment()) }
    }
}
