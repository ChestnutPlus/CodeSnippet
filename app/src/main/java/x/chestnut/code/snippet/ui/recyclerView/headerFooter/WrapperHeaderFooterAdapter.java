package x.chestnut.code.snippet.ui.recyclerView.headerFooter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/30 17:43
 *     desc  :
 *     thanks To:   https://blog.csdn.net/lmj623565791/article/details/51854533
 *     dependent on:
 *     update log:
 * </pre>
 */
public class WrapperHeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*定义了，Header，Footer 的 ItemType*/
    private final int BASE_ITEM_TYPE_HEADER = 100000;
    private final int BASE_ITEM_TYPE_FOOTER = 200000;

    /* int - object，int 为 ItemType */
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public WrapperHeaderFooterAdapter(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
    }

    /*包装一些扩展的方法*/

    /**
     * 传入的是，列表的Position，0-N
     * 判断是否是Headers
     * @param position 0-N
     * @return true/false
     */
    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //先判断是否是，Headers 和 Foots 中的View
        //  是的话，直接创建 InnerViewHolder 并返回
        View view = mHeaderViews.get(viewType);
        if (view!=null) {
            return new InnerViewHolder(view);
        }
        view = mFootViews.get(viewType);
        if (view!=null) {
            return new InnerViewHolder(view);
        }
        //不是得话，调用原来的 ViewHolder
        return mInnerAdapter.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        //这里传入的Position依然是列表的位置0-N
        if (isHeaderViewPos(position))
            return mHeaderViews.keyAt(position);
        //所以这里 判断 Footer 的位置，需要减去前面的 Headers 和 真实 ItemCount
        else if (isFooterViewPos(position))
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        //如果都不是调用原来的方法
        return super.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //判断如果不是，头和尾的时候，才onBindViewHolder
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(viewHolder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getRealItemCount() + getHeadersCount() + getFootersCount();
    }

    /**
     * 适配 GridLayoutManager
     * @param recyclerView recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            //当 GridLayoutManager 获取 SpanSize 的时候，回调这个方法
            //所以我们重写它
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                /**
                 *  SpanSize为多少，表示占用几个item
                 *      gridLayoutManager.getSpanCount()
                 *      返回的是我们开始设置的一行几个item数量
                 * @param position  位置
                 * @return 占用几个item
                 */
                @Override
                public int getSpanSize(int position) {
                    //获取类型
                    int viewType = getItemViewType(position);
                    //判断是否是 Headers 或者 是 Footers
                    // 则占用的是所有的 Item，即是：gridLayoutManager.getSpanCount()
                    if (mHeaderViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    else if (mFootViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    // 如果不是，则，表示占用1个。
                    return 1;
                }
            });
        }
    }

    /**
     * 适配 StaggeredGridLayoutManager
     */
    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        //判断是否是 Headers 或者 是 Footers
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                //则 占据 所有的 Span
                p.setFullSpan(true);
            }
        }
    }

    private static class InnerViewHolder extends RecyclerView.ViewHolder {
        InnerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
