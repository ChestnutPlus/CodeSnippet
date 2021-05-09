package x.chestnut.code.snippet.anim.carBgView

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/13 14:55
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CycleScrollBitmapBean(index: Int, bitmap: Bitmap, addFactor: Int, top: Int) {

    var index = 0
    var bitmap: Bitmap
    var addFactor = 1
    var top = -1

    //屏幕的位置
    private val screenLeftRect = Rect()
    private val screenRightRect = Rect()

    //图片的位置
    private val srcLeftRect = Rect()
    private val srcRightRect = Rect()

    fun onDraw(canvas: Canvas, paint: Paint?) {
        screenLeftRect.right = index
        screenRightRect.left = index
        srcRightRect.right = 1280 - index
        srcLeftRect.left = 1280 - index
        canvas.drawBitmap(bitmap, srcLeftRect, screenLeftRect, paint)
        canvas.drawBitmap(bitmap, srcRightRect, screenRightRect, paint)
        index += addFactor
        if (index >= 1280) {
            index = 0
        }
    }

    init {
        this.index = index
        this.bitmap = bitmap
        this.addFactor = addFactor
        this.top = top
        screenLeftRect.left = 0
        screenLeftRect.bottom = 720
        screenLeftRect.top = top
        screenRightRect.right = 1280
        screenRightRect.bottom = 720
        screenRightRect.top = top
        srcRightRect.left = 0
        srcRightRect.top = 0
        srcRightRect.bottom = bitmap.height
        srcLeftRect.top = 0
        srcLeftRect.right = 1280
        srcLeftRect.bottom = bitmap.height
    }
}