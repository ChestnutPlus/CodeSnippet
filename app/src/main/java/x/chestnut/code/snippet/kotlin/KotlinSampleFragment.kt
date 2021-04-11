package x.chestnut.code.snippet.kotlin

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.kotlin.base.KotlinBaseFragment
import x.chestnut.code.snippet.kotlin.coroutine.KotlinCoroutineFragment
import x.chestnut.code.snippet.kotlin.flow.KotlinFlowFragment

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
