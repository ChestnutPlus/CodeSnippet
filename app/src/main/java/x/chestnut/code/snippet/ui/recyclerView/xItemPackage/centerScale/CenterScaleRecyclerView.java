package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/4/3 9:51
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class CenterScaleRecyclerView extends RecyclerView {

    private final float mSelectedScale = 1.1f;      //选中的时候，放大的倍数
    private final float mNotSelectedScale = 0.5f;   //未选中时候的倍数

    public CenterScaleRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CenterScaleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterScaleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
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
            scale = (vPivot - baseStartX) * (mSelectedScale - mNotSelectedScale) / (dPivot - baseStartX) + mNotSelectedScale;
        }
        //右边
        else {
            scale = (vPivot - dPivot) * (mNotSelectedScale - mSelectedScale) / (baseEnd - dPivot) + mSelectedScale;
        }

        scale = scale < mNotSelectedScale ? mNotSelectedScale : scale;
        scale = scale > mSelectedScale ? mSelectedScale : scale;

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
