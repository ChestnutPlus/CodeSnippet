package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemImg
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemTxt
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/29 21:50
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CircleRecyclerViewFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_circle_recycler_view
    }

    override fun onLazyViewCreate(rootView: View) {
        val recyclerView = rootView
                .findViewById<View>(R.id.recycler_view) as CircleRecyclerView
        recyclerView.setNeedLoop(true)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setDefaultSelected(12, 4)
        val linearSnapHelper = LinearSnapHelper()
        linearSnapHelper.attachToRecyclerView(recyclerView)
        val circleSimpleAdapter = CircleSimpleAdapter()
        recyclerView.adapter = circleSimpleAdapter
        circleSimpleAdapter.addAll(beans)
    }

    val beans: List<XItem<*>>
        get() {
            val beans: MutableList<XItem<*>> = ArrayList()
            val bgRes = intArrayOf(
                    R.drawable.girl_1,
                    R.drawable.girl_2,
                    R.drawable.girl_3,
                    R.drawable.girl_4,
                    R.drawable.girl_5,
                    R.drawable.girl_6)
            val contents = arrayOf(
                    "1. 你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                    "2. 青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                    "3. 留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                    "4. 一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                    "5. 西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                    "6. 当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
            )
            for (i in bgRes.indices) {
                val itemTxt = ItemTxt(contents[i % contents.size])
                beans.add(itemTxt)
                val itemImg = ItemImg(bgRes[i % bgRes.size])
                beans.add(itemImg)
            }
            return beans
        }
}