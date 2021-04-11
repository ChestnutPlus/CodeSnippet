package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/3 9:51
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CenterScaleRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                defStyle: Int) : super(context, attrs, defStyle)

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        val x = child.left

        // view's width and height
        val vWidth = child.width - child.paddingLeft - child.paddingRight

        // device's width
        val dWidth = resources.displayMetrics.widthPixels
        if (vWidth >= dWidth) {
            return super.drawChild(canvas, child, drawingTime)
        }
        val vPivot: Float = (x + 0.5f * vWidth)
        val dPivot: Float = (0.5f * dWidth)

        //左边
        val baseStartX = (dWidth - vWidth) / 2
        val baseEnd = dWidth - baseStartX
        var scale: Float
        scale = if (vPivot < dPivot) {
            (vPivot - baseStartX) * (M_SELECTED_SCALE - M_NOT_SELECTED_SCALE) / dPivot - baseStartX + M_NOT_SELECTED_SCALE
        } else {
            (vPivot - dPivot) * (M_NOT_SELECTED_SCALE - M_SELECTED_SCALE) / baseEnd - dPivot + M_SELECTED_SCALE
        }
        scale = if (scale < M_NOT_SELECTED_SCALE) M_NOT_SELECTED_SCALE else scale
        scale = if (scale > M_SELECTED_SCALE) M_SELECTED_SCALE else scale
        child.scaleX = scale
        child.scaleY = scale

        //位移
//        int vHeight = child.getHeight() - child.getPaddingTop() - child.getPaddingBottom();
//        if (scale > 1) {
//            float yMax = child.getHeight() * (mSelectedScale - 1);
//            float yScaleNow = child.getHeight() * (scale - 1);
//            child.setTranslationY(-1 * yScaleNow / yMax * 29);
//        }
//        else {
//            child.setScaleX(1);
//            child.setScaleY(1);
//            child.setTranslationY(0);
//        }
        return super.drawChild(canvas, child, drawingTime)
    }

    companion object {
        private const val M_SELECTED_SCALE = 1.1f //选中的时候，放大的倍数
        private const val M_NOT_SELECTED_SCALE = 0.5f //未选中时候的倍数
    }
}