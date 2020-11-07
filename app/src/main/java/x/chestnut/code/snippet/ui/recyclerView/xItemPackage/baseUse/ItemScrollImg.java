package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/2 13:57
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class ItemScrollImg extends XItem<Integer> {

    private RecyclerView recyclerView;
    private int scrollYTopOffset = 100;
    private int scrollYBottomOffset = 100;

    private ImageView imageView;
    private int position = 0;
    private int maxScrollItemTop = 0;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            // 判断是linearLayoutManager
            if (imageView.getDrawable()!=null
                    && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager =
                        (LinearLayoutManager) recyclerView.getLayoutManager();

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
                            if (maxScrollItemTop-scrollYBottomOffset>view.getY()
                                    && view.getY()>scrollYTopOffset) {
                                //图片移动的距离
                                int temp = (int) ((view.getY() - scrollYTopOffset)
                                        * (scrollImg + paddingTop)
                                        / (maxScrollItemTop - scrollYBottomOffset - scrollYTopOffset))
                                        - paddingTop;
                                imageView.setPadding(paddingLeft, temp, paddingRight, 0);
                            }
                        }
                    }
                }
            }
        }
    };

    public ItemScrollImg(Integer integer, RecyclerView recyclerView) {
        super(integer);
        this.recyclerView = recyclerView;
    }

    @Override
    public XHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_scroll_img, parent,false));
    }

    @Override
    public void onBindViewHolder(XHolder holder, int position) {
        this.position = position;
        imageView = (ImageView) holder.getViewById(R.id.img);
        imageView.setImageResource(data);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.Item_Scroll_Img;
    }

    @Override
    public void releaseRes() {
        if (recyclerView!=null)
            recyclerView.addOnScrollListener(onScrollListener);
    }
}
