package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XAdapter
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 13:57
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class SimpleAdapter : XAdapter<XItem<*>>() {

    override fun onViewHolderBound(holder: XHolder?, position: Int) {}

    companion object {
        const val Item_Img = 1
        const val Item_Txt = 2
        const val Item_Scroll_Img = 3
        const val Item_Img_Txt = 4
    }
}