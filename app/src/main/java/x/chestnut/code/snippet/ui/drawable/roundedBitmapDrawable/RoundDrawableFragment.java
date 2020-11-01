package x.chestnut.code.snippet.ui.drawable.roundedBitmapDrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/6 12:47
 *     desc  :
 *     thanks To:   看完这篇文章，我保证你也会用 RoundedBitmapDrawable 创建圆角头像
 *                  https://juejin.im/post/5ca3eaf8518825440a4b9ee1#heading-10
 *     dependent on:
 *     update log:
 * </pre>
 */
public class RoundDrawableFragment extends BaseFragment {

    public static RoundDrawableFragment newInstance() {
        RoundDrawableFragment fragment = new RoundDrawableFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_drawable_round;
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        super.onLazyViewCreate(rootView);

        /*圆形Drawable*/
        ImageView img1 = rootView.findViewById(R.id.img_1);
        //第一步：创建 RoundedBitmapDrawable 对象
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.girl_4));
        //第二步：设置 RoundedBitmapDrawable 为圆形
        circleDrawable.setCircular(true);
        //第三步：将 RoundedBitmapDrawable 设置到 ImageView 上
        img1.setImageDrawable(circleDrawable);

        /*圆角Drawable*/
        ImageView img2 = rootView.findViewById(R.id.img_2);
        RoundedBitmapDrawable roundRectangleDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.girl_6));
        roundRectangleDrawable.setCornerRadius(50);
        img2.setImageDrawable(roundRectangleDrawable);

        /*1. RoundedBitmapDrawable 引用的图片资源，须为正方形，否则会被强制压缩。
          2. ImageView 的 scaleType 须为 fitCenter，否则出现“不良反应”*/
        ImageView img3 = rootView.findViewById(R.id.img_3);
        Bitmap circleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.long_0);
        circleBitmap = transferToSquareBitmap(circleBitmap);
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), circleBitmap);
        bitmapDrawable.setCircular(true);
        img3.setImageDrawable(bitmapDrawable);
    }

    /**
     * 根据传入的 Bitmap 的短边将 Bitmap 转换成正方形
     * @param bitmap bitmap
     * @return bitmap
     */
    public static Bitmap transferToSquareBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int squareSideLength = Math.min(bitmapWidth, bitmapHeight);
        Bitmap squareBitmap = Bitmap.createBitmap(squareSideLength, squareSideLength, Bitmap.Config.ARGB_8888);
        int squareBitmapWidth = squareBitmap.getWidth();
        int squareBitmapHeight = squareBitmap.getHeight();
        int deltaX, deltaY;
        if(bitmapWidth > squareBitmapWidth){
            deltaX = - (bitmapWidth - squareBitmapWidth)/2;
        }else {
            deltaX = (bitmapWidth - squareBitmapWidth)/2;
        }
        if(bitmapHeight > squareBitmapHeight){
            deltaY = - (bitmapHeight/2 - squareBitmapHeight/2);
        }else {
            deltaY = (bitmapHeight/2 - squareBitmapHeight/2);
        }
        Canvas squareCanvas = new Canvas(squareBitmap);
        squareCanvas.drawBitmap(bitmap, deltaX, deltaY, null);
        return squareBitmap;
    }
}
