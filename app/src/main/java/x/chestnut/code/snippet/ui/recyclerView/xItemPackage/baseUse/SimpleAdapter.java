package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XAdapter;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder;

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
public class SimpleAdapter extends XAdapter {

    public static final int Item_Img = 1;
    public static final int Item_Txt = 2;
    public static final int Item_Scroll_Img = 3;
    public static final int Item_Img_Txt = 4;

    @Override
    protected void onViewHolderBound(XHolder holder, int position) {

    }
}
