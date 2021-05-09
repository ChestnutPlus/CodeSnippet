package x.chestnut.code.snippet.anim.carTrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
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
class CarChildrenView : CarBaseView {

    private var bitmapCover: Bitmap? = null
    private var bitmapCat: Bitmap? = null
    private var bitmapBear: Bitmap? = null

    //将图像分成多少格
    private val carMarketCoverWidthPart = 2
    private val carMarketCoverHeightPart = 2

    //交点坐标的个数
    private val carMarketCoverCount = (carMarketCoverWidthPart + 1) * (carMarketCoverHeightPart + 1)

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val carMarketCoverNewsPos = FloatArray(carMarketCoverCount * 2)
    private val carMarketCoverOrigPos = FloatArray(carMarketCoverCount * 2)

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapCover = BitmapFactory.decodeResource(resources, R.drawable.car_children_top)
        bitmapCat = BitmapFactory.decodeResource(resources, R.drawable.car_children_cat)
        bitmapBear = BitmapFactory.decodeResource(resources, R.drawable.car_children_bear)
        super.getBitmapMeshPoints(bitmapCover, carMarketCoverHeightPart, carMarketCoverWidthPart, carMarketCoverNewsPos, carMarketCoverOrigPos)
    }

    override fun release() {
        super.release()
        releaseBitmap(bitmapCover)
        releaseBitmap(bitmapCat)
        releaseBitmap(bitmapBear)
    }

    override val carWheel: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_wheel_orange)
    override val carBody: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_children_main)
    override val wheelXOffset: Int
        get() = -25

    override fun onDraw(canvas: Canvas) {

        //车顶
        canvas.save()
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 - 8).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 - bitmapCover!!.height + 65).toFloat())
        super.changeCarBodyPoint(bitmapCover, carMarketCoverCount, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, carMarketCoverOrigPos, 0.5f, 0.2f)
        canvas.drawBitmapMesh(bitmapCover!!, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, 0, null, 0, paint)

        //猫，熊
        val yOffset: Float = ViewUtils.factorMapping(delayChangeFactor.toFloat(), 0f, FACTOR_MAX * 4f, -2f, 2f)
        val yOffset1: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, FACTOR_MAX * 1.5f, -7f, 7f)
        canvas.drawBitmap(bitmapBear!!, 45f, 5 + yOffset, paint)
        canvas.drawBitmap(bitmapCat!!, 175f, 20 + yOffset1, paint)
        canvas.restore()

        //车厢主题
        super.onDraw(canvas)
    }
}