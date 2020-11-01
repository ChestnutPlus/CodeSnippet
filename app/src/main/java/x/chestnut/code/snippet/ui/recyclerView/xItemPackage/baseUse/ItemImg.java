package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class ItemImg extends XItem<Integer> {

    private int height = 0;
    private int width = 0;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ItemImg(Integer integer) {
        super(integer);
    }

    @Override
    public XHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_img, parent,false));
    }

    @Override
    public void onBindViewHolder(XHolder holder, int position) {
        ImageView imageView = (ImageView) holder.getViewById(R.id.img);
        imageView.setImageResource(data);
        if (height!=0 && width!=0) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = width;
            imageView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.Item_Img;
    }

    @Override
    public void releaseRes() {

    }
}
