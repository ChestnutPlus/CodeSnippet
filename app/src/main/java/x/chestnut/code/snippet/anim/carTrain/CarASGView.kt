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
class CarASGView : CarBaseView {

    private var bitmapGirl: Bitmap? = null
    private var bitmapEgg: Bitmap? = null
    private var bitmapBird: Bitmap? = null

    //将图像分成多少格
    private val carMarketCoverWidthPart = 2
    private val carMarketCoverHeightPart = 2

    //交点坐标的个数
    private val carMarketCoverCount = (carMarketCoverWidthPart + 1) * (carMarketCoverHeightPart + 1)

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val carMarketCoverNewsPos = FloatArray(carMarketCoverCount * 2)
    private val carMarketCoverOrigPos = FloatArray(carMarketCoverCount * 2)
    private val mMatrix = Matrix()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapGirl = BitmapFactory.decodeResource(resources, R.drawable.car_asg_girl)
        bitmapEgg = BitmapFactory.decodeResource(resources, R.drawable.car_asg_egg)
        bitmapBird = BitmapFactory.decodeResource(resources, R.drawable.car_asg_bird)
        super.getBitmapMeshPoints(bitmapGirl, carMarketCoverHeightPart, carMarketCoverWidthPart, carMarketCoverNewsPos, carMarketCoverOrigPos)
    }

    override fun release() {
        super.release()
        releaseBitmap(bitmapGirl)
        releaseBitmap(bitmapEgg)
    }

    override val carWheel: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_wheel_orange)
    override val carBody: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_asg_main)
    override val wheelXOffset: Int
        get() = 0

    override fun onDraw(canvas: Canvas) {
        //小鸟
        canvas.save()
        mMatrix.reset()
        var rotation: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, (FACTOR_MAX * 3).toFloat(), 0f, -15f, 15f)
        ViewUtils.applySelfRotationToMatrix(bitmapBird!!, rotation, 0f, 0f, mMatrix)
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 - bitmapBird!!.height + 75).toFloat())
        canvas.drawBitmap(bitmapBird!!, mMatrix, paint)
        canvas.restore()

        //女孩
        canvas.save()
        canvas.translate((width / 2 - bitmapCarBodyWidth / 2 + 20).toFloat(), (height / 2 - bitmapCarBodyHeight / 2 - bitmapGirl!!.height / 2 - 10).toFloat())
        super.changeCarBodyPoint(bitmapGirl, carMarketCoverCount, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, carMarketCoverOrigPos, 0.5f, 0.2f)
        canvas.drawBitmapMesh(bitmapGirl!!, carMarketCoverWidthPart, carMarketCoverHeightPart, carMarketCoverNewsPos, 0, null, 0, paint)
        canvas.restore()

        //车厢主题
        super.onDraw(canvas)

        //蛋蛋
        canvas.save()
        mMatrix.reset()
        rotation = ViewUtils.factorMapping(delayChangeFactor.toFloat(), 0f, (FACTOR_MAX * 3).toFloat(), 8f, -8f)
        ViewUtils.applyRotationToMatrix(rotation, 93f, 154f, mMatrix)
        val yOffset: Float = ViewUtils.factorMapping(changeFactor.toFloat(), 0f, (FACTOR_MAX * 2).toFloat(), -5f, 5f)
        canvas.translate((width / 2).toFloat(), height / 2 - bitmapCarBodyHeight / 2 - bitmapEgg!!.height / 2 - 40 + yOffset)
        canvas.drawBitmap(bitmapEgg!!, mMatrix, paint)
        canvas.restore()
    }
}