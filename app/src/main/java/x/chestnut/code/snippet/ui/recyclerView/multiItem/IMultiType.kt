package x.chestnut.code.snippet.ui.recyclerView.multiItem

import androidx.annotation.IntDef

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 22:26
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
interface IMultiType {

    @ItemState
    val itemType: Int

    @IntDef(ItemState.ITEM_IMG, ItemState.ITEM_TV, ItemState.ITEM_IMG_TV,
            ItemState.ITEM_Scroll_IMG_TV)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ItemState {
        companion object {
            const val ITEM_IMG = 1
            const val ITEM_TV = 2
            const val ITEM_IMG_TV = 3
            const val ITEM_Scroll_IMG_TV = 4
        }
    }
}