package x.chestnut.code.snippet.ui.recyclerView.baseUse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import x.chestnut.code.snippet.R;

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

public class BaseUseAdapter extends RecyclerView.Adapter<BaseUseHolder> {

    private List<BaseUseBean> baseUseBeanList;

    public BaseUseAdapter(List<BaseUseBean> baseUseBeanList) {
        this.baseUseBeanList = baseUseBeanList;
    }

    @NonNull
    @Override
    public BaseUseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new BaseUseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseUseHolder baseUseHolder, int i) {
        BaseUseBean useBean = baseUseBeanList.get(i);
        baseUseHolder.setImageView(useBean.bgRes);
        baseUseHolder.setContent(useBean.content);
    }

    @Override
    public int getItemCount() {
        return baseUseBeanList.size();
    }
}
