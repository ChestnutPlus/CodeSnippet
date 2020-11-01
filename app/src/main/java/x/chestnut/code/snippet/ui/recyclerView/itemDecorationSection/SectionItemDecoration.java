package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/30 15:31
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class SectionItemDecoration extends RecyclerView.ItemDecoration {

    private GroupInfoCallback groupInfoCallback;
    private int sectionHeight;
    private int dividerHeight;
    private Paint mPaint;

    public SectionItemDecoration(GroupInfoCallback groupInfoCallback, int sectionHeight, int dividerHeight) {
        this.groupInfoCallback = groupInfoCallback;
        this.sectionHeight = sectionHeight;
        this.dividerHeight = dividerHeight;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (groupInfoCallback!=null) {
            //获取对应Item的组信息
            GroupInfo groupInfo = groupInfoCallback.getGroupInfo(position);
            //如果是组的第一个，那么就要显示Header，在这里就先撑开对应的大小
            if (groupInfo!=null && groupInfo.isFirstViewInGroup()) {
                outRect.top = sectionHeight;
            }
            else {
                outRect.top = dividerHeight;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            //获取位置
            int position = parent.getChildAdapterPosition(view);
            if (groupInfoCallback!=null) {
                //获取对应Item的组信息
                GroupInfo groupInfo = groupInfoCallback.getGroupInfo(position);
                //获取位置信息
                int top;
                int left = view.getPaddingLeft();
                int right = view.getWidth() - view.getPaddingRight();
                int bottom = view.getTop();
                if (groupInfo!=null && groupInfo.isFirstViewInGroup()) {
                    //只有组的第一个ItemView才绘制Header
                    top = view.getTop() - sectionHeight;
                    //绘制Header-Bg
                    mPaint.setColor(Color.LTGRAY);
                    c.drawRoundRect(left,top,right,bottom,0,0,mPaint);
                    //绘制Header-Title
                    float titleX = left + 10;
                    float titleY = bottom - 10;
                    mPaint.setColor(Color.WHITE);
                    mPaint.setTextSize(80);
                    c.drawText(groupInfo.groupTitle,titleX,titleY,mPaint);
                }
                else {
                    //如果不是，则绘制分割线
                    top = view.getTop() - dividerHeight;
                    mPaint.setColor(Color.BLACK);
                    c.drawRoundRect(left,top,right,bottom,0,0,mPaint);
                }
            }
        }
    }
}
