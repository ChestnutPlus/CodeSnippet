package x.chestnut.code.snippet.anim.voiceDbChangeView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import x.chestnut.code.snippet.utils.ViewUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/9/6 17:45
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class VoiceDBChangeView : View {
    private val width = 169f
    private val height = 246f
    private val paintWidth = 15f

    //上方圆形矩形
    private val topRoundRectPath = Path()
    private val bottomRoundRectPath = Path()
    private val showPath = Path()
    private val tempPath = Path()
    private val pitTopPath = Path()
    private val pitBottomPath = Path()
    private val paint = Paint()
    private var changeFactor = 0f
    private val FACTOR_END = 100f
    private val FACTOR_START = 0f

    constructor(context: Context?) : super(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        //上矩形
        val rectF = RectF(width / 2 - 93 / 2, 8F,
                width / 2 + 93 / 2, 169F + 8F)
        topRoundRectPath.addRoundRect(rectF, 47f, 47f, Path.Direction.CCW)
        //最低线的矩形
        val rectF2 = RectF(width / 2 - 23 + paintWidth / 2, height - paintWidth / 2,
                width / 2 - 23 + paintWidth / 2 + 46 - paintWidth, height - paintWidth / 2 + 0.01f)
        bottomRoundRectPath.addRoundRect(rectF2, paintWidth / 2, paintWidth / 2, Path.Direction.CCW)
        //连接线的矩形
        val rectF1 = RectF(width / 2, height - paintWidth / 2,
                width / 2 + 0.01f, height - paintWidth / 2 - 16 - paintWidth / 2)
        bottomRoundRectPath.addRect(rectF1, Path.Direction.CCW)
        //环
        val r = RectF(85 - 87 + paintWidth / 2, 126 - 87 + paintWidth / 2,
                85 + 87 - paintWidth / 2, 126 + 87 - paintWidth / 2)
        bottomRoundRectPath.addArc(r, 14f, 152f)
        //占坑
        val rectF3 = RectF(width / 2 - 93 / 2 + 0.001f, 8 + 0.001f,
                width / 2 + 93 / 2 - 0.001f, 169 + 8 - 0.001f)
        pitTopPath.addRoundRect(rectF3, 47f, 47f, Path.Direction.CCW)
        pitBottomPath.addCircle(85f, 129f, 87 - paintWidth * 0.80f, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(getWidth() / 2 - width / 2, getHeight() / 2 - height / 2)

        paint.color = Color.GRAY
        canvas.drawPath(showPath,paint)

        paint.color = Color.WHITE
        paint.strokeWidth = paintWidth
        canvas.drawPath(topRoundRectPath, paint)
        canvas.drawPath(bottomRoundRectPath, paint)
        paint.color = Color.rgb(0, 201, 252)
        tempPath.reset()
        tempPath.op(topRoundRectPath, showPath, Path.Op.INTERSECT)
        tempPath.op(pitTopPath, Path.Op.DIFFERENCE)
        canvas.drawPath(tempPath, paint)
        tempPath.reset()
        tempPath.op(showPath, bottomRoundRectPath, Path.Op.INTERSECT)
        tempPath.op(pitBottomPath, Path.Op.DIFFERENCE)
        canvas.drawPath(tempPath, paint)
    }

    fun playAnim() {}
    fun pauseAnim() {}
    fun stopAnim() {}
    var factor: Float
        get() = changeFactor
        set(factor) {
            changeFactor = factor
            val temp = (Math.tan(Math.toRadians(13.0)) * width).toFloat()
            val bottomPathHeight: Float = ViewUtils.factorMapping(changeFactor, FACTOR_START,
                    FACTOR_END, height, -temp / 2)
            showPath.reset()
            showPath.moveTo(0f, bottomPathHeight)
            showPath.lineTo(width, bottomPathHeight + temp)
            showPath.lineTo(width, height)
            showPath.lineTo(0f, height)
            showPath.close()
            invalidate()
        }

    fun release() {}

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = paintWidth
    }
}