package x.chestnut.code.snippet.anim.redRibbon

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/31 14:05
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class RedRibbonView : View {
    private var mPaint: Paint? = null
    private var pathsIndex = 0
    private val paths: Array<Path?>
    private var pathsPoints: Array<IntArray>? = arrayOf(intArrayOf(
            276, 96,  //1
            282, 100, 292, 102,  //2,3
            303, 105, 306, 106,  //4,5
            297, 141, 332, 195,  //6,7
            339, 206, 312, 221, 283, 219,  //8,9,10
            269, 217, 272, 181, 269, 155,  //11,12,13
            265, 126), intArrayOf(
            274, 99,  //1
            281, 103, 293, 105,  //2,3
            304, 108, 311, 110,  //4,5
            341, 152, 319, 226,  //6,7
            321, 238, 287, 215, 278, 208,  //8,9,10
            276, 203, 296, 172, 281, 151,  //11,12,13
            265, 128), intArrayOf(
            273, 101,  //1
            282, 106, 294, 108,  //2,3
            305, 111, 315, 114,  //4,5
            367, 142, 347, 219,  //6,7
            343, 232, 306, 235, 309, 224,  //8,9,10
            313, 211, 310, 164, 294, 147,  //11,12,13
            275, 126), intArrayOf(
            275, 98,  //1
            280, 102, 292, 105,  //2,3
            303, 107, 311, 110,  //4,5
            341, 152, 320, 226,  //6,7
            321, 238, 287, 215, 278, 208,  //8,9,10
            276, 203, 296, 172, 281, 151,  //11,12,13
            265, 128), intArrayOf(
            276, 96,  //1
            282, 100, 292, 102,  //2,3
            303, 105, 306, 106,  //4,5
            297, 141, 332, 195,  //6,7
            339, 206, 312, 221, 283, 219,  //8,9,10
            269, 217, 272, 181, 269, 155,  //11,12,13
            265, 126))

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(paths[pathsIndex]!!, mPaint!!)
    }

    fun playAnim() {}
    fun pauseAnim() {}
    fun stopAnim() {}
    var factor: Float
        get() = pathsIndex.toFloat()
        set(factor) {
            pathsIndex = factor.toInt()
            invalidate()
        }

    fun release() {}

    init {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        paths = arrayOfNulls(5)
        val xOffset = -50
        val yOffset = 0
        for (i in paths.indices) {
            val path = Path()
            //上
            path.moveTo((pathsPoints!![i][0] + xOffset).toFloat(), (pathsPoints!![i][1] + yOffset).toFloat()) //1(data)
            path.quadTo((
                    pathsPoints!![i][2] + xOffset).toFloat(), (pathsPoints!![i][3] + yOffset).toFloat(), (
                    pathsPoints!![i][4] + xOffset).toFloat(), (pathsPoints!![i][5] + yOffset).toFloat()) //2,3(data)
            path.quadTo((
                    pathsPoints!![i][6] + xOffset).toFloat(), (pathsPoints!![i][7] + yOffset).toFloat(), (
                    pathsPoints!![i][8] + xOffset).toFloat(), (pathsPoints!![i][9] + yOffset).toFloat()) //4,5(data)
            //右
            path.quadTo((
                    pathsPoints!![i][10] + xOffset).toFloat(), (pathsPoints!![i][11] + yOffset).toFloat(), (
                    pathsPoints!![i][12] + xOffset).toFloat(), (pathsPoints!![i][13] + yOffset).toFloat()) //6,7(data)
            //下
            path.cubicTo((
                    pathsPoints!![i][14] + xOffset).toFloat(), (pathsPoints!![i][15] + yOffset).toFloat(), (
                    pathsPoints!![i][16] + xOffset).toFloat(), (pathsPoints!![i][17] + yOffset).toFloat(), (
                    pathsPoints!![i][18] + xOffset).toFloat(), (pathsPoints!![i][19] + yOffset).toFloat()) //8,9,10(data)
            //左
            path.cubicTo((
                    pathsPoints!![i][20] + xOffset).toFloat(), (pathsPoints!![i][21] + yOffset).toFloat(), (
                    pathsPoints!![i][22] + xOffset).toFloat(), (pathsPoints!![i][23] + yOffset).toFloat(), (
                    pathsPoints!![i][24] + xOffset).toFloat(), (pathsPoints!![i][25] + yOffset).toFloat()) //11,12,13(data)
            path.quadTo((
                    pathsPoints!![i][26] + xOffset).toFloat(), (pathsPoints!![i][27] + yOffset).toFloat(), (
                    pathsPoints!![i][0] + xOffset).toFloat(), (pathsPoints!![i][1] + yOffset).toFloat()) //14,1(data)
            path.close()
            paths[i] = path
        }
        mPaint = paint
        pathsPoints = null
    }
}