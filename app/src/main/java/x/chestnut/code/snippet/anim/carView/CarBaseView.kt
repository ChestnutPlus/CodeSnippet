package x.chestnut.code.snippet.anim.carView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import x.chestnut.code.snippet.utils.ViewUtils.releaseBitmap

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/13 17:13
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class CarBaseView : View {

    //将图像分成多少格
    private val carBodyWidthPart = 2
    private val carBodyHeightPart = 2
    private val carWheelWidthPart = 1
    private val carWheelHeightPart = 1

    //交点坐标的个数
    private val carBodyPartCount = (carBodyWidthPart + 1) * (carBodyHeightPart + 1)
    private val carWheelPartCount = (carWheelWidthPart + 1) * (carWheelHeightPart + 1)

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val carBodyNewsPos = FloatArray(carBodyPartCount * 2)
    private val carBodyOrigPos = FloatArray(carBodyPartCount * 2)
    private val carWheelNewsPos = FloatArray(carWheelPartCount * 2)
    private val carWheelOrigPos = FloatArray(carWheelPartCount * 2)
    protected var bitmapCarBody: Bitmap? = null
    protected var bitmapWheel: Bitmap? = null
    protected var bitmapCarBodyWidth = 0
    protected var bitmapCarBodyHeight = 0
    private val paintFlagsDrawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    protected var paint = Paint()

    //中轴线-x
    protected var centralAxisX = 0
    protected var changeFactor = 0
    protected var delayChangeFactor = 25
    protected var positiveAndNegativeFactor = 0
    protected val FACTOR_MAX = 30
    protected var lastBodyYChange = 0f

    //anim
    private var positive = true
    private var delayPositive = true
    private var positiveAndNegativePositive = true
    private var isStart = false
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            if (positive) changeFactor += 5 else changeFactor -= 5
            if (delayPositive) delayChangeFactor += 5 else delayChangeFactor -= 5
            if (positiveAndNegativePositive) positiveAndNegativeFactor += 5 else positiveAndNegativeFactor -= 5
            invalidate()
            if (changeFactor >= FACTOR_MAX) {
                positive = false
            }
            if (changeFactor <= 0) {
                positive = true
            }
            if (delayChangeFactor >= FACTOR_MAX) {
                delayPositive = false
            }
            if (delayChangeFactor <= 0) {
                delayPositive = true
            }
            postDelayed(this, 60)
        }
    }

    private var onBodyClickListener: OnBodyClickListener? = null

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapCarBody = carBody
        bitmapWheel = carWheel
        if (bitmapCarBody != null) {
            getBitmapMeshPoints(bitmapCarBody, carBodyHeightPart, carBodyWidthPart, carBodyNewsPos, carBodyOrigPos)
            //初始化坐标
            centralAxisX = bitmapCarBody!!.width / 2
            bitmapCarBodyHeight = bitmapCarBody!!.height
            bitmapCarBodyWidth = bitmapCarBody!!.width
        }
        if (bitmapWheel != null) {
            getBitmapMeshPoints(bitmapWheel, carWheelHeightPart, carWheelWidthPart, carWheelNewsPos, carWheelOrigPos)
        }
        initAnimParams()
    }

    private fun initAnimParams() {
        positive = true
        delayPositive = true
        positiveAndNegativePositive = true
        changeFactor = 0
        delayChangeFactor = 25
        positiveAndNegativeFactor = 0
        lastBodyYChange = 0f
    }

    /**
     * 每个车厢的部分UI不尽相同，所以子类要实现这个方法
     * @return bitmap
     */
    protected abstract val carWheel: Bitmap?
    protected abstract val carBody: Bitmap?

    /**
     * 车轮的x方向的偏移量
     * @return 偏移量
     */
    protected open val wheelXOffset: Int
        get() = 0

    protected open val bodyYOffset: Int
        get() = 0

    /**
     * 得到网格点集合
     * @param bitmap bitmap
     * @param heightPart 纵向分割的份数
     * @param widthPart 横向分割的份数
     * @param newPos 新点集合
     * @param origPos 原始点集合
     */
    protected fun getBitmapMeshPoints(bitmap: Bitmap?, heightPart: Int, widthPart: Int, newPos: FloatArray, origPos: FloatArray) {
        //参考：https://www.jianshu.com/p/51d8dd99d27d
        //初始化网格点
        var index = 0
        val bmWidth = bitmap!!.width.toFloat()
        val bmHeight = bitmap.height.toFloat()
        for (i in 0 until heightPart + 1) {
            val fy = bmHeight * i / heightPart
            for (j in 0 until widthPart + 1) {
                val fx = bmWidth * j / widthPart
                //X轴坐标 放在偶数位
                newPos[index * 2] = fx
                origPos[index * 2] = newPos[index * 2]
                //Y轴坐标 放在奇数位
                newPos[index * 2 + 1] = fy
                origPos[index * 2 + 1] = newPos[index * 2 + 1]
                index += 1
            }
        }
    }

    /**
     * 改变车厢的Points
     * @param bitmap bitmap
     * @param posCount 点的个数
     * @param widthPart 横向分割的份数
     * @param heightPart 纵向分割的份数
     * @param newPos 新点集合
     * @param origPos 原始点集合
     * @param xFactor 变化因子
     * @param yFactor 变化因子
     */
    protected fun changeCarBodyPoint(bitmap: Bitmap?, posCount: Int, widthPart: Int, heightPart: Int, newPos: FloatArray, origPos: FloatArray, xFactor: Float, yFactor: Float) {
        //遍历交点，修改
        for (i in 0 until posCount) {
            val x = i * 2
            val y = x + 1
            //x方向的拉伸，离x周轴线越近变化越小，离顶部（y=0）越近，变化也越小
            if (newPos[x] < centralAxisX) {
                newPos[x] = origPos[x] - changeFactor * Math.abs((widthPart + 1) / 2 - i % (widthPart + 1)) * (origPos[y] / bitmap!!.height) * xFactor
            } else if (newPos[x] > centralAxisX) {
                newPos[x] = origPos[x] + changeFactor * Math.abs((widthPart + 1) / 2 - i % (widthPart + 1)) * (origPos[y] / bitmap!!.height) * xFactor
            }
            //y方向的拉伸，离x周轴线越近变化越大，离顶部（y=0）越近，变化也越小
            lastBodyYChange = changeFactor * (widthPart * heightPart / (heightPart + 1) + 1) * (1 - Math.abs(origPos[x] - centralAxisX) / bitmap!!.width) * yFactor
            newPos[y] = origPos[y] + lastBodyYChange
        }
    }

    /**
     * 车轮的坐标变化
     * @param changeFactor 改变因子
     */
    private fun changeWheelPoint(changeFactor: Int) {
        //改变的幅度由temp的大小决定
        var changeFactor = changeFactor
        val temp = (bitmapWheel!!.height / 15).toFloat()
        changeFactor -= 15
        val tempA = changeFactor * temp / FACTOR_MAX
        //需要变化的坐标写死
        carWheelNewsPos[0 * 2] = carWheelOrigPos[0 * 2] - tempA
        carWheelNewsPos[2 * 2] = carWheelOrigPos[2 * 2] - tempA
        carWheelNewsPos[1 * 2] = carWheelOrigPos[1 * 2] + tempA
        carWheelNewsPos[3 * 2] = carWheelOrigPos[3 * 2] + tempA
    }

    /**
     * 绘制Mes网格点
     * @param canvas canvas
     * @param pos 点集
     * @param paint 画笔
     */
    protected fun drawPoint(canvas: Canvas, pos: FloatArray, paint: Paint?) {
        for (i in 0 until pos.size / 2) {
            val x = i * 2
            val y = x + 1
            canvas.drawPoint(pos[x], pos[y], paint!!)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawFilter = paintFlagsDrawFilter

        if (bitmapCarBody != null) {
            //车厢主体
            canvas.save()
            changeCarBodyPoint(bitmapCarBody, carBodyPartCount, carBodyWidthPart, carBodyHeightPart, carBodyNewsPos, carBodyOrigPos, 0.4f, 0.1f)
            canvas.translate((width / 2 - bitmapCarBodyWidth / 2).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 + bodyYOffset).toFloat())
            canvas.drawBitmapMesh(bitmapCarBody!!, carBodyWidthPart, carBodyHeightPart, carBodyNewsPos, 0, null, 0, paint)
            canvas.restore()

            if (bitmapWheel != null) {
                //车轮子，左边
                canvas.save()
                canvas.translate((width / 2 - bitmapWheel!!.width + wheelXOffset).toFloat(), (height / 2 + bitmapCarBody!!.height / 2 - 24).toFloat())
                changeWheelPoint(changeFactor)
                canvas.drawBitmapMesh(bitmapWheel!!, carWheelWidthPart, carWheelHeightPart, carWheelNewsPos, 0, null, 0, paint)
                canvas.restore()

                //车轮子，右边
                canvas.save()
                canvas.translate((width / 2 + 25 + wheelXOffset).toFloat(), (height / 2 + bitmapCarBody!!.height / 2 - 24).toFloat())
                changeWheelPoint(delayChangeFactor)
                canvas.drawBitmapMesh(bitmapWheel!!, carWheelWidthPart, carWheelHeightPart, carWheelNewsPos, 0, null, 0, paint)
                canvas.restore()
            }
        }
    }

    //点击的范围
    private val clickXRange = 300f
    private val clickYRange = 500f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                val x = event.x //触摸点相对于其所在组件坐标系的坐标
                val y = event.y
                val left = width / 2 - clickXRange / 2
                val top = height / 2 - clickYRange / 2
                val right = width / 2 + clickXRange / 2
                val bottom = height / 2 + clickYRange / 2 - 100
                if (x in left..right && y >= top && y <= bottom && event.eventTime - event.downTime < 200) {
                    onBodyClickListener?.onClick()
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    fun setOnBodyClickListener(onBodyClickListener: OnBodyClickListener) {
        this@CarBaseView.onBodyClickListener = onBodyClickListener
    }

    interface OnBodyClickListener {
        fun onClick()
    }

    fun startAnim() {
        if (!isStart) {
            isStart = true
            initAnimParams()
            post(runnable)
        }
    }

    fun stopAnim() {
        if (isStart) {
            isStart = false
            removeCallbacks(runnable)
            this.invalidate()
            initAnimParams()
        }
    }

    /**
     * 改变 factor 的范围
     * @param factor 新因子
     * @return 新值
     */
    protected open fun getChangeFactorByNewFactor(factor: Float): Float {
        return changeFactor * factor / FACTOR_MAX
    }

    protected open fun getDelayChangeFactorByNewFactor(factor: Float): Float {
        return delayChangeFactor * factor / FACTOR_MAX
    }

    open fun release() {
        stopAnim()
        releaseBitmap(bitmapCarBody)
        releaseBitmap(bitmapWheel)
    }

    init {
        paint.style = Paint.Style.STROKE
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.isAntiAlias = true // 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
    }
}