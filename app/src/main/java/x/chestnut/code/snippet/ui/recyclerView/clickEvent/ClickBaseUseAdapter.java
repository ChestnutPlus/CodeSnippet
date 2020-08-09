package x.chestnut.code.snippet.ui.recyclerView.clickEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 23:21
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ClickBaseUseAdapter extends RecyclerView.Adapter<ClickBaseUseHolder> {

    private List<BaseUseBean> baseUseBeanList;
    private OnItemClickListener mOnItemClickListener;

    public ClickBaseUseAdapter(List<BaseUseBean> baseUseBeanList) {
        this.baseUseBeanList = baseUseBeanList;
    }

    @NonNull
    @Override
    public ClickBaseUseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ClickBaseUseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickBaseUseHolder baseUseHolder, int i) {
        BaseUseBean useBean = baseUseBeanList.get(i);
        baseUseHolder.setImageView(useBean.bgRes);
        baseUseHolder.setContent(useBean.content);
        if (mOnItemClickListener != null) {
            baseUseHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,i,useBean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return baseUseBeanList.size();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
