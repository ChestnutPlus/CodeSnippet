package x.chestnut.code.snippet.anim.carTrain.recycler.adapter

import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XAdapter
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2017/4/30 21:27
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarSimpleAdapter : XAdapter<XItem<*>?>() {
    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
     */
    override fun onViewHolderBound(holder: XHolder?, position: Int) {}

    companion object {
        /*在这里统一Item的类型*/
        const val TYPE_ITEM_CAR_Empty = -1
        const val TYPE_ITEM_CAR_Locomotive = 0
        const val TYPE_ITEM_CAR_Menu = 1
        const val TYPE_ITEM_CAR_Classroom = 2
        const val TYPE_ITEM_CAR_Story = 3
        const val TYPE_ITEM_CAR_Market = 4
        const val TYPE_ITEM_CAR_Eng_Check = 6
        const val TYPE_ITEM_CAR_Children = 7
        const val TYPE_ITEM_CAR_Optimization = 8
        const val TYPE_ITEM_CAR_Art = 9
        const val TYPE_ITEM_CAR_Tools = 10
        const val TYPE_ITEM_CAR_ASG = 11
    }
}