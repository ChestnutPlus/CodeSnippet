package x.chestnut.code.snippet.anim.carTrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import androidx.annotation.Nullable
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carView.CarBaseView
import x.chestnut.code.snippet.utils.ViewUtils
import x.chestnut.code.snippet.utils.ViewUtils.releaseBitmap

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/1 14:38
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarLocomotiveView : CarBaseView {

    private var bitmapBigWheel: Bitmap? = null
    private var bitmapSmallWheelBg: Bitmap? = null
    private var bitmapBumper: Bitmap? = null
    private var bitmapSmallWheel: Bitmap? = null
    private var bitmapLightBulb: Bitmap? = null
    private var bitmapLight: Bitmap? = null
    private var bitmapProtectCase: Bitmap? = null
    private var bitmapChain: Bitmap? = null
    private var bitmapBell: Bitmap? = null
    private var bitmapFunnelBody: Bitmap? = null
    private var bitmapFunnelTop: Bitmap? = null
    private var bitmapFunnelCover: Bitmap? = null
    private var bitmapCloud: Bitmap? = null
    private var bitmapCloudSmile: Bitmap? = null
    private val mMatrix = Matrix()

    //将图像分成多少格
    private val carProtectCaseWidthPart = 2
    private val carProtectCaseHeightPart = 1
    private val carBellWidthPart = 2
    private val carBellHeightPart = 1

    //交点坐标的个数
    private val carProtectCaseCount = (carProtectCaseWidthPart + 1) * (carProtectCaseHeightPart + 1)
    private val carBellCount = (carBellWidthPart + 1) * (carBellHeightPart + 1)

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val carProtectCaseNewsPos = FloatArray(carProtectCaseCount * 2)
    private val carProtectCaseOrigPos = FloatArray(carProtectCaseCount * 2)
    private val carBellNewsPos = FloatArray(carBellCount * 2)
    private val carBellOrigPos = FloatArray(carBellCount * 2)
    private var chainRotationFlag = 1
    override fun release() {
        super.release()
        releaseBitmap(bitmapBigWheel)
        releaseBitmap(bitmapSmallWheelBg)
        releaseBitmap(bitmapBumper)
        releaseBitmap(bitmapSmallWheel)
        releaseBitmap(bitmapLightBulb)
        releaseBitmap(bitmapLight)
        releaseBitmap(bitmapProtectCase)
        releaseBitmap(bitmapChain)
        releaseBitmap(bitmapBell)
        releaseBitmap(bitmapFunnelBody)
        releaseBitmap(bitmapFunnelTop)
        releaseBitmap(bitmapFunnelCover)
        releaseBitmap(bitmapCloud)
        releaseBitmap(bitmapCloudSmile)
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        bitmapBigWheel = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_big_wheel)
        bitmapSmallWheelBg = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_small_wheel_bg)
        bitmapBumper = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_bumper)
        bitmapSmallWheel = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_small_wheel)
        bitmapLightBulb = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_light_bulb)
        bitmapLight = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_light_x)
        bitmapProtectCase = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_protect_case)
        bitmapChain = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_chain)
        bitmapBell = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_bell)
        bitmapFunnelBody = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_funnel_body)
        bitmapFunnelTop = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_funnel_top)
        bitmapFunnelCover = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_funnel_cover)
        bitmapCloud = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_cloud_big)
        bitmapCloudSmile = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_cloud_big_smile)
        super.getBitmapMeshPoints(bitmapProtectCase, carProtectCaseHeightPart, carProtectCaseWidthPart, carProtectCaseNewsPos, carProtectCaseOrigPos)
        super.getBitmapMeshPoints(bitmapBell, carBellHeightPart, carBellWidthPart, carBellNewsPos, carBellOrigPos)
    }

    override val carWheel: Bitmap?
        protected get() = null
    override val carBody: Bitmap
        protected get() = BitmapFactory.decodeResource(resources, R.drawable.car_locomotive_main)
    override val bodyYOffset: Int
        get() = -45

    override fun onDraw(canvas: Canvas) {
        val radius = 15f
        val yOffset: Float = getChangeFactorByNewFactor(radius)

        //小轮子bg
        canvas.drawBitmap(bitmapSmallWheelBg!!, (width / 2 - 220).toFloat(), (height / 2 + 67).toFloat(), paint)

        //车灯光
        canvas.save()
        canvas.translate((width / 2 - 180).toFloat(), (height / 2 + 60).toFloat())
        canvas.rotate(180f)
        canvas.skew(0f, getChangeFactorByNewFactor(0.17f))
        canvas.drawBitmap(bitmapLight!!, 0f, 0f, paint)
        canvas.restore()

        //车灯
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapLightBulb!!, getChangeFactorByNewFactor(5f) - 3f,
                width / 2 - 240f,
                height / 2 - bitmapLightBulb!!.height / 2 - 10f, mMatrix)
        canvas.drawBitmap(bitmapLightBulb!!, mMatrix, paint)

        //铃
        canvas.save()
        canvas.translate((width / 2 - bitmapBell!!.width - 2).toFloat(), height / 2 - bitmapBell!!.height - 77 + yOffset * 0.2f)
        carBellNewsPos[1 * 2 + 1] = carBellOrigPos[1 * 2 + 1] + (1 - carBellOrigPos[1 * 2 + 1] / (bitmapBell!!.height * 2)) * yOffset * 0.35f
        carBellNewsPos[4 * 2 + 1] = carBellOrigPos[4 * 2 + 1] + (1 - carBellOrigPos[4 * 2 + 1] / (bitmapBell!!.height * 2)) * yOffset * 0.25f
        carBellNewsPos[3 * 2] = carBellOrigPos[3 * 2] - Math.abs((carBellOrigPos[3 * 2] - carBellOrigPos[4 * 2]) / bitmapBell!!.width) * yOffset * 0.5f
        carBellNewsPos[5 * 2] = carBellOrigPos[5 * 2] + Math.abs((carBellOrigPos[5 * 2] - carBellOrigPos[4 * 2]) / bitmapBell!!.width) * yOffset * 0.5f
        canvas.drawBitmapMesh(bitmapBell!!, carBellWidthPart, carBellHeightPart, carBellNewsPos, 0, null, 0, paint)
        canvas.restore()

        //烟囱
        canvas.drawBitmap(bitmapFunnelBody!!, (width / 2 - 130).toFloat(), height / 2 - 128 - yOffset, paint)
        canvas.drawBitmap(bitmapFunnelTop!!, (width / 2 - 140).toFloat(), height / 2 - 158 - yOffset * 1.6f, paint)
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapFunnelCover!!, -positiveAndNegativeFactor * 1.5f,
                width / 2 - 138f,
                height / 2 - 168 - yOffset * 3.0f, mMatrix)
        canvas.drawBitmap(bitmapFunnelCover!!, mMatrix, paint)

        //车厢主题
        super.onDraw(canvas)

        //保险杠
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapBumper!!, getChangeFactorByNewFactor(5f),
                width / 2 - 285 - getChangeFactorByNewFactor(5f),
                height / 2 + 55f, mMatrix)
        canvas.drawBitmap(bitmapBumper!!, mMatrix, paint)

        //大轮子
        canvas.drawBitmap(bitmapBigWheel!!, (width / 2 - 20).toFloat(), (height / 2 + 64).toFloat(), paint)

        //小轮子
        canvas.drawBitmap(bitmapSmallWheel!!, (width / 2 - 210).toFloat(), (height / 2 + 95).toFloat(), paint)

        //链条
        canvas.save()
        val chainYOffset: Float = getChangeFactorByNewFactor(radius * 2)
        val radian = Math.acos(((chainYOffset - radius) / radius).toDouble())
        val chainXOffset = (Math.sin(radian) * radius).toFloat() * chainRotationFlag
        canvas.translate((width / 2 + 24).toFloat(), (height / 2 + 100).toFloat())
        canvas.drawBitmap(bitmapChain!!,
                -bitmapChain!!.width + 12 + chainXOffset,
                -bitmapChain!!.height + chainYOffset, paint)
        if (chainYOffset <= 0 || chainYOffset >= 30) chainRotationFlag *= -1
        canvas.restore()

        //保护壳，改变特定点：y轴移动，图片中心向下凹，离y=0越近，程度越深
        canvas.save()
        canvas.translate((width / 2 - 200).toFloat(), height / 2 + 32 + yOffset)
        carProtectCaseNewsPos[1 * 2 + 1] = carProtectCaseOrigPos[1 * 2 + 1] + (1 - carProtectCaseOrigPos[1 * 2 + 1] / (bitmapProtectCase!!.height * 2)) * yOffset * 0.3f
        carProtectCaseNewsPos[4 * 2 + 1] = carProtectCaseOrigPos[4 * 2 + 1] + (1 - carProtectCaseOrigPos[4 * 2 + 1] / (bitmapProtectCase!!.height * 2)) * yOffset * 0.3f
        canvas.drawBitmapMesh(bitmapProtectCase!!, carProtectCaseWidthPart, carProtectCaseHeightPart, carProtectCaseNewsPos, 0, null, 0, paint)
        canvas.restore()

        //正弦函数轨迹
        canvas.save()
        canvas.translate((width / 2 - 120).toFloat(), (height / 2 - 250).toFloat())
        for (i in cloudPointX.indices) {
            val y = getCloudY(cloudPointX[i])
            if (cloudPointX[i] <= CloudWidthFactor * 1.0f / 4 * 3) {
                paint.alpha = getCloudAlpha(cloudPointX[i], y)
                val bitmap = if (cloudIsSmile[i]) bitmapCloudSmile else bitmapCloud
                //缩放
                mMatrix.reset()
                ViewUtils.applySelfScaleToMatrix(bitmap!!,
                        getCloudScale(cloudPointX[i], y), getCloudScale(cloudPointX[i], y),
                        cloudPointX[i] - bitmap!!.width / 2, y - bitmap.height / 2, mMatrix)
                canvas.drawBitmap(bitmap, mMatrix, paint)
                cloudPointX[i] = cloudPointX[i] + 9
            } else {
                cloudIsSmile[i] = !cloudIsSmile[i]
                cloudPointX[i] = 0f
            }
        }
        paint.alpha = 255
        canvas.restore()
    }

    /**
     * 小云的运动轨迹
     */
    private val A = 55 //振幅
    private val CloudWidthFactor = 400
    private val xieLv = -1.0f * A / CloudWidthFactor * 4
    private val aTemp = -1.0f * xieLv * CloudWidthFactor / 2
    private val cloudPointX = floatArrayOf(
            0.0f, (
            CloudWidthFactor / 4).toFloat(), (
            CloudWidthFactor / 2).toFloat())
    private val cloudIsSmile = booleanArrayOf(
            false, true, false
    )

    private fun getCloudY(x: Float): Float {
        return if (x < 0 || x > CloudWidthFactor * 3 / 4) (-1).toFloat() else if (x <= CloudWidthFactor / 2) (-A * Math.sin(x / CloudWidthFactor.toFloat() * 2f * Math.PI)).toFloat() else {
            xieLv * x + aTemp
        }
    }

    private fun getCloudScale(x: Float, y: Float): Float {
        val a = 0.8f
        val b = 0.45f
        val c = 0.3f
        return if (x < 0 || x > CloudWidthFactor * 3 / 4) (-1).toFloat() else if (x <= CloudWidthFactor / 2) // 0.5f - 1.0f
            Math.abs(y) * (a - b) / A + b else  // 0.5f - 0.2f
            b - Math.abs(y) * (b - c) / A
    }

    private fun getCloudAlpha(x: Float, y: Float): Int {
        return if (x < CloudWidthFactor / 2 || x > CloudWidthFactor * 3 / 4) 255 else (255 - 255 * Math.abs(y) / A).toInt()
    }
}