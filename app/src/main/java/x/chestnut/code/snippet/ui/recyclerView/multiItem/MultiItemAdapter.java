package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.recyclerView.scrollImg.ItemScrollImgBean;
import x.chestnut.code.snippet.ui.recyclerView.scrollImg.ViewHolderScrollImgItem;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/29 22:08
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class MultiItemAdapter extends RecyclerView.Adapter {

    //item的类型
    public static final int ITEM_IMG = 1;
    public static final int ITEM_TV = 2;
    public static final int ITEM_IMG_TV = 3;
    public static final int ITEM_Scroll_IMG_TV = 4;

    //数据源
    private List<IMultiType> mList;

    private RecyclerView recyclerView;

    public MultiItemAdapter(List<IMultiType> list, RecyclerView recyclerView) {
        mList = list;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View item;
        switch (viewType) {
            case ITEM_IMG:
                item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_img, viewGroup, false);
                holder = new ViewHolderItemImg(item);
                break;
            case ITEM_TV:
                item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_txt, viewGroup, false);
                holder = new ViewHolderItemTxt(item);
                break;
            case ITEM_IMG_TV:
                item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
                holder = new ViewHolderItemImgTxt(item);
                break;
            case ITEM_Scroll_IMG_TV:
                item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_scroll_img, viewGroup, false);
                holder = new ViewHolderScrollImgItem(item, recyclerView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == ITEM_IMG) {

            ItemImgBean itemImgBean = (ItemImgBean) mList.get(i);
            ViewHolderItemImg viewHolderItemImg = (ViewHolderItemImg) viewHolder;
            viewHolderItemImg.setImageView(itemImgBean.bgRes);

        }
        else if (viewHolder.getItemViewType() == ITEM_TV) {

            ItemTxtBean itemTxtBean = (ItemTxtBean) mList.get(i);
            ViewHolderItemTxt viewHolderItemTxt = (ViewHolderItemTxt) viewHolder;
            viewHolderItemTxt.setContent(itemTxtBean.content);

        }
        else if (viewHolder.getItemViewType() == ITEM_IMG_TV) {

            ItemImgTxtBean itemImgTxtBean = (ItemImgTxtBean) mList.get(i);
            ViewHolderItemImgTxt viewHolderItemImgTxt = (ViewHolderItemImgTxt) viewHolder;
            viewHolderItemImgTxt.setContent(itemImgTxtBean.content);
            viewHolderItemImgTxt.setImageView(itemImgTxtBean.bgRes);

        }
        else if (viewHolder.getItemViewType() == ITEM_Scroll_IMG_TV) {

            ItemScrollImgBean itemScrollImgBean = (ItemScrollImgBean) mList.get(i);
            ViewHolderScrollImgItem scrollImgItem = (ViewHolderScrollImgItem) viewHolder;
            scrollImgItem.setPosition(i);
            scrollImgItem.setImageView(itemScrollImgBean.bgRes);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();
    }
}
