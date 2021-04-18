package x.chestnut.code.snippet.anim.voiceAnim

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import x.chestnut.code.snippet.R

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/5 15:19
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class VoiceAnimView : View {

    private var bgBitmap: Bitmap? = null
    private var paint: Paint = Paint()
    private val pointWidth = 6
    private var points: Array<VoiceAnimPoint?> = arrayOfNulls(5)
    private var pointIndex = 0
    private var isRevert = false
    private var isStart = false
    private val r: Runnable = object : Runnable {
        override fun run() {
            invalidate()
            postDelayed(this, 42)
        }
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bgBitmap = (resources.getDrawable(R.drawable.ai_chat_icon) as BitmapDrawable).bitmap
        paint.isAntiAlias = true
        paint.color = -0x3d1c87
        paint.style = Paint.Style.FILL
        paint.strokeWidth = pointWidth.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        points[0] = VoiceAnimPoint(width / 2 - 24, height / 2, 16f, 14.7f, 11f, 7.3f)
        points[1] = VoiceAnimPoint(width / 2 - 12, height / 2, 24f, 21.7f, 15f, 8.3f)
        points[2] = VoiceAnimPoint(width / 2, height / 2, 38f, 33.9f, 22f, 10.1f)
        points[3] = VoiceAnimPoint(width / 2 + 12, height / 2, 24f, 21.7f, 15f, 8.3f)
        points[4] = VoiceAnimPoint(width / 2 + 24, height / 2, 16f, 14.7f, 11f, 7.3f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        bgBitmap?.let {
            setMeasuredDimension(it.width, it.height)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bgBitmap?.let {
            canvas.drawBitmap(it, (width / 2 - it.width / 2).toFloat(), (height / 2 - it.height / 2).toFloat(), paint)
        }
        for (i in points.indices) {
            val point = points[i] ?: continue
            when (indexChangeFunc(pointIndex - i * 2)) {
                0 -> canvas.drawLine(point.centerX.toFloat(), point.centerY.toFloat(), point.centerX.toFloat(), point.centerY + 0.01f, paint)
                2 -> canvas.drawLine(point.centerX.toFloat(), point.centerY - point.halfHeight / 2 + pointWidth / 2,
                        point.centerX.toFloat(), point.centerY + point.halfHeight / 2 - pointWidth / 2, paint)
                4 -> canvas.drawLine(point.centerX.toFloat(), point.centerY - point.maxHeight / 2 + pointWidth / 2,
                        point.centerX.toFloat(), point.centerY + point.maxHeight / 2 - pointWidth / 2, paint)
                1 -> canvas.drawLine(point.centerX.toFloat(), point.centerY - point.oneHeight / 2 + pointWidth / 2,
                        point.centerX.toFloat(), point.centerY + point.oneHeight / 2 - pointWidth / 2, paint)
                3 -> canvas.drawLine(point.centerX.toFloat(), point.centerY - point.threeHeight / 2 + pointWidth / 2,
                        point.centerX.toFloat(), point.centerY + point.threeHeight / 2 - pointWidth / 2, paint)
            }
        }
        if (!isRevert) {
            pointIndex++
        } else {
            pointIndex--
        }
        if (pointIndex == 23) {
            isRevert = true
            pointIndex = 17
        } else if (pointIndex == -6) {
            pointIndex = 0
            isRevert = false
        }
    }

    fun playAnim() {
        if (!isStart) {
            isStart = true
            post(r)
        }
    }

    fun pauseAnim() {
        stopAnim()
    }

    fun stopAnim() {
        if (isStart) {
            isStart = false
            removeCallbacks(r)
        }
    }

    var factor: Float
        get() = 0f
        set(factor) {}

    fun release() {}

    /**
     * 动画轨迹其实符合一个函数
     * 这里传入对应的x，返回函数的y
     * @param x 位置 [0,17]，相隔2，作为其他元素的坐标
     * @return y 4 ： 最大， 3：threeHeight， 2： 一半， 1：oneHeight， 0 ：0 。
     */
    private fun indexChangeFunc(x: Int): Int {
        return if (x < 0) 0 else if (x < 4) x else if (x < 8) -x + 8 else 0
    }

    private class VoiceAnimPoint(var centerX: Int, var centerY: Int, var maxHeight: Float,
                                 var threeHeight: Float, var halfHeight: Float, var oneHeight: Float)
}