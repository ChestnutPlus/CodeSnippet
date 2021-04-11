package x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseAdapter
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/27 23:09
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class ItemDecorationSectionFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.layout_recycler_view
    }

    override fun onLazyViewCreate(rootView: View) {
        val recyclerView = rootView.findViewById<View>(R.id.recycler_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        val baseAdapter = BaseUseAdapter(beans)
        val groupInfoCallback = object : GroupInfoCallback {
            override fun getGroupInfo(position: Int): GroupInfo? {
                /*
                            * 分组逻辑，这里为了测试每5个数据为一组。大家可以在实际开发中
                            * 替换为真正的需求逻辑
                            */
                val groupInfo = GroupInfo()
                groupInfo.groupId = position / 5
                groupInfo.groupTitle = groupInfo.groupId.toString() + ""
                groupInfo.positionInGroup = position % 5
                return groupInfo
            }
        }
        val sectionItemDecoration = SectionItemDecoration(
                groupInfoCallback, 100, 2)
        recyclerView.addItemDecoration(sectionItemDecoration)
        recyclerView.adapter = baseAdapter
    }

    val beans: List<BaseUseBean>
        get() {
            val beans: MutableList<BaseUseBean> = ArrayList()
            val bgRes = intArrayOf(
                    R.drawable.girl_1,
                    R.drawable.girl_2,
                    R.drawable.girl_3,
                    R.drawable.girl_4,
                    R.drawable.girl_5,
                    R.drawable.girl_6)
            val contents = arrayOf(
                    "你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                    "青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                    "留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                    "一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                    "西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                    "当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
            )
            for (j in 0..4) {
                for (i in bgRes.indices) {
                    val baseUseBean = BaseUseBean()
                    baseUseBean.bgRes = bgRes[i]
                    baseUseBean.content = contents[i]
                    beans.add(baseUseBean)
                }
            }
            return beans
        }
}