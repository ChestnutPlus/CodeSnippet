package x.chestnut.code.snippet.anim.leafLoadingAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import x.chestnut.code.snippet.R;


/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2018/6/29 13:51
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class LeafLoadingView extends View {

    private String TAG = "LeafLoadingView";

    private int progressPadding = 20;
    private int height,width,centerX,centerY;

    private Paint paint;
    private RectF leftArcRectF;     //左边内半圆
    private Matrix matrix = new Matrix();
    private Random random =new Random();

    //进度
    private int progress = 0;        //当前进度值
    private int MAX_PROGRESS = 100;  //最大的进度值

    //白色背景
    private RectF bgRectF;

    //fan
    private int fanBgMargin = 10;
    private int fanPadding = 10;
    private Bitmap fanBitmap;
    private int fanSpeed = 0;
    private int fanSpeedFactor = 0;

    //叶子
    private Bitmap leafBitmap;
    private long LEAF_ROTATE_TIME = 2000;   //叶子旋转一周的时间，毫秒
    private long leafRotateTime = LEAF_ROTATE_TIME;
    private long LEAF_FLOAT_TIME = 3000;    // 叶子飘动一个周期所花的时间
    private long leafFloatTime = LEAF_FLOAT_TIME;
    private List<Leaf> mLeafs;
    private int leafSize = 6;

    public LeafLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        mLeafs = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w - progressPadding*2;
        height = h - progressPadding*2;
        centerX = w/2;
        centerY = h/2;
        mLeafs.clear();
        for (int i = 0; i < leafSize; i++) {
            mLeafs.add(Leaf.generate(random,height));
        }
        leftArcRectF = new RectF(centerX-width/2,centerY-height/2,centerX-width/2+height,centerY-height/2+height);
        bgRectF = new RectF(centerX-width/2-progressPadding, centerY-height/2-progressPadding,centerX+width/2+progressPadding,centerY+height/2+progressPadding);
        initFanBitmap();
        leafBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.leaf)).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (progress<0)
            progress = 0;
        if (progress>MAX_PROGRESS)
            progress = MAX_PROGRESS;
        //绘制浅色背景
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(252,228,155));
        canvas.drawRoundRect(bgRectF,height/2+progressPadding,height/2+progressPadding, paint);
        //绘制叶子
        drawLeafs(canvas);
        //绘制橘色进度
        paint.setColor(Color.rgb(255,168,0));
        int currentPositionWidth = (width - height/2) * progress / MAX_PROGRESS;
        if (currentPositionWidth<height/2) {
            int singleAngle = (int) Math.toDegrees(Math.acos((height/2 - currentPositionWidth)/(float)(height/2)));
            int startAngle = 180 - singleAngle;
            canvas.drawArc(leftArcRectF, startAngle, singleAngle*2, false, paint);
        }
        else {
            canvas.drawArc(leftArcRectF, 90, 180, true, paint);
            canvas.drawRect(centerX-width/2+height/2, centerY-height/2,
                    centerX-width/2+height/2+currentPositionWidth-height/2,
                    centerY-height/2+height,paint);
        }
        //绘制风扇Bg外框
        fanBgMargin = fanBgMargin > progressPadding ? progressPadding : fanBgMargin;
        fanBgMargin = fanBgMargin < 0 ? 0 : fanBgMargin;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(fanBgMargin);
        canvas.drawCircle(centerX+width/2-height/2, centerY, height/2+progressPadding-fanBgMargin/2, paint);
        //绘制风扇Bg
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255,168,0));
        canvas.drawCircle(centerX+width/2-height/2, centerY, height/2+progressPadding-fanBgMargin, paint);
        //绘制风扇
        fanSpeed = (fanSpeedFactor + 2) + fanSpeed;
        int fanTemp = height/2+progressPadding-fanBgMargin-fanPadding;
        drawSelfRotateBitmap(canvas, paint, fanBitmap, fanSpeed, centerX+width/2-height/2-fanTemp, centerY-fanTemp);
        invalidate();
    }

    /**
     * 绘制自旋转位图
     *
     * @param canvas canvas
     * @param paint paint
     * @param bitmap 位图对象
     * @param rotation 旋转度数
     * @param posX 在canvas的位置坐标
     * @param posY 在canvas的位置坐标
     */
    private void drawSelfRotateBitmap(Canvas canvas, Paint paint, Bitmap bitmap, float rotation, float posX, float posY) {
        matrix.reset();
        int offsetX = bitmap.getWidth() / 2;
        int offsetY = bitmap.getHeight() / 2;
        matrix.postTranslate(-offsetX, -offsetY);
        matrix.postRotate(rotation);
        matrix.postTranslate(posX + offsetX, posY + offsetY);
        canvas.drawBitmap(bitmap, matrix, paint);
    }

    private void initFanBitmap() {
        Bitmap oldBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.feng_shan)).getBitmap();
        matrix.reset();
        matrix.postScale((float) (height+2*progressPadding-2*fanBgMargin-2*fanPadding)/oldBitmap.getWidth(),(float) (height+2*progressPadding-2*fanBgMargin-2*fanPadding)/oldBitmap.getHeight());
        fanBitmap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, true);
    }

    /**
     * 绘制叶子
     */
    private void drawLeafs(Canvas canvas) {
        leafRotateTime = leafRotateTime <= 0 ? LEAF_ROTATE_TIME : leafRotateTime;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafs.size(); i++) {
            Leaf leaf = mLeafs.get(i);
            if (currentTime > leaf.startTime && leaf.startTime != 0) {
                // 绘制叶子－－根据叶子的类型和当前时间得出叶子的（x，y）
                getLeafLocation(leaf, currentTime);
                // 根据时间计算旋转角度
                canvas.save();
                // 通过Matrix控制叶子旋转
                matrix.reset();
                float transX = progressPadding + leaf.x;
                float transY = progressPadding + leaf.y;
                matrix.postTranslate(transX, transY);
                // 通过时间关联旋转角度，则可以直接通过修改 LEAF_ROTATE_TIME 调节叶子旋转快慢
                float rotateFraction = ((currentTime - leaf.startTime) % leafRotateTime) / (float) leafRotateTime;
                int angle = (int) (rotateFraction * 360);
                // 根据叶子旋转方向确定叶子旋转角度
                int rotate = leaf.rotateDirection == 0 ? angle + leaf.rotateAngle : -angle + leaf.rotateAngle;
                matrix.postRotate(rotate, transX + leafBitmap.getWidth() / 2, transY + leafBitmap.getHeight() / 2);
                canvas.drawBitmap(leafBitmap, matrix, paint);
                canvas.restore();
            }
        }
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.startTime;
        leafFloatTime = leafFloatTime <= 0 ? LEAF_FLOAT_TIME : leafFloatTime;
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > leafFloatTime) {
            leaf.updateParam(random, height);
        }

        float fraction = (float) intervalTime / leafFloatTime;
        int widthTemp = width - height;
        leaf.x = (int) (widthTemp - widthTemp * fraction);
        leaf.y = getLocationY(leaf) + centerY - height/2;
    }

    // 通过叶子信息获取当前叶子的Y值
    private int getLocationY(Leaf leaf) {
        // y = A(wx+phase)+h
        float w = (float) ((float) 2 * Math.PI / width);
        return (int) (leaf.am * Math.sin(w * leaf.x + leaf.phase)) + (height/4);
    }

    /**
     * 叶子对象，用来记录叶子主要数据
     */
    private static class Leaf {

        // 用于控制随机增加的时间不抱团
        static long mAddTime;
        // 在绘制部分的位置
        float x, y;
        // 叶子轨迹的幅度
        int am;
        // 叶子轨迹的相位
        int phase;
        // 旋转角度
        int rotateAngle;
        // 旋转方向--0代表顺时针，1代表逆时针
        int rotateDirection;
        // 起始时间(ms)
        long startTime;

        /**
         * 更新叶子的部分参数
         * @param random 随机对象
         * @param height 进度条的高度
         */
        void updateParam(Random random, int height) {
            startTime = System.currentTimeMillis() + random.nextInt(1000);
            update(random, height);
        }

        static Leaf generate(Random random, int height) {
            Leaf leaf = new Leaf();
            leaf.update(random, height);
            // 为了产生交错的感觉，让开始的时间有一定的随机性
            mAddTime += random.nextInt(1000);
            leaf.startTime = System.currentTimeMillis() + mAddTime;
            return leaf;
        }

        private void update(Random random, int height) {
            rotateDirection = random.nextInt(2);
            rotateAngle = random.nextInt(360);
            switch (random.nextInt(3)) {
                case 0:
                    am = random.nextInt(height/6) - height/7;
                    break;
                case 2:
                    am = random.nextInt(height/6) + height/7;
                    break;
                default:
                    am = random.nextInt(height/6);
                    break;
            }
            phase = random.nextInt(20)+20;
        }
    }


    /**
     * 对外暴露的接口
     */

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setFanBgMargin(int fanBgMargin) {
        initFanBitmap();
        this.fanBgMargin = fanBgMargin;
    }

    public void setFanSpeedFactor(int fanSpeedFactor) {
        fanSpeedFactor = fanSpeedFactor < 0 ? 0 : fanSpeedFactor;
        fanSpeedFactor = fanSpeedFactor > 4 ? 4 : fanSpeedFactor;
        this.fanSpeedFactor = fanSpeedFactor;
    }

    public void setLeafSize(int leafSize) {
        if (leafSize-this.leafSize>0) {
            int addCount = leafSize - this.leafSize;
            for (int i = 0; i < addCount; i++) {
                mLeafs.add(Leaf.generate(random,height));
            }
        }
        else if (leafSize-this.leafSize<0) {
            int minCount = this.leafSize - leafSize;
            for (int i = 0; i < minCount; i++) {
                mLeafs.remove(mLeafs.size()-1);
            }
        }
        this.leafSize = leafSize;
    }
}

