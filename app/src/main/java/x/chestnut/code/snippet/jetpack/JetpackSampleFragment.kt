package x.chestnut.code.snippet.jetpack

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.jetpack.hilt.HiltSampleFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/12/13 12:11
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class JetpackSampleFragment : ScrollBaseFragment() {

    override val actionBarTitle: String?
        get() = "JetpackSample"

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        addView("Hilt") { startFragment(HiltSampleFragment()) }
    }
}
