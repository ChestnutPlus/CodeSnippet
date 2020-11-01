package x.chestnut.code.snippet.ui.recyclerView.itemDecorationStickySection;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.GroupInfo;
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.GroupInfoCallback;

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

/*
 *   参考：https://blog.csdn.net/briblue/article/details/70211942
 * */

public class StickySectionItemDecoration extends RecyclerView.ItemDecoration {

    private GroupInfoCallback groupInfoCallback;
    private int sectionHeight;
    private int dividerHeight;
    private Paint mPaint;

    public StickySectionItemDecoration(GroupInfoCallback groupInfoCallback, int sectionHeight, int dividerHeight) {
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
            int position = parent.getChildAdapterPosition(view);
            if (groupInfoCallback!=null) {
                GroupInfo groupInfo = groupInfoCallback.getGroupInfo(position);
                //获取位置信息
                int left = view.getPaddingLeft();
                int right = view.getWidth() - view.getPaddingRight();
                //画分割线
                if (i!=0)
                    if (!groupInfo.isFirstViewInGroup() ) {
                        int top = view.getTop() - dividerHeight;
                        int bottom = view.getTop();
                        mPaint.setColor(Color.BLACK);
                        c.drawRoundRect(left,top,right,bottom,0,0,mPaint);
                    }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            //获取位置
            int position = parent.getChildAdapterPosition(view);
            if (groupInfoCallback!=null) {
                //获取对应Item的组信息
                GroupInfo groupInfo = groupInfoCallback.getGroupInfo(position);
                //获取位置信息
                int left = view.getPaddingLeft();
                int right = view.getWidth() - view.getPaddingRight();
                //屏幕上第一个可见的 ItemView 时，i == 0;
                if (i!=0) {
                    //只有组内的第一个ItemView之上才绘制
                    if (groupInfo.isFirstViewInGroup() ) {
                        int top = view.getTop() - sectionHeight;
                        int bottom = view.getTop();
                        drawSection(c,groupInfo,left,top,right,bottom);
                    }
                    //如果不是，就是要画分割线
                    else {
                        //在这里画分割线会挡住上面的Section
                        //所以这段逻辑应该放在 onDraw 中
                    }
                }
                else {
                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。
                    int top = parent.getPaddingTop();

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View
                    //如果是最后一个，说明要判断移动的时候，是否需要Section跟着Item滑动
                    if (groupInfo.isLastViewInGroup()) {

                        //移动的距离就是section的高度与条目底部的差
                        //这个值，在Section需要跟着移动的时候，就是为负数
                        int suggestTop = view.getBottom() - sectionHeight;

                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if ( suggestTop < top ) {
                            top = suggestTop;
                        }
                    }

                    int bottom = top + sectionHeight;
                    drawSection(c,groupInfo,left,top,right,bottom);
                }
            }
        }
    }

    private void drawSection(Canvas c, GroupInfo groupInfo, int left, int top, int right, int bottom) {
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
}
