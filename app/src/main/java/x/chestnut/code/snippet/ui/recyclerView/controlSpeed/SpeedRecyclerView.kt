package x.chestnut.code.snippet.ui.recyclerView.controlSpeed

import android.content.Context
import android.util.AttributeSet
import kotlin.jvm.JvmOverloads
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/1/4 10:48
 * desc  : 使用的是，只需要在RecyclerView.Adapter的方法中:，
 * 返回巨大的：Integer.MAX_VALUE;
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class SpeedRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    //控制滑动速度
    private var FLING_SCALE_DOWN_FACTOR = 1.0f // 减速因子
    private var FLING_MAX_VELOCITY = 3000 // 最大顺时滑动速度

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        return super.fling(solveVelocity(velocityX), solveVelocity(velocityY))
    }

    private fun solveVelocity(velocity: Int): Int {
        return if (velocity > 0) {
            min(velocity.toFloat(), FLING_MAX_VELOCITY * FLING_SCALE_DOWN_FACTOR).toInt()
        } else {
            max(velocity.toFloat(), -FLING_MAX_VELOCITY * FLING_SCALE_DOWN_FACTOR).toInt()
        }
    }
}