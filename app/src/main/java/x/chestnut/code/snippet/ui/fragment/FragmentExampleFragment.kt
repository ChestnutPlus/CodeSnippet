package x.chestnut.code.snippet.ui.fragment

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.ui.fragment.backStack.BackFragmentActivity
import x.chestnut.code.snippet.ui.fragment.bottomTab.BottomTabFragmentActivity
import x.chestnut.code.snippet.ui.fragment.lazyLoad.FragmentLazyLoadActivity

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:39
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class FragmentExampleFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        addView("ViewPager懒加载") { startActivity(FragmentLazyLoadActivity::class.java) }
        addView("底部Tab效果") { startActivity(BottomTabFragmentActivity::class.java) }
        addView("Fragment回退栈") { startActivity(BackFragmentActivity::class.java) }
    }

    override val actionBarTitle: String
        get() = "Fragment示例"
}