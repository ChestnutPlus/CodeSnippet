package x.chestnut.code.snippet.ui.recyclerView.scrollImg;

import x.chestnut.code.snippet.ui.recyclerView.multiItem.IMultiType;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.ItemImgBean;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemAdapter;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/30 23:51
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ItemScrollImgBean extends ItemImgBean implements IMultiType {
    @Override
    public int getItemType() {
        return MultiItemAdapter.ITEM_Scroll_IMG_TV;
    }
}
