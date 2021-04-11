package x.chestnut.code.snippet.ui.recyclerView.baseUse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.R

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
class BaseUseAdapter(private val baseUseBeanList: List<BaseUseBean>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseUseHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_view_item, viewGroup, false)
        return BaseUseHolder(view)
    }

    override fun onBindViewHolder(viewHodler: RecyclerView.ViewHolder, i: Int) {
        val useBean = baseUseBeanList[i]
        val baseUseHolder = viewHodler as? BaseUseHolder
        baseUseHolder?.setImageView(useBean.bgRes)
        baseUseHolder?.setContent(useBean.content)
    }

    override fun getItemCount(): Int {
        return baseUseBeanList.size
    }
}