package x.chestnut.code.snippet.anim.hlLoading

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ViewUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/11/5 12:03
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
 * 动画描述：
 * 1.  总时间为1秒16帧，每秒25帧，一共42帧，1680ms。
 * 2.  圆点一共20个，每个的圆心偏移18度
 * 3.  1-21帧：20个小圆点逐渐出现，从0°的位置开始出现
 * 4.  22-42帧：20个小圆逐渐消失，从0°的位置开始消失
 * 5.  23，24帧，闭眼，其他帧，开眼
 *
</pre> *
 */
class HlLoadingView : View {

    companion object {
        const val FACTOR_END = 42f
        const val FACTOR_START = 0f
    }

    private var bitmapHead: Bitmap? = null
    private var bitmapEyeOpen: Bitmap? = null
    private var bitmapEyeClose: Bitmap? = null
    private val paint = Paint()

    /*动画参数*/
    private var factor = 0f
    private var objectAnimator: ObjectAnimator? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapHead = BitmapFactory.decodeResource(resources, R.drawable.hl_loading_head)
        bitmapEyeOpen = BitmapFactory.decodeResource(resources, R.drawable.hl_eye_open)
        bitmapEyeClose = BitmapFactory.decodeResource(resources, R.drawable.hl_eye_close)
        paint.flags = Paint.ANTI_ALIAS_FLAG
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.rgb(207, 234, 233))
        canvas.drawBitmap(bitmapHead!!, (width / 2 - bitmapHead!!.width / 2).toFloat(), (height / 2 - bitmapHead!!.height / 2).toFloat(), paint)
        //绘制小圆点
        val x0 = (width / 2).toFloat()
        val y0 = (height / 2).toFloat()
        val x = (width / 2).toFloat()
        val y = (height / 2 - 164).toFloat()
        val rotation = 18
        //小圆点出现
        if (factor <= 21) {
            var i = 0
            while (i < 360 / rotation && i <= factor) {
                val tempX = ViewUtils.pointRotateGetX(x0, y0, Math.toRadians((rotation * i).toDouble()).toFloat(), x, y).toInt()
                val tempY = ViewUtils.pointRotateGetY(x0, y0, Math.toRadians((rotation * i).toDouble()).toFloat(), x, y).toInt()
                drawSmallCircle(tempX, tempY, paint, canvas)
                i++
            }
            canvas.drawBitmap(bitmapEyeOpen!!, (width / 2 - bitmapEyeOpen!!.width / 2).toFloat(), (height / 2 - bitmapEyeOpen!!.height / 2 + 18).toFloat(), paint)
        } else if (factor <= 42) {
            for (i in (factor - 21).toInt() + 1 until 360 / rotation) {
                val tempX = ViewUtils.pointRotateGetX(x0, y0, Math.toRadians((rotation * i).toDouble()).toFloat(), x, y).toInt()
                val tempY = ViewUtils.pointRotateGetY(x0, y0, Math.toRadians((rotation * i).toDouble()).toFloat(), x, y).toInt()
                drawSmallCircle(tempX, tempY, paint, canvas)
            }
            //眨眼睛
            if (factor >= 23 && factor <= 27) {
                canvas.drawBitmap(bitmapEyeClose!!, (width / 2 - bitmapEyeClose!!.width / 2).toFloat(), (height / 2 - bitmapEyeClose!!.height / 2 + 18).toFloat(), paint)
            } else {
                canvas.drawBitmap(bitmapEyeOpen!!, (width / 2 - bitmapEyeOpen!!.width / 2).toFloat(), (height / 2 - bitmapEyeOpen!!.height / 2 + 18).toFloat(), paint)
            }
        }
    }

    private fun drawSmallCircle(x: Int, y: Int, paint: Paint, canvas: Canvas) {
        //黄色低
        paint.color = Color.rgb(249, 184, 56)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(x.toFloat(), y.toFloat(), 16f, paint)
        //白色边
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(x.toFloat(), y.toFloat(), (16 + 10 / 2).toFloat(), paint)
    }

    fun getFactor(): Float {
        return factor
    }

    fun setFactor(factor: Float) {
        this.factor = factor
        this.invalidate()
    }

    fun playAnim() {
        objectAnimator?.cancel()
        objectAnimator = ObjectAnimator.ofFloat(this, "factor",
                FACTOR_START, FACTOR_END).apply {
            duration = 5000
            interpolator = LinearInterpolator()
            start()
        }
    }

    fun pauseAnim() {
        objectAnimator?.pause()
    }

    fun stopAnim() {
        objectAnimator?.apply {
            pause()
            cancel()
        }
    }

    fun release() {
        stopAnim()
        objectAnimator = null
        ViewUtils.releaseBitmap(bitmapHead)
        ViewUtils.releaseBitmap(bitmapEyeOpen)
        ViewUtils.releaseBitmap(bitmapEyeClose)
    }
}