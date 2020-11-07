package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import android.view.LayoutInflater;
import android.view.ViewGroup;
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
public class ItemTxt extends XItem<String> {

    public ItemTxt(String s) {
        super(s);
    }

    @Override
    public XHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_txt, parent,false));
    }

    @Override
    public void onBindViewHolder(XHolder holder, int position) {
        TextView textView = (TextView) holder.getViewById(R.id.tv);
        textView.setText(data);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.Item_Txt;
    }

    @Override
    public void releaseRes() {

    }
}
