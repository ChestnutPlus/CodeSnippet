package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/1/4 10:48
 *     desc  : 使用的是，只需要在RecyclerView.Adapter的方法中:，
 *              返回巨大的：Integer.MAX_VALUE;
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class CircleRecyclerView extends RecyclerView {

    private int defaultSelected = Integer.MAX_VALUE >> 1;
    private boolean mNeedLoop = true;
    private boolean mFirstOnLayout;

    public CircleRecyclerView(Context context) {
        this(context, null);
    }

    public CircleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setDefaultSelected(int totalDataSize, int defaultSelected) {
        this.defaultSelected = this.defaultSelected + (totalDataSize - this.defaultSelected % totalDataSize) + defaultSelected;
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (mNeedLoop) {
            if (!mFirstOnLayout) {
                mFirstOnLayout = true;
                scrollToPosition(defaultSelected);
            }
        }
    }

    public void setNeedLoop(boolean needLoop) {
        mNeedLoop = needLoop;
    }
}
