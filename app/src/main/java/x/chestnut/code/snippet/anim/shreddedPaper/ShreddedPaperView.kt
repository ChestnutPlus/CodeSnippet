package x.chestnut.code.snippet.anim.shreddedPaper

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import x.chestnut.code.snippet.utils.ViewUtils
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/10 11:10
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ShreddedPaperView(context: Context?) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaintWidth = 0f
    private var tvViewWidth = 0f
    private var tvViewHeight = 0f
    private val animWidth = 460
    private val animHeight = 180
    private val path = Path()
    private val random = Random()
    private var changeFactor = 0f
    private val FACTOR_END = 100f
    private val FACTOR_START = 0f
    private var mObjectAnimator: ObjectAnimator? = null
    private var relativeLayout: RelativeLayout? = null

    //三角形
    private var triangleStartX = 0f
    private var triangleStartY = 0f
    private var triangleMoveMaxY = 0f
    private var triangleDataBeans: Array<TriangleDataBean?> = arrayOfNulls(8)
    private var linearDataBeans: Array<LinearDataBean?> = arrayOfNulls(8)
    private var circleDataBeans: Array<CircleDataBean?> = arrayOfNulls(3)

    private fun init(relativeLayout: RelativeLayout, tvView: TextView) {
        this.relativeLayout = relativeLayout
        val params = RelativeLayout.LayoutParams(animWidth, animHeight)
        val pos = intArrayOf(0, 0)
        tvView.getLocationOnScreen(pos)
        params.setMargins(pos[0] - (animWidth - tvView.width) / 2,
                pos[1] - (animHeight - tvView.height), 0, 0)
        relativeLayout.removeView(this)
        relativeLayout.addView(this, params)
        val textPaint = tvView.paint
        textPaintWidth = textPaint.measureText(tvView.text.toString())
        tvViewWidth = tvView.width.toFloat()
        tvViewHeight = tvView.height.toFloat()
        triangleStartX = (animWidth / 2).toFloat()
        triangleStartY = animHeight - tvViewHeight / 2
        triangleMoveMaxY = animHeight - tvViewHeight / 2
        val triangleRotationMessFactor = 0.1f
        val triangleYOffsetMessFactor = 1.0f
        changeFactor = 0f
        triangleDataBeans[0] = TriangleDataBean(1 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, 5f, 20 + (random.nextFloat() - 0.5f) * 50 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[1] = TriangleDataBean(15 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, 6f, 70 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[2] = TriangleDataBean(30 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, 8f, 120 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[3] = TriangleDataBean(45 + (random.nextFloat() - 0.5f) * 10 * triangleRotationMessFactor, 10f, 60 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[4] = TriangleDataBean(-1 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, -5f, 20 + (random.nextFloat() - 0.5f) * 50 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[5] = TriangleDataBean(-15 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, -6f, 50 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[6] = TriangleDataBean(-30 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, -8f, 120 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        triangleDataBeans[7] = TriangleDataBean(-45 + (random.nextFloat() - 0.5f) * 20 * triangleRotationMessFactor, -10f, 60 + (random.nextFloat() - 0.5f) * 100 * triangleYOffsetMessFactor, if (random.nextInt(2) % 2 == 0) 15f else 25f, random.nextInt(2) % 2 == 0)
        val linearSpaceX = textPaintWidth / 9
        val y = 3.14f / 9
        linearDataBeans[0] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 1 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 1).toDouble()).toFloat(), -100 + (random.nextFloat() - 0.5f) * 20, 50 + (random.nextFloat() - 0.5f) * 20, 10f, 5f, 0f)
        linearDataBeans[1] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 2 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 2).toDouble()).toFloat(), -75 + (random.nextFloat() - 0.5f) * 20, 120 + (random.nextFloat() - 0.5f) * 20, 10f, 5f, 10f)
        linearDataBeans[2] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 3 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 3).toDouble()).toFloat(), -30 + (random.nextFloat() - 0.5f) * 20, 50 + (random.nextFloat() - 0.5f) * 20, 15f, 5f, 20f)
        linearDataBeans[3] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 4 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 4).toDouble()).toFloat(), -10 + (random.nextFloat() - 0.5f) * 10, 70 + (random.nextFloat() - 0.5f) * 10, 10f, 5f, 30f)
        linearDataBeans[4] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 5 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 5).toDouble()).toFloat(), 5 + (random.nextFloat() - 0.5f) * 20, 55 + (random.nextFloat() - 0.5f) * 20, 10f, 5f, 40f)
        linearDataBeans[5] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 6 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 6).toDouble()).toFloat(), 26 + (random.nextFloat() - 0.5f) * 20, 50 + (random.nextFloat() - 0.5f) * 20, 15f, 5f, 50f)
        linearDataBeans[6] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 7 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 7).toDouble()).toFloat(), 70 + (random.nextFloat() - 0.5f) * 20, 120 + (random.nextFloat() - 0.5f) * 20, 10f, 5f, 60f)
        linearDataBeans[7] = LinearDataBean(animWidth / 2 - textPaintWidth / 2 + 8 * linearSpaceX, animHeight - tvViewHeight / 2 - linearSpaceX * 1.5f * Math.sin((y * 8).toDouble()).toFloat(), 88 + (random.nextFloat() - 0.5f) * 10, 80 + (random.nextFloat() - 0.5f) * 10, 15f, 5f, 70f)
        circleDataBeans[0] = CircleDataBean(364f, 135f, 50f, 14f, 4f)
        circleDataBeans[1] = CircleDataBean(403f, 165f, 30f, 14f, 4f)
        circleDataBeans[2] = CircleDataBean(352f, 165f, 10f, 14f, 4f)
    }

    override fun onDraw(canvas: Canvas) {
        if (mObjectAnimator == null) return

        //绘制动画区域
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        paint.setColor(Color.WHITE);
//        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

        //绘制三角形
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        for (i in triangleDataBeans!!.indices) {
            val triangleDataBean = triangleDataBeans!![i]
            if (i < triangleDataBeans!!.size / 2) drawTriangle(canvas, triangleDataBean, true) else drawTriangle(canvas, triangleDataBean, false)
        }

        //绘制线性
        for (linearDataBean in linearDataBeans!!) {
            drawLinear(canvas, linearDataBean)
        }

        //绘制圆
        paint.style = Paint.Style.STROKE
        for (circleDataBean in circleDataBeans!!) {
            drawCircle(canvas, circleDataBean)
        }
    }

    private fun drawTriangle(canvas: Canvas, triangleDataBean: TriangleDataBean?, isClockwise: Boolean) {
        val changeFactor: Float = ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END,
                0f, 60f, 0.3f,
                60f, 100f, 0.7f)
        val r: Float
        r = if (isClockwise) Math.toRadians((ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END, 0f, 15f) + triangleDataBean!!.degree).toDouble()).toFloat() else Math.toRadians((ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END, 0f, -15f) + triangleDataBean!!.degree).toDouble()).toFloat()
        val yMax = triangleMoveMaxY + triangleDataBean.yOffset
        var y: Float = triangleStartY - ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END, 0f, yMax)
        var x = triangleStartX
        x = ViewUtils.pointRotateGetX(triangleStartX, triangleStartY, r, x, y) + triangleDataBean.xOffset
        y = ViewUtils.pointRotateGetY(triangleStartX, triangleStartY, r, x, y)

        //边长变化
        val sideLength: Float = ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END,
                0f, triangleDataBean.sideLengthMax, 0.3f,
                triangleDataBean.sideLengthMax, triangleDataBean.sideLengthMax, 0.4f,
                triangleDataBean.sideLengthMax, 0f, 0.3f)

        //绘制三角形
        path.reset()
        val a = 1.0f / 1.732f
        val b = 1.732f / 2f
        var c = (b - a) * sideLength
        val d = c * 1.732f
        c = y + c
        val x1 = x
        val y1 = y - a * sideLength
        val x2 = x + d
        val y2 = c
        val x3 = x - d
        val y3 = c

        //旋转度
        val rotationR: Float = ViewUtils.factorMapping(changeFactor, FACTOR_START, FACTOR_END, 0f, if (triangleDataBean.rotationDir) 3.14f else -3.14f)
        path.moveTo(ViewUtils.pointRotateGetX(x, y, rotationR, x1, y1), ViewUtils.pointRotateGetY(x, y, rotationR, x1, y1))
        path.lineTo(ViewUtils.pointRotateGetX(x, y, rotationR, x2, y2), ViewUtils.pointRotateGetY(x, y, rotationR, x2, y2))
        path.lineTo(ViewUtils.pointRotateGetX(x, y, rotationR, x3, y3), ViewUtils.pointRotateGetY(x, y, rotationR, x3, y3))
        path.close()
        canvas.drawPath(path, paint)
    }

    private fun drawLinear(canvas: Canvas, linearDataBean: LinearDataBean?) {
        val factorBegin = linearDataBean!!.showInFactor
        val factorEnd = 90f
        if (changeFactor > factorBegin && changeFactor <= factorEnd) {

            //一定倾角
            val rotationR = Math.toRadians(linearDataBean.degree.toDouble()).toFloat()

            //y轴的移动
            val yAdd: Float = ViewUtils.factorMapping(changeFactor, factorBegin, factorEnd, 0f, linearDataBean.moveMax)
            val x: Float = ViewUtils.pointRotateGetX(linearDataBean.x, linearDataBean.y,
                    rotationR, linearDataBean.x, linearDataBean.y - yAdd)
            val y: Float = ViewUtils.pointRotateGetY(linearDataBean.x, linearDataBean.y,
                    rotationR, linearDataBean.x, linearDataBean.y - yAdd)
            val p1x = x - linearDataBean.b / 2
            val p1y = y - linearDataBean.a / 2
            val p2x = x + linearDataBean.b / 2
            //长度慢慢减小
            val p3y: Float = p1y + ViewUtils.factorMapping(changeFactor, factorBegin, factorEnd,
                    0f, linearDataBean.a, 0.1f,
                    linearDataBean.a, linearDataBean.a, 0.3f,
                    linearDataBean.a, 0f, 0.6f)
            path.reset()
            path.moveTo(ViewUtils.pointRotateGetX(x, y, rotationR, p1x, p1y),
                    ViewUtils.pointRotateGetY(x, y, rotationR, p1x, p1y))
            path.lineTo(ViewUtils.pointRotateGetX(x, y, rotationR, p2x, p1y),
                    ViewUtils.pointRotateGetY(x, y, rotationR, p2x, p1y))
            path.lineTo(ViewUtils.pointRotateGetX(x, y, rotationR, p2x, p3y),
                    ViewUtils.pointRotateGetY(x, y, rotationR, p2x, p3y))
            path.lineTo(ViewUtils.pointRotateGetX(x, y, rotationR, p1x, p3y),
                    ViewUtils.pointRotateGetY(x, y, rotationR, p1x, p3y))
            path.close()
            canvas.drawPath(path, paint)
        }
    }

    private fun drawCircle(canvas: Canvas, circleDataBean: CircleDataBean?) {
        val factorBegin = circleDataBean!!.showInFactor
        if (factorBegin < changeFactor) {
            //圆环
            val ringWidth: Float = ViewUtils.factorMapping(changeFactor, factorBegin, FACTOR_END, circleDataBean.ringWidth, 0.2f)
            paint.strokeWidth = ringWidth
            val r: Float = ViewUtils.factorMapping(changeFactor, factorBegin, FACTOR_END, 0f, circleDataBean.radius)
            canvas.drawCircle(circleDataBean.x, circleDataBean.y, r, paint)
            //圆环线性
            for (linearPoint in circleDataBean.linearPoints) {
                val begin = linearPoint!!.showInFactor
                if (begin < changeFactor) {
                    val pointWidth: Float = ViewUtils.factorMapping(changeFactor, begin, FACTOR_END, linearPoint.linearLength, 0f)
                    paint.strokeWidth = pointWidth
                    val x = circleDataBean.x
                    var y: Float = ViewUtils.factorMapping(changeFactor, begin, FACTOR_END, 0f, circleDataBean.radius + 25)
                    y += circleDataBean.y
                    val rotationR = Math.toRadians(linearPoint.degree.toDouble()).toFloat()
                    canvas.drawPoint(ViewUtils.pointRotateGetX(circleDataBean.x, circleDataBean.y, rotationR, x, y),
                            ViewUtils.pointRotateGetY(circleDataBean.x, circleDataBean.y, rotationR, x, y), paint)
                }
            }
        }
    }

    fun startAnim(relativeLayout: RelativeLayout, tvView: TextView): ShreddedPaperView {
        init(relativeLayout, tvView)
        if (mObjectAnimator != null && mObjectAnimator!!.isRunning) {
            mObjectAnimator!!.pause()
            mObjectAnimator!!.cancel()
        }
        val objectAnimator = ObjectAnimator.ofFloat(this, "changeFactor", FACTOR_START, FACTOR_END)
        objectAnimator.duration = 1500
        objectAnimator.interpolator = LinearOutSlowInInterpolator()
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                setChangeFactor(0f)
                this@ShreddedPaperView.mObjectAnimator = null
            }
        })
        objectAnimator.start()
        this@ShreddedPaperView.mObjectAnimator = objectAnimator
        return this
    }

    fun release() {
        if (mObjectAnimator != null && mObjectAnimator!!.isRunning) {
            mObjectAnimator!!.pause()
            mObjectAnimator!!.cancel()
        }
        if (relativeLayout != null) {
            relativeLayout!!.removeView(this@ShreddedPaperView)
        }
        relativeLayout = null
        mObjectAnimator = null
        changeFactor = 0f
    }

    fun setChangeFactor(changeFactor: Float) {
        this.changeFactor = changeFactor
        invalidate()
    }

    fun getChangeFactor(): Float {
        return changeFactor
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }
}