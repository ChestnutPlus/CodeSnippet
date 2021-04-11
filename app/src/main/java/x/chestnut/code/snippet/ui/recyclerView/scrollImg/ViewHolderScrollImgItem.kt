package x.chestnut.code.snippet.ui.recyclerView.scrollImg

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import x.chestnut.code.snippet.R

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/30 23:53
 * desc  :
 * thanks To:   在固定长宽imageView上,显示图片指定区域
 * https://blog.csdn.net/leol_2/article/details/80281683
 * dependent on:
 * update log:
</pre> *
 */
class ViewHolderScrollImgItem(itemView: View, recyclerView: RecyclerView) : ViewHolder(itemView) {

    private val scrollYTopOffset = 100
    private val scrollYBottomOffset = 100
    private val imageView: ImageView = itemView.findViewById(R.id.img)
    private var minPosition = 0
    private var maxScrollItemTop = 0
    fun setImageView(@DrawableRes img: Int) {
        imageView.setImageResource(img)
    }

    fun setPosition(position: Int) {
        this.minPosition = position
    }

    init {
        val onScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 判断是linearLayoutManager
                if (imageView.drawable != null
                        && recyclerView.layoutManager is LinearLayoutManager) {
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                    /*记录PaddingStart和PaddingEnd的值，后面需要恢复*/
                    val paddingLeft = imageView.paddingLeft
                    val paddingRight = imageView.paddingRight

                    /*ImageView实际的显示区域*/
                    val imageViewWidth = imageView.width - paddingLeft - paddingRight
                    val imageViewHeight = imageView.height

                    /*图片的大小，内存中的，并非实际大小*/
                    val srcWidth = imageView.drawable.bounds.width()
                    val srcHeight = imageView.drawable.bounds.height()

                    //linearLayoutManager-VERTICAL，控件的宽度要大于高度，且图片的是一张长图
                    if (linearLayoutManager.orientation == LinearLayoutManager.VERTICAL
                            && imageViewWidth > imageViewHeight && srcHeight >= srcWidth) {

                        /*按照，图片铺满宽度，换算出，图片在这种比例下的height*/
                        val realHeight = srcHeight * imageViewWidth / srcWidth

                        /*算出，在最后一个，第一个的时候，paddingTop的绝对值*/
                        val paddingTop = realHeight - imageViewHeight

                        // 当最后一个可见，并非完全可见的时候，初始化位置
                        if (linearLayoutManager.findLastVisibleItemPosition() == minPosition) {
                            imageView.setPadding(paddingLeft, paddingTop, paddingRight, 0)
                        } else if (linearLayoutManager.findFirstVisibleItemPosition() == minPosition) {
                            imageView.setPadding(paddingLeft, -paddingTop, paddingRight, 0)
                        } else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= minPosition
                                && minPosition <= linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                            val view = linearLayoutManager.findViewByPosition(minPosition)
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
                                    imageView.setPadding(paddingLeft, temp, paddingRight, 0)
                                }
                            }
                        }
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(onScrollListener)
    }
}