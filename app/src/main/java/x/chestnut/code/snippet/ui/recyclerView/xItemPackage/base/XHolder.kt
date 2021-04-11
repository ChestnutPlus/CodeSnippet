package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2017/4/27 23:02
 * desc  :  封装了Holder。
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
open class XHolder(itemView: View) : ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    /**
     * 获取 ItemView
     * @return ItemView
     */
    private val itemHoldView: View = itemView

    /**
     * 获取View,根据ID
     * @param resId id
     * @return view
     */
    fun getViewById(@IdRes resId: Int): View {
        return retrieveView(resId)
    }

    /**
     * 检索View
     * 若，当前缓存的View没有，
     * 则 find and put into the views
     *
     * @param viewId  viewID
     * @param <V> 类型
     * @return  view
    </V> */
    private fun retrieveView(@IdRes viewId: Int) : View {
        var view = views[viewId]
        if (view == null) {
            view = itemHoldView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view
    }
}