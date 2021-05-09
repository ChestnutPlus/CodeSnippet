package x.chestnut.code.snippet.anim.carTrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carView.CarBaseView
import x.chestnut.code.snippet.utils.ViewUtils
import x.chestnut.code.snippet.utils.ViewUtils.releaseBitmap

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/30 10:05
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarToolsView : CarBaseView {

    private var bitmapCover: Bitmap? = null
    private var bitmapLight: Bitmap? = null
    private val mMatrix = Matrix()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapCover = BitmapFactory.decodeResource(resources, R.drawable.car_tools_top)
        bitmapLight = BitmapFactory.decodeResource(resources, R.drawable.car_tools_light)
    }

    override fun release() {
        super.release()
        releaseBitmap(bitmapCover)
    }

    override val carWheel: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_wheel_orange)
    override val carBody: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_tools_main)

    override fun onDraw(canvas: Canvas) {

        //车顶
        canvas.save()
        val yOffsetTop: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, FACTOR_MAX * 2f, -10f, 10f)
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 + 150).toFloat(), height / 2 - bitmapCarBodyHeight / 2 - bitmapCover!!.height + 60 + yOffsetTop)
        canvas.drawBitmap(bitmapCover!!, 0f, 0f, paint)
        canvas.restore()

        //车厢主题
        super.onDraw(canvas)

        //手电筒
        canvas.save()
        mMatrix.reset()
        val rotation: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, FACTOR_MAX * 3f, 0f, -8f, 8f)
        val yOffsetLight: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, FACTOR_MAX * 2f, -2f, 2f)
        ViewUtils.applySelfRotationToMatrix(bitmapLight!!, rotation, 0f, 0f, mMatrix)
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 + 20).toFloat(), height / 2 - bitmapCarBodyHeight / 2 - bitmapCover!!.height + 85 + yOffsetLight)
        canvas.drawBitmap(bitmapLight!!, mMatrix, paint)
        canvas.restore()
    }
}