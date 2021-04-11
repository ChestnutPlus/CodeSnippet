package x.chestnut.code.snippet.ui.recyclerView.multiItem

import androidx.annotation.DrawableRes
import x.chestnut.code.snippet.ui.recyclerView.multiItem.IMultiType
import x.chestnut.code.snippet.ui.recyclerView.multiItem.ItemImgBean

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/30 23:51
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ItemScrollImgBean : IMultiType {
    @DrawableRes
    var bgRes = 0
    override val itemType: Int
        get() = IMultiType.ItemState.ITEM_Scroll_IMG_TV
}