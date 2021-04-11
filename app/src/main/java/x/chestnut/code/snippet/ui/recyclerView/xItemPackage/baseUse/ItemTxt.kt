package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 13:57
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ItemTxt(s: String) : XItem<String>(s) {

    override val itemType: Int
        get() = SimpleAdapter.Item_Txt

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder {
        return XHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_txt, parent, false))
    }

    override fun onBindViewHolder(holder: XHolder, position: Int) {
        val textView = holder.getViewById(R.id.tv) as TextView
        textView.text = data
    }

    override fun releaseRes() {}
}