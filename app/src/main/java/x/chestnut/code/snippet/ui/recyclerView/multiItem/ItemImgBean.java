package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import android.support.annotation.DrawableRes;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/29 22:06
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ItemImgBean implements IMultiType{
    @DrawableRes
    public int bgRes;
    @Override
    public int getItemType() {
        return MultiItemAdapter.ITEM_IMG;
    }
}
