package x.chestnut.code.snippet.widget.wheelView

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.widget.wheelView.WheelView
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2019/1/23 16:41
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class WheelView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ScrollView(context, attrs, defStyle) {
    private val tf: Typeface? = null
    private var items: MutableList<String>? = null
    private var views: LinearLayout? = null

    /*每页显示的数量*/
    private var displayCount = 0

    /*偏移量（需要在最前面和最后面补全的个数）*/
    private var offsetTop = 1
    private var offsetBottom = 1

    /*当前选中的标号，要除去偏移量*/
    var selectedIndex = 0
        private set

    /*颜色*/
    private var selectColor = 0
    private var notSelectColor = 0

    /*item*/
    private var tvItemPadding = 0
    private var tvItemTvSize = 0

    /*子Item的高度*/
    private var itemHeight = 0
    private var initialY = 0

    /*检查选中位置的Runnable*/
    private var scrollerTask: Runnable? = null

    /*检查时间，ms*/
    private val newCheck = 50
    var onWheelViewListener: OnWheelViewListener? = null

    interface OnWheelViewListener {
        fun onSelected(selectedIndex: Int, item: String?)
    }

    private fun init(context: Context) {
        this.isVerticalScrollBarEnabled = false
        views = LinearLayout(context)
        views!!.orientation = LinearLayout.VERTICAL
        this.addView(views)
        scrollerTask = Runnable {
            val newY = scrollY
            if (initialY - newY == 0) { // stopped
                val remainder = initialY % itemHeight
                val divided = initialY / itemHeight
                if (remainder == 0) {
                    selectedIndex = divided
                    onSelectedCallBack()
                } else {
                    if (remainder > itemHeight / 2) {
                        post {
                            smoothScrollTo(0, initialY - remainder + itemHeight)
                            selectedIndex = divided + 1
                            onSelectedCallBack()
                        }
                    } else {
                        post {
                            smoothScrollTo(0, initialY - remainder)
                            selectedIndex = divided
                            onSelectedCallBack()
                        }
                    }
                }
            } else {
                initialY = scrollY
                postDelayed(scrollerTask, newCheck.toLong())
            }
        }
    }

    val selectValue: String
        get() = items!![selectedIndex + offsetTop]

    private fun getItems(): List<String>? {
        return items
    }

    fun setItems(list: List<String>?) {
        if (null == items) {
            items = ArrayList()
        }
        items!!.clear()
        items!!.addAll(list!!)
        // 前面和后面补全
        for (i in 0 until offsetTop) {
            items!!.add(0, "")
        }
        for (i in 0 until offsetBottom) {
            items!!.add("")
        }
        //初始化子Views
        displayCount = offsetTop + offsetBottom + 1
        views!!.removeAllViews()
        for (item in items!!) {
            views!!.addView(createView(item))
        }
        refreshItemView(0)
    }

    private fun createView(item: String): TextView {
        val tv = TextView(context)
        tv.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tv.isSingleLine = true
        tv.typeface = tf
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvItemTvSize.toFloat())
        tv.text = item
        tv.gravity = Gravity.CENTER
        tv.setPadding(tvItemPadding, tvItemPadding, tvItemPadding, tvItemPadding)
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv)
            views!!.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayCount)
            //设置父控件的高
            var layoutParams = this.layoutParams
            layoutParams.height = itemHeight * displayCount
            layoutParams = layoutParams
        }
        return tv
    }

    /**
     * ScrollView 的一个方法
     *
     * @param l 变化后的 x 轴位置
     * @param t 变化后的 y 轴位置
     * @param oldl 原来的 x 轴位置
     * @param oldt 原来的 y 轴位置
     */
    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        refreshItemView(t)
    }

    private fun refreshItemView(y: Int) {
        var position = y / itemHeight + offsetTop
        val remainder = y % itemHeight
        val divided = y / itemHeight
        if (remainder == 0) {
            position = divided + offsetTop
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offsetTop + 1
            }
        }
        val childSize = views!!.childCount
        for (i in 0 until childSize) {
            val itemView = views!!.getChildAt(i) as TextView ?: return
            if (position == i) {
                itemView.setTextColor(selectColor)
            } else {
                itemView.setTextColor(notSelectColor)
            }
        }
    }

    /**
     * 选中回调
     */
    private fun onSelectedCallBack() {
        if (null != onWheelViewListener) {
            onWheelViewListener!!.onSelected(selectedIndex, items!![selectedIndex + offsetTop])
        }
    }

    fun setSelection(position: Int) {
        selectedIndex = position
        post {
            smoothScrollTo(0, selectedIndex * itemHeight)
            refreshItemView(selectedIndex * itemHeight)
        }
    }

    fun setSelectionByValue(value: String) {
        for (i in items!!.indices) {
            if (items!![i] == value) {
                selectedIndex = i - offsetTop
                post {
                    smoothScrollTo(0, selectedIndex * itemHeight)
                    refreshItemView(selectedIndex * itemHeight)
                }
                return
            }
        }
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY / 3)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            initialY = scrollY
            postDelayed(scrollerTask, newCheck.toLong())
        }
        return super.onTouchEvent(ev)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun getViewMeasuredHeight(view: View): Int {
        val width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        view.measure(width, expandSpec)
        return view.measuredHeight
    }

    companion object {
        val TAG = WheelView::class.java.simpleName
    }

    /**
     * 构造方法
     * @param context 上下文
     */
    init {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.WheelView)
            offsetTop = array.getInt(R.styleable.WheelView_offset_top, 1)
            offsetBottom = array.getInt(R.styleable.WheelView_offset_bottom, 1)
            selectColor = array.getColor(R.styleable.WheelView_item_tv_select_color, Color.parseColor("#000000"))
            notSelectColor = array.getColor(R.styleable.WheelView_item_tv_not_select_color, Color.parseColor("#bbbbbb"))
            tvItemPadding = array.getDimensionPixelSize(R.styleable.WheelView_item_tv_padding, dip2px(10f))
            tvItemTvSize = array.getDimensionPixelSize(R.styleable.WheelView_item_tv_size, dip2px(12f))
            array.recycle()
        } else {
            offsetTop = 1
            offsetBottom = 1
            selectColor = Color.parseColor("#000000")
            notSelectColor = Color.parseColor("#bbbbbb")
            tvItemPadding = dip2px(10f)
            tvItemTvSize = 14
        }
        init(context)
    }
}