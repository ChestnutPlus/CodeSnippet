package x.chestnut.code.snippet.ui.drawable.roundedBitmapDrawable

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/6 12:47
 * desc  :
 * thanks To:   看完这篇文章，我保证你也会用 RoundedBitmapDrawable 创建圆角头像
 * https://juejin.im/post/5ca3eaf8518825440a4b9ee1#heading-10
 * dependent on:
 * update log:
</pre> *
 */
class RoundDrawableFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_drawable_round
    }

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)

        /*圆形Drawable*/
        val img1 = rootView.findViewById<ImageView>(R.id.img_1)
        //第一步：创建 RoundedBitmapDrawable 对象
        val circleDrawable = RoundedBitmapDrawableFactory.create(resources,
                BitmapFactory.decodeResource(resources, R.drawable.girl_4))
        //第二步：设置 RoundedBitmapDrawable 为圆形
        circleDrawable.isCircular = true
        //第三步：将 RoundedBitmapDrawable 设置到 ImageView 上
        img1.setImageDrawable(circleDrawable)

        /*圆角Drawable*/
        val img2 = rootView.findViewById<ImageView>(R.id.img_2)
        val roundRectangleDrawable = RoundedBitmapDrawableFactory.create(
                resources,
                BitmapFactory.decodeResource(resources, R.drawable.girl_6))
        roundRectangleDrawable.cornerRadius = 50f
        img2.setImageDrawable(roundRectangleDrawable)

        /*1. RoundedBitmapDrawable 引用的图片资源，须为正方形，否则会被强制压缩。
          2. ImageView 的 scaleType 须为 fitCenter，否则出现“不良反应”*/
        val img3 = rootView.findViewById<ImageView>(R.id.img_3)
        var circleBitmap = BitmapFactory.decodeResource(resources, R.drawable.long_0)
        circleBitmap = transferToSquareBitmap(circleBitmap)
        val bitmapDrawable = RoundedBitmapDrawableFactory.create(
                resources, circleBitmap)
        bitmapDrawable.isCircular = true
        img3.setImageDrawable(bitmapDrawable)
    }

    /**
     * 根据传入的 Bitmap 的短边将 Bitmap 转换成正方形
     * @param bitmap bitmap
     * @return bitmap
     */
    private fun transferToSquareBitmap(bitmap: Bitmap?): Bitmap? {
        if (bitmap == null) {
            return null
        }
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val squareSideLength = Math.min(bitmapWidth, bitmapHeight)
        val squareBitmap = Bitmap.createBitmap(squareSideLength, squareSideLength,
                Bitmap.Config.ARGB_8888)
        val squareBitmapWidth = squareBitmap.width
        val squareBitmapHeight = squareBitmap.height
        val deltaX: Int
        val deltaY: Int
        deltaX = if (bitmapWidth > squareBitmapWidth) {
            -(bitmapWidth - squareBitmapWidth) / 2
        } else {
            (bitmapWidth - squareBitmapWidth) / 2
        }
        deltaY = if (bitmapHeight > squareBitmapHeight) {
            -(bitmapHeight / 2 - squareBitmapHeight / 2)
        } else {
            bitmapHeight / 2 - squareBitmapHeight / 2
        }
        val squareCanvas = Canvas(squareBitmap)
        squareCanvas.drawBitmap(bitmap, deltaX.toFloat(), deltaY.toFloat(), null)
        return squareBitmap
    }
}