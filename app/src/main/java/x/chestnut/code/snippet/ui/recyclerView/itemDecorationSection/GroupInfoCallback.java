package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/30 15:41
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public interface GroupInfoCallback {
    /**
     * 获取，对应 position 位置的
     * GroupInfo分组信息
     * @param position 位置
     * @return 信息
     */
    GroupInfo getGroupInfo(int position);
}
