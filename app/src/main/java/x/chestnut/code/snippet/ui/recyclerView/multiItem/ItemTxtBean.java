package x.chestnut.code.snippet.ui.recyclerView.multiItem;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/29 22:07
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ItemTxtBean implements IMultiType{
    public String content;
    @Override
    public int getItemType() {
        return MultiItemAdapter.ITEM_TV;
    }
}
