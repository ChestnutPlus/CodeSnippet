package x.chestnut.code.snippet.ui.recyclerView.itemDecorationStickySection

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.GroupInfo
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.GroupInfoCallback

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
/*
 *   参考：https://blog.csdn.net/briblue/article/details/70211942
 * */
class StickySectionItemDecoration(
        private val groupInfoCallback: GroupInfoCallback?,
        private val sectionHeight: Int, private val dividerHeight: Int
) : ItemDecoration() {

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
            val position = parent.getChildAdapterPosition(view)
            if (groupInfoCallback != null) {
                val groupInfo = groupInfoCallback.getGroupInfo(position)
                //获取位置信息
                val left = view.paddingLeft
                val right = view.width - view.paddingRight
                //画分割线
                if (i != 0) if (!groupInfo!!.isFirstViewInGroup) {
                    val top = view.top - dividerHeight
                    val bottom = view.top
                    mPaint.color = Color.BLACK
                    c.drawRoundRect(left.toFloat(), top.toFloat(), right.toFloat(),
                            bottom.toFloat(), 0f, 0f, mPaint)
                }
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView,
                            state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            //获取位置
            val position = parent.getChildAdapterPosition(view)
            if (groupInfoCallback != null) {
                //获取对应Item的组信息
                val groupInfo = groupInfoCallback.getGroupInfo(position)
                //获取位置信息
                val left = view.paddingLeft
                val right = view.width - view.paddingRight
                //屏幕上第一个可见的 ItemView 时，i == 0;
                if (i != 0) {
                    //只有组内的第一个ItemView之上才绘制
                    if ((groupInfo != null) && groupInfo.isFirstViewInGroup) {
                        val top = view.top - sectionHeight
                        val bottom = view.top
                        drawSection(c, groupInfo, left, top, right, bottom)
                    } else {
                        //在这里画分割线会挡住上面的Section
                        //所以这段逻辑应该放在 onDraw 中
                    }
                } else {
                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。
                    var top = parent.paddingTop

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View
                    //如果是最后一个，说明要判断移动的时候，是否需要Section跟着Item滑动
                    if ((groupInfo != null) && groupInfo.isLastViewInGroup) {

                        //移动的距离就是section的高度与条目底部的差
                        //这个值，在Section需要跟着移动的时候，就是为负数
                        val suggestTop = view.bottom - sectionHeight

                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if (suggestTop < top) {
                            top = suggestTop
                        }
                    }
                    val bottom = top + sectionHeight
                    drawSection(c, groupInfo, left, top, right, bottom)
                }
            }
        }
    }

    private fun drawSection(c: Canvas, groupInfo: GroupInfo?, left: Int,
                            top: Int, right: Int, bottom: Int) {
        //绘制Header-Bg
        mPaint.color = Color.LTGRAY
        c.drawRoundRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), 0f, 0f, mPaint)
        //绘制Header-Title
        val titleX = (left + 10).toFloat()
        val titleY = (bottom - 10).toFloat()
        mPaint.color = Color.WHITE
        mPaint.textSize = 80f
        if (groupInfo?.groupTitle != null) {
            c.drawText(groupInfo.groupTitle, titleX, titleY, mPaint)
        }
    }

    init {
        mPaint.isAntiAlias = true
    }
}