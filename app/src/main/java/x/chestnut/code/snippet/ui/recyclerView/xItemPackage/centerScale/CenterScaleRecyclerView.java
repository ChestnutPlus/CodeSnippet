package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/3 9:51
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class CenterScaleRecyclerView extends RecyclerView {

    private static final float M_SELECTED_SCALE = 1.1f;      //选中的时候，放大的倍数
    private static final float M_NOT_SELECTED_SCALE = 0.5f;   //未选中时候的倍数

    public CenterScaleRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CenterScaleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterScaleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {

        int x = child.getLeft();

        // view's width and height
        int vWidth = child.getWidth() - child.getPaddingLeft() - child.getPaddingRight();

        // device's width
        int dWidth = getResources().getDisplayMetrics().widthPixels;

        if (vWidth >= dWidth) {
            return super.drawChild(canvas, child, drawingTime);
        }

        int vPivot = (int) (x + 0.5f * vWidth);
        int dPivot = (int) (0.5f * dWidth);

        //左边
        int baseStartX = (dWidth - vWidth) / 2;
        int baseEnd = dWidth - baseStartX;
        float scale;
        if (vPivot<dPivot) {
            scale = (vPivot - baseStartX) * (M_SELECTED_SCALE - M_NOT_SELECTED_SCALE)
                    / (dPivot - baseStartX) + M_NOT_SELECTED_SCALE;
        }
        //右边
        else {
            scale = (vPivot - dPivot) * (M_NOT_SELECTED_SCALE - M_SELECTED_SCALE)
                    / (baseEnd - dPivot) + M_SELECTED_SCALE;
        }

        scale = scale < M_NOT_SELECTED_SCALE ? M_NOT_SELECTED_SCALE : scale;
        scale = scale > M_SELECTED_SCALE ? M_SELECTED_SCALE : scale;

        child.setScaleX(scale);
        child.setScaleY(scale);

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
        return super.drawChild(canvas, child, drawingTime);
    }

}
