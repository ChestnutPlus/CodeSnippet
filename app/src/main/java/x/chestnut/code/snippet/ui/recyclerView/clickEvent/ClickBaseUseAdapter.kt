package x.chestnut.code.snippet.ui.recyclerView.clickEvent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/27 23:21
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ClickBaseUseAdapter(private val baseUseBeanList: List<BaseUseBean>)
    : RecyclerView.Adapter<ClickBaseUseHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ClickBaseUseHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_view_item, viewGroup, false)
        return ClickBaseUseHolder(view)
    }

    override fun onBindViewHolder(baseUseHolder: ClickBaseUseHolder, i: Int) {
        val useBean = baseUseBeanList[i]
        baseUseHolder.setImageView(useBean.bgRes)
        baseUseHolder.setContent(useBean.content)
        if (mOnItemClickListener != null) {
            baseUseHolder.layout.setOnClickListener { v ->
                mOnItemClickListener?.onItemClick(v, i, useBean)
            }
        }
    }

    override fun getItemCount(): Int {
        return baseUseBeanList.size
    }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener?) {
        this.mOnItemClickListener = mOnItemClickListener
    }
}