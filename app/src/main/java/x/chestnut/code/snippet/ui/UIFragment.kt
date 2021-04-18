package x.chestnut.code.snippet.ui

import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.ui.drawable.DrawableExampleFragment
import x.chestnut.code.snippet.ui.fragment.FragmentExampleFragment
import x.chestnut.code.snippet.ui.notification.NotificationFragment
import x.chestnut.code.snippet.ui.recyclerView.RecyclerViewExampleFragment
import x.chestnut.code.snippet.ui.textView.TextViewFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/11/8 16:10
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class UIFragment : ScrollBaseFragment() {

    override val actionBarTitleId: Int
        get() = R.string.title_ui

    override fun onViewCreate(rootView: View) {
        super.onViewCreate(rootView)
        addView("Fragment") { startFragment(FragmentExampleFragment()) }
        addView("RecyclerView") { startFragment(RecyclerViewExampleFragment()) }
        addView("Drawable") { startFragment(DrawableExampleFragment()) }
        addView("Notification") { startFragment(NotificationFragment()) }
        addView("TextView") { startFragment(TextViewFragment()) }
    }
}