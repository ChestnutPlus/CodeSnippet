package x.chestnut.code.snippet.ui.recyclerView

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.ui.recyclerView.baseUse.RecyclerViewBaseUseFragment
import x.chestnut.code.snippet.ui.recyclerView.clickEvent.ClickRecyclerFragment
import x.chestnut.code.snippet.ui.recyclerView.controlSpeed.ControlSpeedRecyclerViewFragment
import x.chestnut.code.snippet.ui.recyclerView.headerFooter.HeaderFooterFragment
import x.chestnut.code.snippet.ui.recyclerView.itemDecoration.ItemDecorationFragment
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.ItemDecorationSectionFragment
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationStickySection.ItemDecorationStickySectionFragment
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemRecyclerFragment
import x.chestnut.code.snippet.ui.recyclerView.scrollImg.ScrollImgItemFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.XItemFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:50
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class RecyclerViewExampleFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        addView("基本用法") { startFragment(RecyclerViewBaseUseFragment()) }
        addView("多种Item") { startFragment(MultiItemRecyclerFragment()) }
        addView("设置点击事件") { startFragment(ClickRecyclerFragment()) }
        addView("设置ItemDecoration分割线") { startFragment(ItemDecorationFragment()) }
        addView("ItemDecoration实现Section") { startFragment(ItemDecorationSectionFragment()) }
        addView("ItemDecoration实现StickySection") { startFragment(ItemDecorationStickySectionFragment()) }
        addView("HeaderFooter实现") { startFragment(HeaderFooterFragment()) }
        addView("Scroll-Img-Item") { startFragment(ScrollImgItemFragment()) }
        addView("Control-RecyclerView-Speed") { startFragment(ControlSpeedRecyclerViewFragment()) }
        addView("X-Item-封装") { startFragment(XItemFragment()) }
    }

    override val actionBarTitle: String
        get() = "RecyclerView示例"
}