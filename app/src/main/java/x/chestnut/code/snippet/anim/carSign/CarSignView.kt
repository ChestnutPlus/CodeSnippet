package x.chestnut.code.snippet.anim.carSign

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.Nullable
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ViewUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/3 17:04
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
open class CarSignView : View {

    var isReadyToShowAnim = true

    //字体
    private var signStr: String? = null
    private var typeface: Typeface? = null
    private var signStrSize = 48f
    private var bitmapCover: Bitmap? = null
    private var bitmapTrunk: Bitmap? = null
    private val rectFSign: RectF = RectF(0F, 0F, 240F, 290F)
    private val paint = Paint()
    private val path = Path()
    private val myMatrix = Matrix()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        bitmapCover = BitmapFactory.decodeResource(resources, R.drawable.car_sign_cover)
        bitmapTrunk = BitmapFactory.decodeResource(resources, R.drawable.car_sign_trunk)
    }

    init {
        paint.style = Paint.Style.STROKE
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isReadyToShowAnim) return
        //绘制动画区域
//        paint.color = Color.GRAY
//        canvas.translate(100f, 20f)
//        canvas.drawRect(rectFSign, paint)

        val bTrunk = bitmapTrunk
        val bCover = bitmapCover
        bTrunk ?: return
        bCover ?: return

        // bTrunk
        //横向位移，bTrunk width/2
        val xOffset: Float = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END,
                0F, (bTrunk.width / 2).toFloat())

        //纵向位移，( rectFSign.bottom - 35 , 5 , 120 )
        var yOffset: Float = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END,
                rectFSign.bottom - 35, 5F, 120F)

        //旋转，21° - 0°，逆时针
        var rotationOffset: Float = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END,
                21F, 0F)
        myMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bTrunk, rotationOffset,
                rectFSign.right / 2 - xOffset, yOffset, myMatrix)
        canvas.drawBitmap(bTrunk, myMatrix, paint)

        // bCover
        //旋转，-10° - 0°，逆时针
        rotationOffset = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END,
                -10F, 0F)

        //纵向位移，( rectFSign.bottom , 80 , 108 )
        yOffset = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END, rectFSign.bottom,
                80F, 108F)
        myMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bCover, rotationOffset,
                rectFSign.right / 2 - bCover.width / 2, yOffset, myMatrix)
        canvas.drawBitmap(bCover, myMatrix, paint)

        // sign str
        signStr?.let { str ->
            // str 的绘制 path
            paint.color = Color.RED
            path.reset()
            //左下点
            val x0: Float = rectFSign.right / 2 - bCover.width / 2
            val y0: Float = yOffset + bCover.height
            //旋转点
            val rX0: Float = x0 + bCover.width / 2
            val rY0: Float = y0 - bCover.height / 2
            //右下点
            val x1: Float = x0 + bCover.width
            val radians = Math.toRadians(rotationOffset.toDouble()).toFloat()
            path.moveTo(ViewUtils.pointRotateGetX(rX0, rY0, radians, x0, y0),
                    ViewUtils.pointRotateGetY(rX0, rY0, radians, x0, y0))
            path.lineTo(ViewUtils.pointRotateGetX(rX0, rY0, radians, x1, y0),
                    ViewUtils.pointRotateGetY(rX0, rY0, radians, x1, y0))
            paint.color = Color.WHITE
            paint.textSize = signStrSize
            if (typeface != null) paint.typeface = typeface
            val hOffset: Float = (bCover.width - paint.measureText(str)) / 2
            canvas.drawTextOnPath(str, path, hOffset, -20f, paint)
        }
    }

    fun setSignStr(signStr: String?) {
        this.signStr = signStr
    }

    fun setTypeface(typeface: Typeface?) {
        this.typeface = typeface
    }

    fun setSignStrSize(signStrSize: Float) {
        this.signStrSize = signStrSize
    }

    /*动画参数*/
    private var objectAnimator: ObjectAnimator? = null
    private val PROGRESS_END = 100f
    private val PROGRESS_START = 0f
    private var progress = PROGRESS_START

    fun playAnim() {
        if (!isReadyToShowAnim) return
        if (progress == 100f) {
            progress = 0f
            objectAnimator = null
        }
        if (objectAnimator != null) {
            objectAnimator?.resume()
        } else {
            objectAnimator = ObjectAnimator.ofFloat(this, "progress",
                    progress, PROGRESS_END)
            val time = ViewUtils.factorMapping(progress, PROGRESS_START, PROGRESS_END,
                    3000F, 0F)
            objectAnimator?.duration = time.toLong()
            objectAnimator?.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator?.start()
        }
    }

    fun pauseAnim() {
        objectAnimator?.pause()
        this.invalidate()
    }

    fun stopAnim() {
        objectAnimator?.pause()
        objectAnimator?.cancel()
        progress = 0f
        this.invalidate()
        objectAnimator = null
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    fun setProgressByUser(progress: Float) {
        this.progress = progress
        invalidate()
        objectAnimator = null
    }

    fun getProgress(): Float {
        return progress
    }

    open fun release() {
        stopAnim()
        objectAnimator = null
        ViewUtils.releaseBitmap(bitmapCover)
        ViewUtils.releaseBitmap(bitmapTrunk)
    }
}