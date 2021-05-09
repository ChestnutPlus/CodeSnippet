package x.chestnut.code.snippet.anim.carTrain.recycler.recyclerView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.anim.carTrain.recycler.adapter.CarSimpleAdapter

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/31 11:41
 * desc  : 针对小火车的，部分参数写死
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarRecyclerGallery : RecyclerView {
    private val mSelectedScale = 1.2f //选中的时候，放大的倍数，真实
    private val mNotSelectedScale = 0.8f //未选中时候的倍数，非真实，0.8f实际对应1.0f
    private var isReadyToScale = false

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {}

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        if (child.tag != null && child.tag as Int != CarSimpleAdapter.TYPE_ITEM_CAR_Locomotive && isReadyToScale) {
            val x = child.left

            // view's width and height
            val vWidth = child.width - child.paddingLeft - child.paddingRight
            val vHeight = child.height - child.paddingTop - child.paddingBottom

            // device's width
            val dWidth = resources.displayMetrics.widthPixels
            if (vWidth >= dWidth) {
                return super.drawChild(canvas, child, drawingTime)
            }
            var scale: Float
            //中心轴，x
            val pivot = (dWidth - vWidth) / 2
            scale = if (x <= pivot) {
                2 * (mSelectedScale - mNotSelectedScale) * (x + vWidth) / (dWidth + vWidth) + mNotSelectedScale
            } else {
                2 * (mSelectedScale - mNotSelectedScale) * (dWidth - x) / (dWidth + vWidth) + mNotSelectedScale
            }
            child.pivotX = (vWidth / 2).toFloat()
            child.pivotY = (vHeight / 2).toFloat()
            scale = if (scale < 1) 1f else scale
            child.scaleX = scale
            child.scaleY = scale
            if (scale > 1) {
                val yMax = child.height * (mSelectedScale - 1)
                val yScaleNow = child.height * (scale - 1)
                child.translationY = -1 * yScaleNow / yMax * 29
            } else {
                child.scaleX = 1f
                child.scaleY = 1f
                child.translationY = 0f
            }

            //child.setAlpha(scale*scale*scale);
        } else {
            child.scaleX = 1f
            child.scaleY = 1f
            child.translationY = 0f
        }
        return super.drawChild(canvas, child, drawingTime)
    }

    fun setReadyToScale(readyToScale: Boolean) {
        isReadyToScale = readyToScale
        this.adapter!!.notifyDataSetChanged()
        if (this.adapter!!.itemCount >= 2) {
            smoothScrollToPosition(0)
        }
    }

    fun isReadyToScale(): Boolean {
        return isReadyToScale
    }
}