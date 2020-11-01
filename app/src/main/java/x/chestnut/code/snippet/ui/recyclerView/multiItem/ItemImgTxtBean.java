package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import androidx.annotation.DrawableRes;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/29 22:07
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ItemImgTxtBean implements IMultiType{
    public String content;
    @DrawableRes
    public int bgRes;
    @Override
    public int getItemType() {
        return MultiItemAdapter.ITEM_IMG_TV;
    }
}
