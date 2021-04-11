package x.chestnut.code.snippet.contract

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.other.OtherExampleFragment
import x.chestnut.code.snippet.ui.notification.NotificationFragment
import x.chestnut.code.snippet.kotlin.KotlinSampleFragment
import x.chestnut.code.snippet.jetpack.JetpackSampleFragment
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.AnimFragment
import x.chestnut.code.snippet.ui.UIFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:29
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MainFragment : ScrollBaseFragment() {

    override val actionBarTitleId: Int
        get() = R.string.app_name

    override fun onLazyViewCreate(rootView: View) {
        addView("Jetpack") { startFragment(JetpackSampleFragment()) }
        addView("Kotlin") { startFragment(KotlinSampleFragment()) }
        addView("Other Example") { startFragment(OtherExampleFragment()) }
        addView(R.string.title_ui) { startFragment(UIFragment()) }
        addView(R.string.title_anim) { startFragment(AnimFragment()) }
    }
}