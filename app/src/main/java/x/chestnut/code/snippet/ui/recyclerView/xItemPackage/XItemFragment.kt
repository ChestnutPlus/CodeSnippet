package x.chestnut.code.snippet.ui.recyclerView.xItemPackage

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.XItemBaseUseFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale.CenterScaleFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView.CircleRecyclerViewFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:56
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class XItemFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        addView("基本用法-轻松实现多Item布局") { startFragment(XItemBaseUseFragment()) }
        addView("无限循环RecyclerView") { startFragment(CircleRecyclerViewFragment()) }
        addView("中心放大RecyclerView") { startFragment(CenterScaleFragment()) }
    }

    override val actionBarTitle: String
        get() = "X-Item封装"
}