package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/30 15:37
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class GroupInfo {

    //Header的Id
    var groupId = 0

    //Header的标题
    var groupTitle: String = ""

    //ItemView 在组内的位置
    var positionInGroup = 0

    // 组的成员个数
    var groupSize = 0
    val isFirstViewInGroup: Boolean
        get() = positionInGroup == 0
    val isLastViewInGroup: Boolean
        get() = positionInGroup == groupSize - 1 && positionInGroup >= 0
}