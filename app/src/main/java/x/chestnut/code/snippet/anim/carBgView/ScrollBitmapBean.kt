package x.chestnut.code.snippet.anim.carBgView

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/13 11:52
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ScrollBitmapBean(currentIndex: Int,
                       top: Int,
                       addFactor: Float,
                       bitmap: Bitmap) {
    //移动因子，每次 onDraw 方法移动的距离
    var addFactor = 1f

    //屏幕的最大宽度
    var screenWidth = 1280

    //当前移动的位置
    var currentIndex = -1f

    //top
    var top = -1

    //bitmap
    var bitmap: Bitmap
    fun onDraw(canvas: Canvas, paint: Paint?) {
        canvas.drawBitmap(bitmap, currentIndex, top.toFloat(), paint)
        currentIndex += addFactor
        if (currentIndex >= screenWidth) {
            currentIndex = (-1 * bitmap.width).toFloat()
        }
    }

    init {
        this.currentIndex = currentIndex.toFloat()
        this.top = top
        this.bitmap = bitmap
        this.addFactor = addFactor
    }
}