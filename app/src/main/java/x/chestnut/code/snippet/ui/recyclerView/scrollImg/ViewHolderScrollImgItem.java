package x.chestnut.code.snippet.ui.recyclerView.scrollImg;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private int scrollYTopOffset = 100;
    private int scrollYBottomOffset = 100;

    private ImageView imageView;
    private int position = 0;
    private int maxScrollItemTop = 0;

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

                    /*记录PaddingStart和PaddingEnd的值，后面需要恢复*/
                    int paddingLeft = imageView.getPaddingLeft();
                    int paddingRight = imageView.getPaddingRight();

                    /*ImageView实际的显示区域*/
                    int imageViewWidth = imageView.getWidth() - paddingLeft - paddingRight;
                    int imageViewHeight = imageView.getHeight();

                    /*图片的大小，内存中的，并非实际大小*/
                    int srcWidth = imageView.getDrawable().getBounds().width();
                    int srcHeight = imageView.getDrawable().getBounds().height();

                    //linearLayoutManager-VERTICAL，控件的宽度要大于高度，且图片的是一张长图
                    if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL
                            && imageViewWidth > imageViewHeight && srcHeight >= srcWidth) {

                        /*按照，图片铺满宽度，换算出，图片在这种比例下的height*/
                        int realHeight = srcHeight * imageViewWidth / srcWidth;

                        /*算出，在最后一个，第一个的时候，paddingTop的绝对值*/
                        int paddingTop = realHeight - imageViewHeight;

                        // 当最后一个可见，并非完全可见的时候，初始化位置
                        if (linearLayoutManager.findLastVisibleItemPosition() == position) {
                            imageView.setPadding(paddingLeft, paddingTop, paddingRight, 0);
                        }

                        // 当为第一个可见，并非完全可见的时候，初始化位置
                        else if (linearLayoutManager.findFirstVisibleItemPosition() == position) {
                            imageView.setPadding(paddingLeft, -paddingTop, paddingRight, 0);
                        }

                        //  并且为屏幕上最后一个或者是最前一个完全可见的时候
                        //  才开始移动
                        else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= position
                                && position <= linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                            View view = linearLayoutManager.findViewByPosition(position);
                            if (view!=null) {
                                //可移动的距离
                                int scrollImg = realHeight - imageViewHeight;
                                if (view.getY()> maxScrollItemTop)
                                    maxScrollItemTop = (int) view.getY();
                                if (maxScrollItemTop-scrollYBottomOffset>view.getY() && view.getY()>scrollYTopOffset) {
                                    //图片移动的距离
                                    int temp = (int) ((view.getY() - scrollYTopOffset) * (scrollImg + paddingTop)
                                            / (maxScrollItemTop - scrollYBottomOffset - scrollYTopOffset)) - paddingTop;
                                    imageView.setPadding(paddingLeft, temp, paddingRight, 0);
                                }
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
