package x.chestnut.code.snippet.anim.carBgView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ViewUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/4 16:46
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarGrassLandView : View {

    //草地
    private var grassCycleScrollBitmapBean: CycleScrollBitmapBean? = null
    private val paint = Paint()
    private var isStart = false
    private val r: Runnable = object : Runnable {
        override fun run() {
            this@CarGrassLandView.invalidate()
            postDelayed(this, 60)
        }
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val grassLandBitmap = (resources.getDrawable(R.drawable.car_bg_grassland) as BitmapDrawable).bitmap
        grassCycleScrollBitmapBean = CycleScrollBitmapBean(0, grassLandBitmap, 3, 720 - grassLandBitmap.height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        grassCycleScrollBitmapBean!!.onDraw(canvas, paint)
    }

    fun startAnim() {
        if (!isStart) {
            isStart = true
            post(r)
        }
    }

    fun stopAnim() {
        if (isStart) {
            isStart = false
            removeCallbacks(r)
        }
    }

    fun release() {
        if (grassCycleScrollBitmapBean != null) {
            ViewUtils.releaseBitmap(grassCycleScrollBitmapBean!!.bitmap)
        }
    }
}