package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection

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
 * time  : 2019/3/30 15:31
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class SectionItemDecoration(private val groupInfoCallback: GroupInfoCallback?,
                            private val sectionHeight: Int, private val dividerHeight: Int)
    : ItemDecoration() {

    private val mPaint: Paint = Paint()

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (groupInfoCallback != null) {
            //获取对应Item的组信息
            val groupInfo = groupInfoCallback.getGroupInfo(position)
            //如果是组的第一个，那么就要显示Header，在这里就先撑开对应的大小
            if (groupInfo != null && groupInfo.isFirstViewInGroup) {
                outRect.top = sectionHeight
            } else {
                outRect.top = dividerHeight
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView,
                        state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            //获取位置
            val position = parent.getChildAdapterPosition(view)
            if (groupInfoCallback != null) {
                //获取对应Item的组信息
                val groupInfo = groupInfoCallback.getGroupInfo(position)
                //获取位置信息
                var top: Int
                val left = view.paddingLeft
                val right = view.width - view.paddingRight
                val bottom = view.top
                if (groupInfo != null && groupInfo.isFirstViewInGroup) {
                    //只有组的第一个ItemView才绘制Header
                    top = view.top - sectionHeight
                    //绘制Header-Bg
                    mPaint.color = Color.LTGRAY
                    c.drawRoundRect(left.toFloat(), top.toFloat(), right.toFloat(),
                            bottom.toFloat(), 0f, 0f, mPaint)
                    //绘制Header-Title
                    val titleX = (left + 10).toFloat()
                    val titleY = (bottom - 10).toFloat()
                    mPaint.color = Color.WHITE
                    mPaint.textSize = 80f
                    c.drawText(groupInfo.groupTitle, titleX, titleY, mPaint)
                } else {
                    //如果不是，则绘制分割线
                    top = view.top - dividerHeight
                    mPaint.color = Color.BLACK
                    c.drawRoundRect(left.toFloat(), top.toFloat(), right.toFloat(),
                            bottom.toFloat(), 0f, 0f, mPaint)
                }
            }
        }
    }

    init {
        mPaint.isAntiAlias = true
    }
}