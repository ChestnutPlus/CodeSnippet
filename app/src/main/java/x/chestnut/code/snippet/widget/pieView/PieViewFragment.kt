package x.chestnut.code.snippet.widget.pieView

import android.view.View
import android.widget.RelativeLayout
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment
import java.util.*

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/5 11:48
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class PieViewFragment : ScrollBaseFragment() {

    override val actionBarTitle: String
        get() = "饼图"

    override fun onLazyViewCreate(rootView: View) {
        val layout = rootView.findViewById<RelativeLayout>(R.id.layout)
        val pieView = PieView(rootView.context)
        val pieData = PieData("1", 0.12f, 0.1f)
        val pieData1 = PieData("1", 0.12f, 0.2f)
        val pieData2 = PieData("1", 0.12f, 0.4f)
        val pieData3 = PieData("1", 0.12f, 0.3f)
        val pieDataList: MutableList<PieData> = ArrayList()
        pieDataList.add(pieData)
        pieDataList.add(pieData1)
        pieDataList.add(pieData2)
        pieDataList.add(pieData3)
        pieView.setData(pieDataList)
        val layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                400, 400)
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        pieView.setLayoutParams(layoutParams)
        layout.addView(pieView, 0)
    }
}
