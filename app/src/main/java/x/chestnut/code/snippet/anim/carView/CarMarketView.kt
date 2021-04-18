package x.chestnut.code.snippet.anim.carView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import x.chestnut.code.snippet.R

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
open class CarMarketView : CarBaseView {

    private var bitmapCover: Bitmap? = null
    private var bitmapConnection: Bitmap? = null

    //将图像分成多少格
    private val carMarketConnectWidthPart = 2
    private val carMarketConnectHeightPart = 2
    private val carMarketCoverWidthPart = 2
    private val carMarketCoverHeightPart = 2

    //交点坐标的个数
    private val carMarketConnectCount = (carMarketConnectWidthPart + 1) * (carMarketConnectHeightPart + 1)
    private val carMarketCoverCount = (carMarketCoverWidthPart + 1) * (carMarketCoverHeightPart + 1)

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val carMarketConnectNewsPos = FloatArray(carMarketConnectCount * 2)
    private val carMarketConnectOrigPos = FloatArray(carMarketConnectCount * 2)
    private val carMarketCoverNewsPos = FloatArray(carMarketCoverCount * 2)
    private val carMarketCoverOrigPos = FloatArray(carMarketCoverCount * 2)

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapCover = BitmapFactory.decodeResource(resources, R.drawable.car_market_cover)
        bitmapConnection = BitmapFactory.decodeResource(resources, R.drawable.car_market_connection)
        super.getBitmapMeshPoints(bitmapConnection, carMarketConnectHeightPart, carMarketConnectWidthPart, carMarketConnectNewsPos, carMarketConnectOrigPos)
        super.getBitmapMeshPoints(bitmapCover, carMarketCoverHeightPart, carMarketCoverWidthPart, carMarketCoverNewsPos, carMarketCoverOrigPos)
    }

    override val carWheel: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_wheel_purple)
    override val carBody: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_market_main)

    override fun onDraw(canvas: Canvas) {

        //车篷连接处
        canvas.save()
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 + 57).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 - bitmapConnection!!.height + 27).toFloat())
        super.changeCarBodyPoint(bitmapConnection, carMarketConnectCount, carMarketConnectWidthPart, carMarketConnectHeightPart, carMarketConnectNewsPos, carMarketConnectOrigPos, 0.3f, 0.08f)
        canvas.drawBitmapMesh(bitmapConnection!!, carMarketConnectWidthPart, carMarketConnectHeightPart, carMarketConnectNewsPos, 0, null, 0, paint)
        canvas.restore()

        //车厢主题
        super.onDraw(canvas)

        //车篷
        canvas.save()
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 + 30).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 - bitmapConnection!!.height - 21).toFloat())
        super.changeCarBodyPoint(bitmapCover, carMarketCoverCount, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, carMarketCoverOrigPos, 0.5f, 0.2f)
        canvas.drawBitmapMesh(bitmapCover!!, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, 0, null, 0, paint)
        canvas.restore()
    }

    fun playAnim() {
        super.startAnim()
    }

    fun pauseAnim() {
        super.stopAnim()
    }
}