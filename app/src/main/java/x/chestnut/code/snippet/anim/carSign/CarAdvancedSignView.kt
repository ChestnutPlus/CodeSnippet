package x.chestnut.code.snippet.anim.carSign

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.*
import android.os.Build
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
 * time  : 2018/8/14 23:37
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarAdvancedSignView constructor(context: Context?, @Nullable attrs: AttributeSet? = null)
    : View(context, attrs) {

    var trunkX = 0f
    var trunkY = 0f
    var trunkRotationDegree = 0f
    var coverX = 0f
    var coverY = 0f
    var coverRotationDegree = 0f
    private val bitmapCover: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.car_sign_cover)
    private val bitmapTrunk: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.car_sign_trunk)
    private var objectAnimator: ObjectAnimator? = null
    private var signStr: String? = null
    private var typeface: Typeface? = null
    private var signStrSize = 48f
    private val path = Path()
    private val rectFSign = RectF(0f, 0f, 240f, 290f)
    private val paint = Paint()
    private val myMatrix = Matrix()
    private val paintFlagsDrawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    init {
        paint.style = Paint.Style.STROKE
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.isAntiAlias = true
        initParams()
    }

    private fun initParams() {
        trunkX = rectFSign.right / 2
        trunkY = rectFSign.bottom - 35
        trunkRotationDegree = 21f
        coverX = rectFSign.right / 2 - bitmapCover.width / 2
        coverY = rectFSign.bottom
        coverRotationDegree = -10f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawFilter = paintFlagsDrawFilter

        //绘制动画区域
        paint.color = Color.GRAY
        canvas.translate(100f, 20f)
        canvas.drawRect(rectFSign, paint)
        myMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapTrunk, trunkRotationDegree, trunkX, trunkY, myMatrix)
        canvas.drawBitmap(bitmapTrunk, myMatrix, paint)
        myMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapCover, coverRotationDegree, coverX, coverY, myMatrix)
        canvas.drawBitmap(bitmapCover, myMatrix, paint)

        // sign str
        signStr?.let {
            // str 的绘制 path
            paint.color = Color.RED
            path.reset()
            //左下点
            val x0 = rectFSign.right / 2 - bitmapCover.width / 2
            val y0 = coverY + bitmapCover.height
            //旋转点
            val rX0 = x0 + bitmapCover.width / 2
            val rY0 = y0 - bitmapCover.height / 2
            //右下点
            val x1 = x0 + bitmapCover.width
            val radians = Math.toRadians(coverRotationDegree.toDouble()).toFloat()
            path.moveTo(ViewUtils.pointRotateGetX(rX0, rY0, radians, x0, y0), ViewUtils.pointRotateGetY(rX0, rY0, radians, x0, y0))
            path.lineTo(ViewUtils.pointRotateGetX(rX0, rY0, radians, x1, y0), ViewUtils.pointRotateGetY(rX0, rY0, radians, x1, y0))
            paint.color = Color.WHITE
            paint.textSize = signStrSize
            if (typeface != null) paint.typeface = typeface
            val hOffset = (bitmapCover.width - paint.measureText(signStr)) / 2
            canvas.drawTextOnPath(it, path, hOffset, -20f, paint)
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

    fun playAnim() {
        if (objectAnimator != null) {
            objectAnimator?.let {
                if (it.isPaused) {
                    if (it.animatedFraction == 1f) it.start() else it.resume()
                } else {
                    it.start()
                }
            }
        } else {
            initParams()
            val trunkXPropertyValuesHolder = PropertyValuesHolder.ofFloat(
                    "trunkX", trunkX, trunkX - bitmapTrunk.width / 2)
            val trunkYPropertyValuesHolder = PropertyValuesHolder.ofFloat(
                    "trunkY", trunkY, 5f, 120f)
            val trunkRotationDegreePropertyValuesHolder = PropertyValuesHolder.ofFloat(
                    "trunkRotationDegree", trunkRotationDegree, 0f)
            val coverYPropertyValuesHolder = PropertyValuesHolder.ofFloat(
                    "coverY", coverY, 80f, 108f)
            val coverRotationDegreePropertyValuesHolder = PropertyValuesHolder.ofFloat(
                    "coverRotationDegree", coverRotationDegree, 0f)
            val animator = ObjectAnimator.ofPropertyValuesHolder(this,
                    trunkXPropertyValuesHolder, trunkYPropertyValuesHolder,
                    trunkRotationDegreePropertyValuesHolder,
                    coverYPropertyValuesHolder, coverRotationDegreePropertyValuesHolder)
            animator.duration = 3000
            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.addUpdateListener { invalidate() }
            animator.start()
            objectAnimator = animator
        }
    }

    fun pauseAnim() {
        objectAnimator?.pause()
        this.invalidate()
    }

    fun stopAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            objectAnimator?.setCurrentFraction(0f)
        }
        objectAnimator?.pause()
    }

    var progress: Float
        get() {
            val animator = objectAnimator
            return if (animator != null) animator.animatedFraction * 100 else 0f
        }
        set(progress) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                objectAnimator?.setCurrentFraction(progress * 0.01f)
            }
        }

    fun release() {
        objectAnimator?.pause()
        objectAnimator?.cancel()
        objectAnimator = null
    }
}