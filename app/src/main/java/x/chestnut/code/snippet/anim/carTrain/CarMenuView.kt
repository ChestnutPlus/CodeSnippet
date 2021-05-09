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
class CarMenuView : CarBaseView {

    private var bitmapMenuBook: Bitmap? = null
    private var bitmapMenuBottom: Bitmap? = null
    private var bitmapMenuCover: Bitmap? = null
    private var bitmapMenuDinosaur: Bitmap? = null
    private var bitmapMenuFootball: Bitmap? = null
    private var bitmapMenuFork: Bitmap? = null
    private val mMatrix = Matrix()

    override fun release() {
        super.release()
        releaseBitmap(bitmapMenuBook)
        releaseBitmap(bitmapMenuBottom)
        releaseBitmap(bitmapMenuCover)
        releaseBitmap(bitmapMenuDinosaur)
        releaseBitmap(bitmapMenuFootball)
        releaseBitmap(bitmapMenuFork)
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmapMenuBook = BitmapFactory.decodeResource(resources, R.drawable.car_menu_book)
        bitmapMenuBottom = BitmapFactory.decodeResource(resources, R.drawable.car_menu_bottom)
        bitmapMenuCover = BitmapFactory.decodeResource(resources, R.drawable.car_menu_cover)
        bitmapMenuDinosaur = BitmapFactory.decodeResource(resources, R.drawable.car_menu_dinosaur)
        bitmapMenuFootball = BitmapFactory.decodeResource(resources, R.drawable.car_menu_football)
        bitmapMenuFork = BitmapFactory.decodeResource(resources, R.drawable.car_menu_fork)
    }

    override val carWheel: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_wheel_purple)
    override val carBody: Bitmap
        get() = BitmapFactory.decodeResource(resources, R.drawable.car_menu_main)

    override fun onDraw(canvas: Canvas) {
        val carMainTopCenterX = (width / 2).toFloat()
        val carMainTopCenterY = (height / 2 - bitmapCarBodyHeight / 2).toFloat()
        //底
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuBottom!!, getChangeFactorByNewFactor(8f),
                carMainTopCenterX - bitmapMenuBottom!!.width / 2,
                carMainTopCenterY - bitmapMenuBottom!!.height + 45 + getChangeFactorByNewFactor(8f), mMatrix)
        canvas.drawBitmap(bitmapMenuBottom!!, mMatrix, paint)
        //车厢主题
        super.onDraw(canvas)
        //书
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuBook!!, getChangeFactorByNewFactor(25f) + 7,
                carMainTopCenterX - bitmapMenuBook!!.width / 2 - 10 + getChangeFactorByNewFactor(15f),
                carMainTopCenterY - bitmapMenuBook!!.height - 20 + getChangeFactorByNewFactor(25f), mMatrix)
        canvas.drawBitmap(bitmapMenuBook!!, mMatrix, paint)
        //足球
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuFootball!!, getChangeFactorByNewFactor(30f) + 7,
                carMainTopCenterX - bitmapMenuFootball!!.width / 2 - 40 + getChangeFactorByNewFactor(10f),
                carMainTopCenterY - bitmapMenuFootball!!.height + 15 + getChangeFactorByNewFactor(15f), mMatrix)
        canvas.drawBitmap(bitmapMenuFootball!!, mMatrix, paint)
        //叉子
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuFork!!, positiveAndNegativeFactor * 2.0f,
                carMainTopCenterX - bitmapMenuFork!!.width / 2 - 80,
                carMainTopCenterY - bitmapMenuFork!!.height + 22, mMatrix)
        canvas.drawBitmap(bitmapMenuFork!!, mMatrix, paint)
        //恐龙
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuDinosaur!!, getDelayChangeFactorByNewFactor(10f) - 20,
                carMainTopCenterX - bitmapMenuDinosaur!!.width / 2 + 32,
                carMainTopCenterY - bitmapMenuDinosaur!!.height + 20, mMatrix)
        canvas.drawBitmap(bitmapMenuDinosaur!!, mMatrix, paint)
        //盖
        mMatrix.reset()
        ViewUtils.applySelfRotationToMatrix(bitmapMenuCover!!, getChangeFactorByNewFactor(8f),
                carMainTopCenterX - bitmapMenuCover!!.width / 2,
                carMainTopCenterY - bitmapMenuCover!!.height + 38 + getChangeFactorByNewFactor(8f), mMatrix)
        canvas.drawBitmap(bitmapMenuCover!!, mMatrix, paint)
    }
}