package x.chestnut.code.snippet.widget.wheelView

import android.view.View
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import java.util.*

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/5 21:57
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class WheelViewFragment : BaseFragment() {

    override val actionBarTitle: String
        get() = "WheelView"

    override fun setContentView(): Int {
        return R.layout.fragment_wheel_view
    }

    override fun onLazyViewCreate(rootView: View) {
        val tvSelect = rootView.findViewById(R.id.tv_select) as TextView

        val dateList = ArrayList<String>()
        for (i in 0..31) {
            dateList.add(i.toString())
        }
        val wheelView = rootView.findViewById(R.id.wheel_view) as WheelView
        wheelView.setItems(dateList)
        wheelView.setSelection(2)
        wheelView.onWheelViewListener = object : WheelView.OnWheelViewListener {
            override fun onSelected(selectedIndex: Int, item: String?) {
                tvSelect.text = "$selectedIndex : $item"
            }
        }
    }
}