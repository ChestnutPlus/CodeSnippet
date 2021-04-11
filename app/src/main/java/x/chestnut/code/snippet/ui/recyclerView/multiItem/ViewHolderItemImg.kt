package x.chestnut.code.snippet.ui.recyclerView.multiItem

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import x.chestnut.code.snippet.R

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 22:13
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ViewHolderItemImg(itemView: View) : ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.img)

    fun setImageView(@DrawableRes imageView: Int) {
        this.imageView.setImageResource(imageView)
    }
}