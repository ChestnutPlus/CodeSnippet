package x.chestnut.code.snippet.anim

import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carSign.CarSignFragment
import x.chestnut.code.snippet.anim.countNumberView.CountNumberViewFragment
import x.chestnut.code.snippet.base.ScrollBaseFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/10 12:24
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class AnimFragment : ScrollBaseFragment() {

    override val actionBarTitleId: Int
        get() = R.string.title_anim

    override fun onViewCreate(rootView: View) {
        addView("CountNumberView") { startFragment(CountNumberViewFragment()) }
        addView("CarSign") { startFragment(CarSignFragment()) }
    }
}