package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/4/27 23:09
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public abstract class CircleAdapter<ITEM extends XItem> extends RecyclerView.Adapter<XHolder>{

    public String TAG = "CircleAdapter";
    protected List<ITEM> mData;

    public CircleAdapter(){
        mData = new ArrayList<>();
    }

    public void setData(List<ITEM> data) {
        addAll(data);
        notifyDataSetChanged();
    }

    public List<ITEM> getData() {
        return mData;
    }

    @NonNull
    @Override
    public XHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for(int i=0;i<getItemCount();i++){
            if(viewType == mData.get(i).getItemType()){
                return mData.get(i).onCreateViewHolder(parent,viewType);
            }
        }
        throw new RuntimeException("BaseAdapter - Wrong ViewType");
    }

    @Override
    public void onBindViewHolder(@NonNull XHolder holder, int position) {
        int p = getRealPosition(position);
        mData.get(p).onBindViewHolder(holder,position);
        onViewHolderBound(holder,p);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull XHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //释放资源
        int position = holder.getAdapterPosition();
        //越界检查
        if(position<0 || position>=mData.size()){
            return;
        }
        mData.get(position).releaseRes();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealItemCount() {
        return  mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(getRealPosition(position)).getItemType();
    }

    public int getRealPosition(int position) {
        return position % mData.size();
    }

    /**
     * add one ITEM
     * @param item ITEM
     */
    public void add(ITEM item){
        mData.add(item);
        int index = mData.indexOf(item);
        notifyItemChanged(index);
    }

    public void add(int index,ITEM item){
        mData.add(index,item);
        notifyItemChanged(index);
    }

    /**
     * remove a ITEM
     * @param item ITEM
     */
    public void remove(ITEM item){
        int indexOfCell = mData.indexOf(item);
        remove(indexOfCell);
    }

    public void remove(int index){
        mData.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * remove some items
     * @param start index begin
     * @param count the num of items
     */
    public void remove(int start,int count){
        if((start +count) > mData.size()){
            return;
        }
        int size = getItemCount();
        for(int i =start;i<size;i++){
            mData.remove(i);
        }
        notifyItemRangeRemoved(start,count);
    }

    /**
     * add a item list
     * @param items items
     */
    public void addAll(List<ITEM> items){
        if(items == null || items.size() == 0){
            return;
        }
        mData.addAll(items);
        notifyItemRangeChanged(mData.size()-items.size(),mData.size());
    }

    public void addAll(int index, List<ITEM> items){
        if(items == null || items.size() == 0){
            return;
        }
        mData.addAll(index,items);
        notifyItemRangeChanged(index,index+items.size());
    }

    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
     * @param holder holder
     * @param position position
     */
    protected abstract void onViewHolderBound(XHolder holder, int position);
}
