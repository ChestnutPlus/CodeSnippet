package x.chestnut.code.snippet.anim.storyWelcomeView

import android.animation.Animator
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
 * time  : 2018/8/26 11:52
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class StoryWelcomeView : View {

    private val iconWidthPart = 11
    private val iconHeightPart = 1
    private val iconPartCount = (iconWidthPart + 1) * (iconHeightPart + 1)
    private val iconNewsPos = FloatArray(iconPartCount * 2)
    private val iconOrigPos = FloatArray(iconPartCount * 2)
    private var centralAxisX = 0f
    private var bitmapIcon: Bitmap? = null
    private var bitmapIconProjection: Bitmap? = null
    private val mMatrix = Matrix()
    private val textRect: RectF by lazy {
        RectF((width / 2 - 400).toFloat(), 168f, (right / 2 + 400).toFloat(), 1068f)
    }
    private val pathTvGu: Path by lazy {
        val path = Path()
        path.addArc(textRect, (240 - 1).toFloat(), 10f)
        path
    }
    private val pathTvShi: Path by lazy {
        val path = Path()
        path.addArc(textRect, (265 - 1).toFloat(), 10f)
        path
    }
    private val pathTvJi: Path by lazy {
        val path = Path()
        path.addArc(textRect, (290 - 1).toFloat(), 10f)
        path
    }
    private var isShowTvGu = false
    private var isShowTvShi = false
    private var isShowTvJi = false
    private var disappearRadius = 0f
    private var factor = 0f
    private val FACTOR_END = 100f
    private val FACTOR_START = 0f
    private var bgColor = 0

    //icon的y轴变化
    private var iconYOffset = 0f
    private var iconMeshFactor = 0f
    private val ICON_Y_OFFSET_START = -236f
    private val ICON_Y_OFFSET_END = 303f
    private val ICON_MESH_X_FACTOR = 0.08f
    private val ICON_MESH_Y_FACTOR = 0.05f
    private var iconProjectionFactor = 1.0f
    private var iconObjectAnimator: ObjectAnimator? = null
    private val animatorListener: Animator.AnimatorListener? = null
    private val paint = Paint()
    private val paintFlagsDrawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    constructor(context: Context?) : super(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapIcon = BitmapFactory.decodeResource(resources, R.drawable.story_icon).apply {
            centralAxisX = width * 1.0f / 2
        }
        bitmapIconProjection = BitmapFactory.decodeResource(resources, R.drawable.icon_projection)
        paint.style = Paint.Style.FILL
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.strokeWidth = 4f
        paint.textSize = 100f
        getBitmapMeshPoints(bitmapIcon, iconHeightPart, iconWidthPart, iconNewsPos, iconOrigPos)
        initParam()
    }

    private fun initParam() {
        factor = 0f
        iconMeshFactor = 0f
        iconYOffset = ICON_Y_OFFSET_START
        isShowTvGu = false
        isShowTvShi = false
        isShowTvJi = false
        bgColor = Color.rgb(252, 68, 80)
        disappearRadius = 0f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawFilter = paintFlagsDrawFilter
        //Bg
        canvas.drawColor(bgColor)
        //阴影
        mMatrix.reset()
        ViewUtils.applySelfScaleToMatrix(bitmapIconProjection!!, iconProjectionFactor, iconProjectionFactor,
                (width / 2 - bitmapIconProjection!!.width / 2).toFloat(), 524f, mMatrix)
        canvas.drawBitmap(bitmapIconProjection!!, mMatrix, paint)
        //icon
        canvas.save()
        changeIconMeshPoint(bitmapIcon, iconPartCount, iconWidthPart, iconHeightPart, iconNewsPos, iconOrigPos,
                centralAxisX, iconMeshFactor, ICON_MESH_X_FACTOR, ICON_MESH_Y_FACTOR)
        canvas.translate((width / 2 - bitmapIcon!!.width / 2).toFloat(), iconYOffset)
        canvas.drawBitmapMesh(bitmapIcon!!, iconWidthPart, iconHeightPart, iconNewsPos, 0, null, 0, paint)
        canvas.restore()
        //文字
        paint.color = Color.WHITE
        if (isShowTvGu) canvas.drawTextOnPath("故", pathTvGu, 0f, 10f, paint)
        if (isShowTvShi) canvas.drawTextOnPath("事", pathTvShi, 0f, 10f, paint)
        if (isShowTvJi) canvas.drawTextOnPath("机", pathTvJi, 0f, 10f, paint)
        //最后的动画
        if (disappearRadius != 0f) {
            paint.color = Color.rgb(252, 68, 80)
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), disappearRadius, paint)
        }
    }

    fun setTypeface(typeface: Typeface?) {
        paint.typeface = typeface
    }

    fun playAnim() {
        iconObjectAnimator?.cancel()
        initParam()
        val iconObjectAnimator = ObjectAnimator.ofFloat(this, "factor", FACTOR_START, FACTOR_END)
        iconObjectAnimator.duration = 5030
        iconObjectAnimator.interpolator = LinearInterpolator()
        if (animatorListener != null) iconObjectAnimator.addListener(animatorListener)
        iconObjectAnimator.start()
        this@StoryWelcomeView.iconObjectAnimator = iconObjectAnimator
    }

    fun pauseAnim() {
        iconObjectAnimator?.pause()
    }

    fun stopAnim() {
        iconObjectAnimator?.run {
            removeAllListeners()
            pause()
            cancel()
        }
    }

    fun setFactor(factor: Float) {
        this.factor = factor
        changeFactors(factor * 0.01f)
        this@StoryWelcomeView.invalidate()
    }

    fun getFactor(): Float {
        return factor
    }

    fun release() {
        stopAnim()
        ViewUtils.releaseBitmap(bitmapIconProjection)
        ViewUtils.releaseBitmap(bitmapIcon)
    }

    /**
     * 改变Points Mesh
     * @param bitmap bitmap
     * @param posCount 点的个数
     * @param widthPart 横向分割的份数
     * @param heightPart 纵向分割的份数
     * @param newPos 新点集合
     * @param origPos 原始点集合
     * @param centralAxisX X
     * @param factor 变化因子
     * @param xFactor 变化因子
     * @param yFactor 变化因子
     */
    private fun changeIconMeshPoint(bitmap: Bitmap?, posCount: Int, widthPart: Int, heightPart: Int, newPos: FloatArray, origPos: FloatArray,
                                    centralAxisX: Float, factor: Float,
                                    xFactor: Float, yFactor: Float) {
        //遍历交点，修改
        if (bitmap == null) return
        for (i in 0 until posCount) {
            val x = i * 2
            val y = x + 1
            //x方向的拉伸，离x周轴线越近变化越小，离顶部（y=0）越近，变化也越小
            if (newPos[x] - centralAxisX < -8) {
                newPos[x] = origPos[x] - factor * Math.abs((widthPart + 1) / 2 - i % (widthPart + 1)) * (origPos[y] / bitmap.height) * xFactor
            } else if (newPos[x] - centralAxisX > 8) {
                newPos[x] = origPos[x] + factor * Math.abs((widthPart + 1) / 2 - i % (widthPart + 1)) * (origPos[y] / bitmap.height) * xFactor
            } else {
                newPos[x] = origPos[x]
            }
            //y方向的拉伸，离x周轴线越近变化越大，离顶部（y=0）越近，变化也越小
            val lastBodyYChange = factor * (widthPart * heightPart / (heightPart + 1) + 1) * (1 - Math.abs(origPos[x] - centralAxisX) / bitmap.width) * yFactor
            newPos[y] = origPos[y] + lastBodyYChange
        }
    }

    /**
     * 得到网格点集合
     * @param bitmap bitmap
     * @param heightPart 纵向分割的份数
     * @param widthPart 横向分割的份数
     * @param newPos 新点集合
     * @param origPos 原始点集合
     */
    private fun getBitmapMeshPoints(bitmap: Bitmap?, heightPart: Int, widthPart: Int, newPos: FloatArray, origPos: FloatArray) {
        //参考：https://www.jianshu.com/p/51d8dd99d27d
        //初始化网格点
        if (bitmap == null) return
        var index = 0
        val bmWidth = bitmap.width.toFloat()
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
     * 获取Icon YOffset 的完成度
     * @param v 时间完成度
     */
    private fun changeFactors(v: Float) {
        //tv文字
        if (v >= 0.19 && !isShowTvGu) {
            isShowTvGu = true
        } else if (v >= 0.51 && !isShowTvShi) {
            isShowTvShi = true
        } else if (v >= 0.69 && !isShowTvJi) {
            isShowTvJi = true
        }

        //消失动画
        if (v >= 0.75) {
            disappearRadius = ViewUtils.factorMapping(v, 0.75f, 1.0f, 0f, 735f)
        }

        //Bg
        if (v <= 0.13) bgColor = Color.rgb(252, 68, 80) else if (v <= 0.31) bgColor = Color.rgb(88, 201, 241) else if (v <= 0.49) bgColor = Color.rgb(247, 231, 93) else if (v <= 0.62) bgColor = Color.rgb(96, 209, 91) else if (v <= 0.72) bgColor = Color.rgb(250, 107, 28) else if (v <= 0.85) bgColor = Color.rgb(233, 84, 150)

        //v：时间完成度百分比
        var y = 1.0f
        iconMeshFactor = 0.0f
        if (v <= 0.05) {
            iconProjectionFactor = ViewUtils.factorMapping(v, 0f, 0.05f, 1.0f, 0.769f)
            y = 0f
        } else if (v <= 0.13) {
            y = 12.5f * v - 0.625f
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.05f, 0.13f, 0.769f, 0.30f)
        } else if (v <= 0.16) {
            //1.0f
            iconMeshFactor = ViewUtils.factorMapping(v, 0.13f, 0.16f, 0f, 100f)
            iconProjectionFactor = 0.30f
            y = 1.0f
        } else if (v <= 0.23) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.16f, 0.23f, 100f, 0f)
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.16f, 0.23f, 0.30f, 0.75f)
            y = -4.876756f * v + 1.7802811f
        } else if (v <= 0.31) {
            y = 4.2671604f * v - 0.3228197f
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.23f, 0.31f, 0.75f, 0.30f)
        } else if (v <= 0.34) {
            //0.75
            iconMeshFactor = ViewUtils.factorMapping(v, 0.31f, 0.34f, 0f, 75f)
            iconProjectionFactor = 0.30f
            y = 1.0f
        } else if (v <= 0.42) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.34f, 0.42f, 75f, 0f)
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.34f, 0.42f, 0.30f, 0.50f)
            y = -2.9452696f * v + 2.0013916f
        } else if (v <= 0.49) {
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.42f, 0.49f, 0.50f, 0.30f)
            y = 3.3660219f * v - 0.64935064f
        } else if (v <= 0.51) {
            //0.50
            iconMeshFactor = ViewUtils.factorMapping(v, 0.49f, 0.51f, 0f, 50f)
            iconProjectionFactor = 0.30f
            y = 1.0f
        } else if (v <= 0.57) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.51f, 0.57f, 50f, 0f)
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.51f, 0.57f, 0.30f, 0.40f)
            y = -2.102659f * v + 2.0723562f
        } else if (v <= 0.62) {
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.57f, 0.62f, 0.40f, 0.30f)
            y = 2.5231903f * v - 0.564378f
        } else if (v <= 0.64) {
            //0.38
            iconMeshFactor = ViewUtils.factorMapping(v, 0.62f, 0.64f, 0f, 38f)
            iconProjectionFactor = 0.30f
            y = 1.0f
        } else if (v <= 0.69) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.64f, 0.69f, 38f, 0f)
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.64f, 0.69f, 0.30f, 0.35f)
            y = -1.4471241f * v + 1.9261594f
        } else if (v <= 0.72) {
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.69f, 0.72f, 0.35f, 0.30f)
            y = 2.4118764f * v - 0.7365509f
        } else if (v <= 0.73) {
            //0.25
            iconMeshFactor = ViewUtils.factorMapping(v, 0.72f, 0.73f, 0f, 25f)
            iconProjectionFactor = 0.30f
            y = 1.0f
        } else if (v <= 0.76) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.73f, 0.76f, 25f, 0f)
            iconProjectionFactor = ViewUtils.factorMapping(v, 0.73f, 0.76f, 0.30f, 0.35f)
            y = -1.4842277f * v + 2.083486f
        } else if (v <= 0.79) {
            y = 1.4842306f * v - 0.1725421f
        } else if (v <= 0.80) {
            //0.13
            iconMeshFactor = ViewUtils.factorMapping(v, 0.79f, 0.80f, 0f, 13f)
            y = 1.0f
        } else if (v <= 0.82) {
            iconMeshFactor = ViewUtils.factorMapping(v, 0.80f, 0.82f, 13f, 0f)
            y = -0.7421121f * v + 1.5936897f
        } else if (v <= 0.85) {
            y = 0.49474287f * v + 0.5794686f
        }
        iconYOffset = y * (ICON_Y_OFFSET_END - ICON_Y_OFFSET_START) + ICON_Y_OFFSET_START
    }
}
