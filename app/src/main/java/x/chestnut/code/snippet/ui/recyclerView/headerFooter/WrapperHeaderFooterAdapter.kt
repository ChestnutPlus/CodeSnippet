package x.chestnut.code.snippet.ui.recyclerView.headerFooter

import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseAdapter

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/30 17:43
 * desc  :
 * thanks To:   https://blog.csdn.net/lmj623565791/article/details/51854533
 * dependent on:
 * update log:
</pre> *
 */
class WrapperHeaderFooterAdapter(private val mInnerAdapter: BaseUseAdapter)
    : RecyclerView.Adapter<ViewHolder>() {

    /*定义了，Header，Footer 的 ItemType*/
    private val BASE_ITEM_TYPE_HEADER = 100000
    private val BASE_ITEM_TYPE_FOOTER = 200000

    /* int - object，int 为 ItemType */
    private val mHeaderViews = SparseArrayCompat<View?>()
    private val mFootViews = SparseArrayCompat<View?>()
    /*包装一些扩展的方法*/
    /**
     * 传入的是，列表的Position，0-N
     * 判断是否是Headers
     * @param position 0-N
     * @return true/false
     */
    private fun isHeaderViewPos(position: Int): Boolean {
        return position < headersCount
    }

    private fun isFooterViewPos(position: Int): Boolean {
        return position >= headersCount + realItemCount
    }

    private val realItemCount: Int
        get() = mInnerAdapter.itemCount
    private val headersCount: Int
        get() = mHeaderViews.size()
    private val footersCount: Int
        get() = mFootViews.size()

    fun addHeaderView(view: View?) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    fun addFootView(view: View?) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //先判断是否是，Headers 和 Foots 中的View
        //  是的话，直接创建 InnerViewHolder 并返回
        var view = mHeaderViews[viewType]
        if (view != null) {
            return InnerViewHolder(view)
        }
        view = mFootViews[viewType]
        return view?.let { InnerViewHolder(it) }
                ?: mInnerAdapter.onCreateViewHolder(viewGroup, viewType)
        //不是得话，调用原来的 ViewHolder
    }

    override fun getItemViewType(position: Int): Int {
        //这里传入的Position依然是列表的位置0-N
        return when {
            isHeaderViewPos(position) -> {
                mHeaderViews.keyAt(position)
            }
            isFooterViewPos(position) -> {
                mFootViews.keyAt(position - headersCount - realItemCount)
            }
            else -> {
                //如果都不是调用原来的方法
                super.getItemViewType(position - headersCount)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //判断如果不是，头和尾的时候，才onBindViewHolder
        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }
        mInnerAdapter.onBindViewHolder(viewHolder, position - headersCount)
    }

    override fun getItemCount(): Int {
        return realItemCount + headersCount + footersCount
    }

    /**
     * 适配 GridLayoutManager
     * @param recyclerView recyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val gridLayoutManager = layoutManager
            //当 GridLayoutManager 获取 SpanSize 的时候，回调这个方法
            //所以我们重写它
            gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                /**
                 * SpanSize为多少，表示占用几个item
                 * gridLayoutManager.getSpanCount()
                 * 返回的是我们开始设置的一行几个item数量
                 * @param position  位置
                 * @return 占用几个item
                 */
                override fun getSpanSize(position: Int): Int {
                    //获取类型
                    val viewType = getItemViewType(position)
                    //判断是否是 Headers 或者 是 Footers
                    // 则占用的是所有的 Item，即是：gridLayoutManager.getSpanCount()
                    if (mHeaderViews[viewType] != null) {
                        return gridLayoutManager.spanCount
                    } else if (mFootViews[viewType] != null) {
                        return gridLayoutManager.spanCount
                    }
                    // 如果不是，则，表示占用1个。
                    return 1
                }
            }
        }
    }

    /**
     * 适配 StaggeredGridLayoutManager
     */
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        //mInnerAdapter.onViewAttachedToWindow(holder)
        val position = holder.layoutPosition
        //判断是否是 Headers 或者 是 Footers
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            val lp = holder.itemView.layoutParams
            if (lp is StaggeredGridLayoutManager.LayoutParams) {
                //则 占据 所有的 Span
                lp.isFullSpan = true
            }
        }
    }

    class InnerViewHolder(itemView: View) : ViewHolder(itemView)
}