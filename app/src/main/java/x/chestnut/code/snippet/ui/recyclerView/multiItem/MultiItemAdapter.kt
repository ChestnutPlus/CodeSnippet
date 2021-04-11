package x.chestnut.code.snippet.ui.recyclerView.multiItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.ui.recyclerView.scrollImg.ViewHolderScrollImgItem

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 22:08
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MultiItemAdapter(private val mList: List<IMultiType>,
                       private val recyclerView: RecyclerView
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val holder: ViewHolder
        val item: View
        when (viewType) {
            IMultiType.ItemState.ITEM_IMG -> {
                item = LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.recycler_item_img, viewGroup, false)
                holder = ViewHolderItemImg(item)
            }
            IMultiType.ItemState.ITEM_TV -> {
                item = LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.recycler_item_txt, viewGroup, false)
                holder = ViewHolderItemTxt(item)
            }
            IMultiType.ItemState.ITEM_IMG_TV -> {
                item = LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.recycler_view_item, viewGroup, false)
                holder = ViewHolderItemImgTxt(item)
            }
            IMultiType.ItemState.ITEM_Scroll_IMG_TV -> {
                item = LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.recycler_item_scroll_img, viewGroup, false)
                holder = ViewHolderScrollImgItem(item, recyclerView)
            }
            else -> {
                throw RuntimeException("no found item type!")
            }
        }
        return holder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        when (viewHolder.itemViewType) {
            IMultiType.ItemState.ITEM_IMG -> {
                val itemImgBean = mList[i] as ItemImgBean
                val viewHolderItemImg = viewHolder as ViewHolderItemImg
                viewHolderItemImg.setImageView(itemImgBean.bgRes)
            }
            IMultiType.ItemState.ITEM_TV -> {
                val itemTxtBean = mList[i] as ItemTxtBean
                val viewHolderItemTxt = viewHolder as ViewHolderItemTxt
                viewHolderItemTxt.setContent(itemTxtBean.content)
            }
            IMultiType.ItemState.ITEM_IMG_TV -> {
                val itemImgTxtBean = mList[i] as ItemImgTxtBean
                val viewHolderItemImgTxt = viewHolder as ViewHolderItemImgTxt
                viewHolderItemImgTxt.setContent(itemImgTxtBean.content)
                viewHolderItemImgTxt.setImageView(itemImgTxtBean.bgRes)
            }
            IMultiType.ItemState.ITEM_Scroll_IMG_TV -> {
                val itemScrollImgBean = mList[i] as ItemScrollImgBean
                val scrollImgItem = viewHolder as ViewHolderScrollImgItem
                scrollImgItem.position = i
                scrollImgItem.setImageView(itemScrollImgBean.bgRes)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].itemType
    }
}