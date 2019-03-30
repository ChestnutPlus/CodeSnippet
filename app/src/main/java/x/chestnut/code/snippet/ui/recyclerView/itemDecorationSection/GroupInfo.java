package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/30 15:37
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class GroupInfo {
    //Header的Id
    public int groupId;
    //Header的标题
    public String groupTitle;
    //ItemView 在组内的位置
    public int positionInGroup;
    // 组的成员个数
    public int groupSize;

    public boolean isFirstViewInGroup () {
        return positionInGroup == 0;
    }

    public boolean isLastViewInGroup() {
        return positionInGroup == groupSize - 1 && positionInGroup >= 0;
    }
}
