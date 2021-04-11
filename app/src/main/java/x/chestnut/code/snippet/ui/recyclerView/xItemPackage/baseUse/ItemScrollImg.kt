package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
class ItemScrollImg(integer: Int, private val recyclerView: RecyclerView) 
    : XItem<Int>(integer) {

    override val itemType: Int
        get() = SimpleAdapter.Item_Scroll_Img
    
    private val scrollYTopOffset = 100
    private val scrollYBottomOffset = 100
    private var imageView: ImageView? = null
    private var position = 0
    private var maxScrollItemTop = 0
    private val onScrollListener: RecyclerView.OnScrollListener = object 
        : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            // 判断是linearLayoutManager
            val img = imageView ?: return
            if (img.drawable != null
                    && recyclerView.layoutManager is LinearLayoutManager) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                /*记录PaddingStart和PaddingEnd的值，后面需要恢复*/
                val paddingLeft = img.paddingLeft
                val paddingRight = img.paddingRight

                /*ImageView实际的显示区域*/
                val imageViewWidth = img.width - paddingLeft - paddingRight
                val imageViewHeight = img.height

                /*图片的大小，内存中的，并非实际大小*/
                val srcWidth = img.drawable.bounds.width()
                val srcHeight = img.drawable.bounds.height()

                //linearLayoutManager-VERTICAL，控件的宽度要大于高度，且图片的是一张长图
                if (linearLayoutManager.orientation == LinearLayoutManager.VERTICAL
                        && imageViewWidth > imageViewHeight && srcHeight >= srcWidth) {

                    /*按照，图片铺满宽度，换算出，图片在这种比例下的height*/
                    val realHeight = srcHeight * imageViewWidth / srcWidth

                    /*算出，在最后一个，第一个的时候，paddingTop的绝对值*/
                    val paddingTop = realHeight - imageViewHeight

                    // 当最后一个可见，并非完全可见的时候，初始化位置
                    if (linearLayoutManager.findLastVisibleItemPosition() == position) {
                        img.setPadding(paddingLeft, paddingTop, paddingRight, 0)
                    } else if (linearLayoutManager.findFirstVisibleItemPosition() == position) {
                        img.setPadding(paddingLeft, -paddingTop, paddingRight, 0)
                    } else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= position
                            && position <= linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                        val view = linearLayoutManager.findViewByPosition(position)
                        if (view != null) {
                            //可移动的距离
                            val scrollImg = realHeight - imageViewHeight
                            if (view.y > maxScrollItemTop) maxScrollItemTop = view.y.toInt()
                            if (maxScrollItemTop - scrollYBottomOffset > view.y
                                    && view.y > scrollYTopOffset) {
                                //图片移动的距离
                                val temp = (((view.y - scrollYTopOffset)
                                        * (scrollImg + paddingTop)
                                        / (maxScrollItemTop - scrollYBottomOffset - scrollYTopOffset)).toInt()
                                        - paddingTop)
                                img.setPadding(paddingLeft, temp, paddingRight, 0)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder {
        return XHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_scroll_img, parent, false))
    }

    override fun onBindViewHolder(holder: XHolder, position: Int) {
        this.position = position
        imageView = holder.getViewById(R.id.img) as ImageView
        imageView?.setImageResource(data)
        recyclerView.addOnScrollListener(onScrollListener)
    }

    override fun releaseRes() {
        recyclerView.addOnScrollListener(onScrollListener)
    }
}