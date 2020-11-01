package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ItemImgTxt extends XItem<Integer> {

    private String content;

    public ItemImgTxt(Integer integer, String content) {
        super(integer);
        this.content = content;
    }

    @Override
    public XHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent,false));
    }

    @Override
    public void onBindViewHolder(XHolder holder, int position) {
        ImageView imageView = (ImageView) holder.getViewById(R.id.img);
        imageView.setImageResource(data);
        TextView textView = (TextView) holder.getViewById(R.id.tv);
        textView.setText(content);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.Item_Img_Txt;
    }

    @Override
    public void releaseRes() {

    }
}
