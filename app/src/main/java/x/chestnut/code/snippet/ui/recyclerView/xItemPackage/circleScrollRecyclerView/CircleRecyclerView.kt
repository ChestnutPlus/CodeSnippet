package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

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
class CircleRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var defaultSelected = Int.MAX_VALUE shr 1
    private var mNeedLoop = true
    private var mFirstOnLayout = false

    fun setDefaultSelected(totalDataSize: Int, defaultSelected: Int) {
        this.defaultSelected = (this.defaultSelected
                + (totalDataSize - this.defaultSelected % totalDataSize)
                + defaultSelected)
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        if (mNeedLoop) {
            if (!mFirstOnLayout) {
                mFirstOnLayout = true
                scrollToPosition(defaultSelected)
            }
        }
    }

    fun setNeedLoop(needLoop: Boolean) {
        mNeedLoop = needLoop
    }

    init {
        overScrollMode = OVER_SCROLL_NEVER
    }
}