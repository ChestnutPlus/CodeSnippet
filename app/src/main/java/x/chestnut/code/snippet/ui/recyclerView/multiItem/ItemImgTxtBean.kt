package x.chestnut.code.snippet.ui.recyclerView.multiItem

import androidx.annotation.DrawableRes
import x.chestnut.code.snippet.ui.recyclerView.multiItem.IMultiType.ItemState.Companion.ITEM_IMG_TV

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 22:07
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ItemImgTxtBean : IMultiType {
    var content: String? = null
    @DrawableRes
    var bgRes = 0
    override val itemType: Int
        get() = ITEM_IMG_TV
}