package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem
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
class XItemBaseUseFragment : BaseFragment() {

    private var recyclerView: RecyclerView? = null

    override fun setContentView(): Int {
        return R.layout.layout_recycler_view
    }

    override fun onLazyViewCreate(rootView: View) {
        recyclerView = rootView.findViewById<View>(R.id.recycler_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = linearLayoutManager
        val simpleAdapter = SimpleAdapter()
        recyclerView?.adapter = simpleAdapter
        simpleAdapter.addAll(beans)
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
                    "你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                    "青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                    "留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                    "一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                    "西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                    "当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
            )
            val longBgRes = intArrayOf(
                    R.drawable.long_0,
                    R.drawable.long_1,
                    R.drawable.long_2,
                    R.drawable.long_3,
                    R.drawable.long_4)
            val random = Random(System.currentTimeMillis())
            for (i in 0..49) {
                when (random.nextInt(100) % 4) {
                    0 -> {
                        val itemImg = ItemImg(bgRes[i % bgRes.size])
                        beans.add(itemImg)
                    }
                    1 -> {
                        val itemTxt = ItemTxt(contents[i % contents.size])
                        beans.add(itemTxt)
                    }
                    2 -> {
                        val itemImgTxt = ItemImgTxt(bgRes[i % bgRes.size],
                                contents[i % contents.size])
                        beans.add(itemImgTxt)
                    }
                    3 -> {
                        recyclerView?.let {
                            val itemScrollImg = ItemScrollImg(
                                    longBgRes[i % longBgRes.size], it)
                            beans.add(itemScrollImg)
                        }
                    }
                }
            }
            return beans
        }
}