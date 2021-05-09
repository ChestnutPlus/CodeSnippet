package x.chestnut.code.snippet.anim.carTrain.recycler.layoutManager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/3 14:53
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MyLinearLayoutManager : LinearLayoutManager {
    private var isScrollVerticallyEnabled = true
    private var isScrollHorizontallyEnabled = true

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    fun setScrollVerticallyEnabled(scrollVerticallyEnabled: Boolean) {
        isScrollVerticallyEnabled = scrollVerticallyEnabled
    }

    fun setScrollHorizontallyEnabled(scrollHorizontallyEnabled: Boolean) {
        isScrollHorizontallyEnabled = scrollHorizontallyEnabled
    }

    override fun canScrollVertically(): Boolean {
        return isScrollVerticallyEnabled && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollHorizontallyEnabled && super.canScrollHorizontally()
    }
}