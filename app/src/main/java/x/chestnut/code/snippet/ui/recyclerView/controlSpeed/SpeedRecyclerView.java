package x.chestnut.code.snippet.ui.recyclerView.controlSpeed;

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
public class SpeedRecyclerView extends RecyclerView {

    //控制滑动速度
    public float FLING_SCALE_DOWN_FACTOR = 1.0f; // 减速因子
    public int FLING_MAX_VELOCITY = 3000; // 最大顺时滑动速度

    public SpeedRecyclerView(Context context) {
        this(context, null);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = solveVelocity(velocityX);
        velocityY = solveVelocity(velocityY);
        return super.fling(velocityX, velocityY);
    }

    private int solveVelocity(int velocity) {
        if (velocity > 0) {
            return (int) Math.min(velocity, FLING_MAX_VELOCITY * FLING_SCALE_DOWN_FACTOR);
        } else {
            return (int) Math.max(velocity, -FLING_MAX_VELOCITY * FLING_SCALE_DOWN_FACTOR);
        }
    }
}
