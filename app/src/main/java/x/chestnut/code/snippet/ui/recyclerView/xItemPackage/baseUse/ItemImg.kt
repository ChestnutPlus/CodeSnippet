package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
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
class ItemImg(integer: Int) : XItem<Int>(integer) {

    override val itemType: Int
        get() = SimpleAdapter.Item_Img

    private var height = 0
    private var width = 0
    
    fun setHeight(height: Int) {
        this.height = height
    }

    fun setWidth(width: Int) {
        this.width = width
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder {
        return XHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_img, parent, false))
    }

    override fun onBindViewHolder(holder: XHolder, position: Int) {
        val imageView = holder.getViewById(R.id.img) as ImageView
        imageView.setImageResource(data)
        if (height != 0 && width != 0) {
            val layoutParams = imageView.layoutParams
            layoutParams.height = height
            layoutParams.width = width
            imageView.layoutParams = layoutParams
        }
    }

    override fun releaseRes() {}
}