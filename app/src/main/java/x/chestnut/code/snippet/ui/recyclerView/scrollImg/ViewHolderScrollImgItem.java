package x.chestnut.code.snippet.ui.recyclerView.scrollImg;

import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import x.chestnut.code.snippet.R;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/30 23:53
 *     desc  :
 *     thanks To:   在固定长宽imageView上,显示图片指定区域
 *                  https://blog.csdn.net/leol_2/article/details/80281683
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ViewHolderScrollImgItem extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private int position = 0;

    public ViewHolderScrollImgItem(@NonNull View itemView, RecyclerView recyclerView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img);
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 判断是linearLayoutManager
                if (imageView.getDrawable()!=null && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    int imageViewWidth = imageView.getWidth();
                    int imageViewHeight = imageView.getHeight();
                    int srcWidth = imageView.getDrawable().getBounds().width();
                    int srcHeight = imageView.getDrawable().getBounds().height();

                    //linearLayoutManager-VERTICAL，控件的宽度要大于高度，且图片的是一张长图
                    if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL
                            && imageViewWidth > imageViewHeight && srcHeight >= srcWidth) {

                        int realHeight = srcHeight * imageViewWidth / srcWidth;

                        // 当最后一个可见，并非完全可见的时候，初始化位置
                        if (linearLayoutManager.findLastVisibleItemPosition() == position) {
                            int paddingTop = realHeight - imageViewHeight;
                            imageView.setPadding(0, paddingTop, 0, 0);
                            Log.i("ViewHolderScrollImgItem", "paddingTop:"+(paddingTop));
                        }

                        // 当为第一个可见，并非完全可见的时候，初始化位置
                        else if (linearLayoutManager.findFirstVisibleItemPosition() == position) {
                            int paddingTop = realHeight - imageViewHeight;
                            imageView.setPadding(0, -paddingTop, 0, 0);
                            Log.i("ViewHolderScrollImgItem", "-paddingTop:"+(-paddingTop));
                        }

                        //  并且为屏幕上最后一个或者是最前一个完全可见的时候
                        //  才开始移动
                        else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == position
                                || linearLayoutManager.findFirstCompletelyVisibleItemPosition() == position) {

                            Resources resources = recyclerView.getResources();
                            DisplayMetrics dm = resources.getDisplayMetrics();
                            int screenHeight = dm.heightPixels;
                            View view = linearLayoutManager.findViewByPosition(position);

                            if (view!=null) {

                                //可移动的距离
                                int scrollScreen = screenHeight - imageViewHeight;
                                int scrollImg = realHeight - imageViewHeight;

                                //图片移动的距离
                                int temp = (int) (view.getY() * scrollImg / scrollScreen);
                                imageView.setPadding(0, temp, 0, 0);

                                Log.i("ViewHolderScrollImgItem", "temp:" + temp + ", y:" + view.getY() + ", scrollScreen:"+scrollScreen);
                            }
                        }
                    }
                }
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
    }

    public void setImageView(@DrawableRes int img) {
        imageView.setImageResource(img);
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
