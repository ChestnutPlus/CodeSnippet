package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemImg
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.SimpleAdapter
import java.util.*

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/3 9:48
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CenterScaleFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_center_scale_recycler_view
    }

    override fun onLazyViewCreate(rootView: View) {
        val recyclerView = rootView
                .findViewById<View>(R.id.recycler_view) as CenterScaleRecyclerView
        val linearLayoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val simpleAdapter = SimpleAdapter()
        recyclerView.adapter = simpleAdapter
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
            val random = Random(System.currentTimeMillis())
            for (i in 0..49) {
                when (random.nextInt(100) % 4) {
                    0 -> {
                        val itemImg = ItemImg(bgRes[i % bgRes.size])
                        itemImg.setHeight(200)
                        itemImg.setWidth(200)
                        beans.add(itemImg)
                    }
                }
            }
            return beans
        }
}