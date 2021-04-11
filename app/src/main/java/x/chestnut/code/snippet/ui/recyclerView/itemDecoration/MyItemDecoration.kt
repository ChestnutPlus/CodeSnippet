package x.chestnut.code.snippet.ui.recyclerView.itemDecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/30 14:18
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MyItemDecoration : ItemDecoration() {

    private var mDividerHeight = 0f
    private val mPaint: Paint = Paint()

    /**
     * outRect 值得是包裹着整个 Item View
     * 的背景。通过复写 getItemOffsets() 方法，然后指定 outRect 中的
     * top、left、right、bottom 就可以控制各个方向的间隔了。
     * 注意的是这些属性都是偏移量，是指偏移 ItemView 各个方向的数值。
     *
     * 例如，outRect.bottom=1，那么就是相对于 itemView 底部，
     * 有1px的距离，显示的效果就是看起来像是分割线，
     * 且分割线的颜色就是背景色。
     *
     * @param outRect   outRect 是一个全为 0 的 Rect
     * @param view      view 指 RecyclerView 中的 Item
     * @param parent    parent 就是 RecyclerView 本身
     * @param state     state 就是一个状态。
     */
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        //第一个的ItemView不需要分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 5
            mDividerHeight = 5f
        }
    }

    /**
     * getItemOffsets 是针对每一个 ItemView，而 onDraw 方法却是针对 RecyclerView 本身，
     * 所以在 onDraw 方法中需要遍历屏幕上可见的 ItemView，分别获取它们的位置信息，
     * 然后分别的绘制对应的分割线。
     *
     * onDraw 是绘制在itemView下方，如果想要绘制在itemView上方，可以
     * 使用下面的，onDrawOver!
     *
     * @param c         canvas ，可以绘制很多东西
     * @param parent    recyclerView
     * @param state     状态
     */
    override fun onDraw(c: Canvas, parent: RecyclerView,
                        state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(view)
            //第一个ItemView不需要绘制
            if (index == 0) {
                continue
            }
            //得到位置
            //view.getTop()获取到的是Y轴坐标，从上往下递增，所以是 view.getTop() - mDividerHeight
            val dividerTop = view.top - mDividerHeight
            val dividerLeft = parent.paddingLeft.toFloat()
            val dividerBottom = view.top.toFloat()
            val dividerRight = (parent.width - parent.paddingRight).toFloat()
            //手动绘制
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint)
        }
    }

    /**
     * 通常绘制一些排行榜，热搜的信息。
     * @param c         canvas
     * @param parent    recyclerView
     * @param state     状态
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView,
                            state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.BLACK
    }
}