package x.chestnut.code.snippet.ui.recyclerView.baseUse

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import x.chestnut.code.snippet.R

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/27 23:35
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class BaseUseHolder(itemView: View) : ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.img)
    private val tv: TextView = itemView.findViewById(R.id.tv)

    fun setImageView(@DrawableRes imageView: Int) {
        this.imageView.setImageResource(imageView)
    }

    fun setContent(content: String?) {
        tv.text = content
    }
}