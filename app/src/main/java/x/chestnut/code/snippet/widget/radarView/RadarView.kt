package x.chestnut.code.snippet.widget.radarView

import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/4 10:01
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class RadarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val TAG = "RadarView"
    private val Style_Circle = 1
    private val Style_Rect = 2
    private val style = Style_Circle
    private var mWidth = 0
    private var mHeight = 0

    //多边形的外切圆的半径
    private var mRadius = 0

    //多边形绘制角度的起点
    private val mStartAngle = 0

    //雷达图的层数
    private val mLevel = 5

    //数据的最大值
    private var mTheMaxData = 0f
    private var radarDataBeanList: List<RadarDataBean>? = null
    private val path = Path()
    private val linePaint = Paint()
    private val pointPaint = Paint()
    private val radarPaint = Paint()
    private val radarTextPaint = Paint()
    private val circleBgRectF = RectF()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mRadius = if (mWidth < mHeight) mWidth / 2 else mHeight / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (radarDataBeanList!!.size >= 3) {
            //初始化参数
            val mSlide = radarDataBeanList!!.size
            val mAngleIncrease = 360 / mSlide
            val mRadiusIncrease = mRadius / mLevel
            canvas.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
            //每一层遍历绘制
            for (j in 0 until mLevel) {
                var mAngle = mStartAngle
                val mRadiusTemp = mRadius - j * mRadiusIncrease
                if (style == Style_Circle) {
                    circleBgRectF.left = (-mRadius + mRadiusIncrease * j).toFloat()
                    circleBgRectF.top = (-mRadius + mRadiusIncrease * j).toFloat()
                    circleBgRectF.right = (mRadius - mRadiusIncrease * j).toFloat()
                    circleBgRectF.bottom = (mRadius - mRadiusIncrease * j).toFloat()
                }
                path.reset()
                //绘制每一个顶点
                for (i in 0 until mSlide) {
                    //计算每一个订单的坐标
                    val x = Math.cos(Math.toRadians(mAngle.toDouble())).toFloat() * mRadiusTemp
                    val y = Math.sin(Math.toRadians(mAngle.toDouble())).toFloat() * mRadiusTemp
                    //绘制从圆心到最外层的线
                    if (j == 0) {
                        canvas.drawLine(0f, 0f, x, y, linePaint)
                        //更新bean坐标
                        val dataBean = radarDataBeanList!![i]
                        dataBean.x = Math.cos(Math.toRadians(mAngle.toDouble())).toFloat() * (mRadiusTemp * dataBean.value / mTheMaxData)
                        dataBean.y = Math.sin(Math.toRadians(mAngle.toDouble())).toFloat() * (mRadiusTemp * dataBean.value / mTheMaxData)
                    }
                    //绘制多边形
                    if (style == Style_Rect) {
                        if (i == 0) path.moveTo(x, y) else {
                            path.lineTo(x, y)
                        }
                    } else {
                        if (i == 0) path.moveTo(x, y)
                        path.addArc(circleBgRectF, mAngle.toFloat(), mAngleIncrease.toFloat())
                    }
                    mAngle += mAngleIncrease
                }
                if (style == Style_Rect) path.close()
                canvas.drawPath(path, linePaint)
            }
            //绘制雷达图的端点
            path.reset()
            for (i in 0 until mSlide) {
                val dataBean = radarDataBeanList!![i]
                canvas.drawPoint(dataBean.x, dataBean.y, pointPaint)
                if (i == 0) {
                    path.moveTo(dataBean.x, dataBean.y)
                } else {
                    path.lineTo(dataBean.x, dataBean.y)
                }
                if (!TextUtils.isEmpty(dataBean.name)) canvas.drawText(dataBean.name, dataBean.x + pointPaint.strokeWidth, dataBean.y, radarTextPaint)
            }
            path.close()
            canvas.drawPath(path, radarPaint)
        }
    }

    /**
     * 对外暴露接口
     */
    fun setData(radarDataBeanList: List<RadarDataBean>?) {
        this.radarDataBeanList = radarDataBeanList
        if (radarDataBeanList != null) for (i in radarDataBeanList.indices) {
            if (radarDataBeanList[i].value > mTheMaxData) mTheMaxData = radarDataBeanList[i].value
        }
        invalidate()
    }

    fun setRadarPointColor(color: Int) {
        pointPaint.color = color
        invalidate()
    }

    fun setRadarPointWidth(width: Int) {
        pointPaint.strokeWidth = width.toFloat()
        invalidate()
    }

    fun setTheMaxData(mTheMaxData: Float) {
        if (this.mTheMaxData < mTheMaxData) this.mTheMaxData = mTheMaxData
    }

    init {
        linePaint.isAntiAlias = true
        linePaint.color = -0x303031
        linePaint.strokeWidth = 5f
        linePaint.style = Paint.Style.STROKE
        pointPaint.isAntiAlias = true
        pointPaint.color = -0xffff01
        pointPaint.strokeWidth = 20f
        pointPaint.style = Paint.Style.STROKE
        pointPaint.strokeCap = Paint.Cap.ROUND
        radarPaint.isAntiAlias = true
        radarPaint.color = -0x77828204
        radarPaint.style = Paint.Style.FILL
        radarTextPaint.isAntiAlias = true
        radarTextPaint.color = Color.BLACK
        radarTextPaint.style = Paint.Style.FILL
        radarTextPaint.textSize = 30f
    }
}