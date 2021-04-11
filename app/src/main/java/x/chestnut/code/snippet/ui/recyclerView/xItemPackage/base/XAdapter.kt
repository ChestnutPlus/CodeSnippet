package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2017/4/27 23:09
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class XAdapter<ITEM : XItem<*>?> : RecyclerView.Adapter<XHolder>() {

    var TAG = "BaseAdapter"
    private var mData: MutableList<ITEM> = ArrayList()
    var data: List<ITEM>?
        get() = mData
        set(data) {
            addAll(data)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder {
        for (i in 0 until itemCount) {
            if (viewType == mData[i]?.itemType) {
                return mData[i]?.onCreateViewHolder(parent, viewType)
                        ?: throw RuntimeException("onCreateViewHolder - can not null")
            }
        }
        throw RuntimeException("BaseAdapter - Wrong ViewType")
    }

    override fun onBindViewHolder(holder: XHolder, position: Int) {
        mData[position]?.onBindViewHolder(holder, position)
        onViewHolderBound(holder, position)
    }

    override fun onViewDetachedFromWindow(holder: XHolder) {
        super.onViewDetachedFromWindow(holder)
        //查到到对应的位置
        val position = holder.adapterPosition
        //越界检查
        if (position < 0 || position >= mData.size) {
            return
        }
        //回调DataBean类中的释放方法
        mData[position]?.releaseRes()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return mData[position]?.itemType ?: 0
    }

    /**
     * add one ITEM
     * @param item ITEM
     */
    fun add(item: ITEM) {
        mData.add(item)
        val index = mData.indexOf(item)
        notifyItemChanged(index)
    }

    fun add(index: Int, item: ITEM) {
        mData.add(index, item)
        notifyItemChanged(index)
    }

    /**
     * remove a ITEM
     * @param item ITEM
     */
    fun remove(item: ITEM) {
        val indexOfCell = mData.indexOf(item)
        remove(indexOfCell)
    }

    fun remove(index: Int) {
        mData.removeAt(index)
        notifyItemRemoved(index)
    }

    /**
     * remove some items
     * @param start index begin
     * @param count the num of items
     */
    fun remove(start: Int, count: Int) {
        if (start + count > mData.size) {
            return
        }
        val size = itemCount
        for (i in start until size) {
            mData.removeAt(i)
        }
        notifyItemRangeRemoved(start, count)
    }

    /**
     * add a item list
     * @param items items
     */
    fun addAll(items: List<ITEM>?) {
        if (items == null || items.isEmpty()) {
            return
        }
        mData.addAll(items)
        notifyItemRangeChanged(mData.size - items.size, mData.size)
    }

    fun addAll(index: Int, items: List<ITEM>?) {
        if (items == null || items.isEmpty()) {
            return
        }
        mData.addAll(index, items)
        notifyItemRangeChanged(index, index + items.size)
    }

    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
     * @param holder holder
     * @param position position
     */
    protected abstract fun onViewHolderBound(holder: XHolder?, position: Int)
}