package x.chestnut.code.snippet.anim.carView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.util.AttributeSet
import android.view.View

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
    protected val FACTOR_MAX = 30

    //anim
    private var positive = true
    private var delayPositive = true
    private var isStart = false
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            if (positive) changeFactor += 5 else changeFactor -= 5
            if (delayPositive) delayChangeFactor += 5 else delayChangeFactor -= 5
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

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapCarBody = carBody
        bitmapWheel = carWheel
        getBitmapMeshPoints(bitmapCarBody, carBodyHeightPart, carBodyWidthPart, carBodyNewsPos, carBodyOrigPos)
        getBitmapMeshPoints(bitmapWheel, carWheelHeightPart, carWheelWidthPart, carWheelNewsPos, carWheelOrigPos)
        //初始化坐标
        centralAxisX = bitmapCarBody!!.width / 2
        bitmapCarBodyHeight = bitmapCarBody!!.height
        bitmapCarBodyWidth = bitmapCarBody!!.width
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
    protected val wheelXOffset: Int
        protected get() = 0

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
            newPos[y] = origPos[y] + changeFactor * (widthPart * heightPart / (heightPart + 1) + 1) * (1 - Math.abs(origPos[x] - centralAxisX) / bitmap!!.width) * yFactor
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

        //车厢主体
        canvas.save()
        changeCarBodyPoint(bitmapCarBody, carBodyPartCount, carBodyWidthPart, carBodyHeightPart, carBodyNewsPos, carBodyOrigPos, 0.4f, 0.1f)
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2).toFloat(), (height / 2 - bitmapCarBodyHeight / 2).toFloat())
        canvas.drawBitmapMesh(bitmapCarBody!!, carBodyWidthPart, carBodyHeightPart, carBodyNewsPos, 0, null, 0, paint)
        canvas.restore()

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

    fun startAnim() {
        if (!isStart) {
            isStart = true
            post(runnable)
        }
    }

    fun stopAnim() {
        if (isStart) {
            isStart = false
            removeCallbacks(runnable)
            changeFactor = 0
            delayChangeFactor = 25
            this.invalidate()
        }
    }

    init {
        paint.style = Paint.Style.STROKE
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.isAntiAlias = true // 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
    }
}