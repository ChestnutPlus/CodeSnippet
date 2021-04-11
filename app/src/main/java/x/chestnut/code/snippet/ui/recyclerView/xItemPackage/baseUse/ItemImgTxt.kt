package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
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
class ItemImgTxt(integer: Int, private val content: String) : XItem<Int>(integer) {

    override val itemType: Int
        get() = SimpleAdapter.Item_Img_Txt

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder {
        return XHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: XHolder, position: Int) {
        val imageView = holder.getViewById(R.id.img) as ImageView
        imageView.setImageResource(data)
        val textView = holder.getViewById(R.id.tv) as TextView
        textView.text = content
    }

    override fun releaseRes() {}
}