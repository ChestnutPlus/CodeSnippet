package x.chestnut.code.snippet.anim.carBgView

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ViewUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/12 17:16
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarBgScrollView : View {

    private var paint: Paint? = null
    private var bgBlueLinearGradient: LinearGradient? = null
    private var grassLandLinearGradient: LinearGradient? = null
    private var grayCloud1ScrollBitmapBean: ScrollBitmapBean? = null
    private var grayCloud2ScrollBitmapBean: ScrollBitmapBean? = null
    private var bigHillScrollBitmapBean: ScrollBitmapBean? = null
    private var middleHillScrollBitmapBean: ScrollBitmapBean? = null
    private var smallHillScrollBitmapBean: ScrollBitmapBean? = null
    private var grassScrollBitmapBean1: ScrollBitmapBean? = null
    private var grassScrollBitmapBean2: ScrollBitmapBean? = null
    private var grassScrollBitmapBean3: ScrollBitmapBean? = null
    private val rect = Rect(0, 0, 1280, 720)
    private var isStart = false
    private val speedFactor = 80
    private val r: Runnable = object : Runnable {
        override fun run() {
            this@CarBgScrollView.invalidate(rect)
            postDelayed(this, speedFactor.toLong())
        }
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint = Paint()
        bgBlueLinearGradient = LinearGradient(0f, 0f, 0f, 720f,
                intArrayOf(-0xc1632d, -0xb92e01, -0xb1212a),
                floatArrayOf(0f, 0.57f, 1f), Shader.TileMode.MIRROR)
        grassLandLinearGradient = LinearGradient(0f, 0f, 630f, 720f,
                -0x321ece, -0x5631e8, Shader.TileMode.MIRROR)
        val addFactor1 = (speedFactor / 50).toFloat()
        grayCloud1ScrollBitmapBean = ScrollBitmapBean(-100, 45, addFactor1,
                (resources.getDrawable(R.drawable.gray_cloud_1) as BitmapDrawable).bitmap)
        grayCloud2ScrollBitmapBean = ScrollBitmapBean(530, 0, addFactor1,
                (resources.getDrawable(R.drawable.gray_cloud_2) as BitmapDrawable).bitmap)
        val addFactor = addFactor1 * 3
        val grass = (resources.getDrawable(R.drawable.grass) as BitmapDrawable).bitmap
        grassScrollBitmapBean1 = ScrollBitmapBean(34, 597, addFactor, grass)
        grassScrollBitmapBean2 = ScrollBitmapBean(496, 597, addFactor, grass)
        grassScrollBitmapBean3 = ScrollBitmapBean(956, 597, addFactor, grass)
        val bigHill = (resources.getDrawable(R.drawable.big_hill) as BitmapDrawable).bitmap
        val smallHill = (resources.getDrawable(R.drawable.small_hill) as BitmapDrawable).bitmap
        bigHillScrollBitmapBean = ScrollBitmapBean(306, 112, addFactor, bigHill)
        middleHillScrollBitmapBean = ScrollBitmapBean(60, 266, addFactor, bigHill)
        smallHillScrollBitmapBean = ScrollBitmapBean(956, 342, addFactor, smallHill)
    }

    override fun onDraw(canvas: Canvas) {
        //浅蓝背景
        paint!!.shader = bgBlueLinearGradient
        canvas.drawRect(0f, 0f, 1280f, 720f, paint!!)
        paint!!.shader = null

        //山
        bigHillScrollBitmapBean!!.onDraw(canvas, paint)
        middleHillScrollBitmapBean!!.onDraw(canvas, paint)
        smallHillScrollBitmapBean!!.onDraw(canvas, paint)

        //云
        grayCloud1ScrollBitmapBean!!.onDraw(canvas, paint)
        grayCloud2ScrollBitmapBean!!.onDraw(canvas, paint)

        //草丛
        grassScrollBitmapBean1!!.onDraw(canvas, paint)
        grassScrollBitmapBean2!!.onDraw(canvas, paint)
        grassScrollBitmapBean3!!.onDraw(canvas, paint)

        //草地背景
        paint!!.shader = grassLandLinearGradient
        canvas.drawRect(0f, 628f, 1280f, 720f, paint!!)
        paint!!.shader = null
    }

    fun playAnim() {
        if (!isStart) {
            isStart = true
            post(r)
        }
    }

    fun pauseAnim() {}
    fun stopAnim() {
        if (isStart) {
            isStart = false
            removeCallbacks(r)
        }
    }

    var factor: Float
        get() = 0F
        set(factor) {}

    /**
     * 释放方法
     */
    fun release() {
        stopAnim()
        if (grayCloud1ScrollBitmapBean != null) ViewUtils.releaseBitmap(grayCloud1ScrollBitmapBean!!.bitmap)
        if (grayCloud2ScrollBitmapBean != null) ViewUtils.releaseBitmap(grayCloud2ScrollBitmapBean!!.bitmap)
        if (bigHillScrollBitmapBean != null) ViewUtils.releaseBitmap(bigHillScrollBitmapBean!!.bitmap)
        if (middleHillScrollBitmapBean != null) ViewUtils.releaseBitmap(middleHillScrollBitmapBean!!.bitmap)
        if (smallHillScrollBitmapBean != null) ViewUtils.releaseBitmap(smallHillScrollBitmapBean!!.bitmap)
        if (grassScrollBitmapBean1 != null) ViewUtils.releaseBitmap(grassScrollBitmapBean1!!.bitmap)
        if (grassScrollBitmapBean2 != null) ViewUtils.releaseBitmap(grassScrollBitmapBean2!!.bitmap)
        if (grassScrollBitmapBean3 != null) ViewUtils.releaseBitmap(grassScrollBitmapBean3!!.bitmap)
    }
}