package x.chestnut.code.snippet.widget.pieView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/6/28 14:38
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class PieView : View {
    private val colors = intArrayOf(
            -0x330100, -0x9b6a13, -0x1cd9ca,
            -0x800000, -0x7f8000, -0x7397,
            -0x7f7f80, -0x194800, -0x830400)
    private var mWidth = 0
    private var mHeight = 0
    private var centerX = 0
    private var centerY = 0
    private var radius = 0
    private val paint = Paint()
    private var rectFArc: RectF? = null
    private var currentStartAngle = 0f
    private var pieDataList: MutableList<PieData>? = ArrayList()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint.isAntiAlias = true
    }

    fun addData(pieData: PieData) {
        pieDataList!!.add(pieData)
        invalidate()
    }

    fun setData(pieDataList: MutableList<PieData>?) {
        this.pieDataList = pieDataList
        invalidate()
    }

    /**
     * 读取View的真正的大小
     * @param w width
     * @param h height
     * @param oldw old width
     * @param oldh old height
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        centerX = width / 2
        centerY = height / 2
        radius = if (mWidth > mHeight) mHeight else mWidth
        radius /= 2
        rectFArc = RectF((centerX - mWidth / 2).toFloat(), (centerY - mHeight / 2).toFloat(),
                (centerX + mWidth / 2).toFloat(), (centerY + mHeight / 2).toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制整个背景圆
        paint.style = Paint.Style.FILL
        paint.color = Color.GRAY
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)
        //绘制扇形
        if (pieDataList != null) for (i in pieDataList!!.indices) {
            val pieData = pieDataList!![i]
            if (pieData.color == -1) {
                paint.color = colors[i % colors.size]
            } else {
                paint.color = pieData.color
            }
            val sweepAngle = (pieData.percentage * 360).toInt()
            canvas.drawArc(rectFArc!!, currentStartAngle, sweepAngle.toFloat(),
                    true, paint)
            currentStartAngle += sweepAngle.toFloat()
        }
    }
}
