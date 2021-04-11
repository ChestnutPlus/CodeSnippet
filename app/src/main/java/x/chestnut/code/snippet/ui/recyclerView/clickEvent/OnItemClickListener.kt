package x.chestnut.code.snippet.ui.recyclerView.clickEvent

import android.view.View
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 23:43
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
interface OnItemClickListener {
    fun onItemClick(view: View?, position: Int, data: BaseUseBean)
}