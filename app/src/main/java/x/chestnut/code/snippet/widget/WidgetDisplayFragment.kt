package x.chestnut.code.snippet.widget

import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.widget.pieView.PieViewFragment
import x.chestnut.code.snippet.widget.radarView.RadarViewFragment
import x.chestnut.code.snippet.widget.wheelView.WheelViewFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/5 11:39
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class WidgetDisplayFragment : ScrollBaseFragment() {

    override val actionBarTitleId: Int
        get() = R.string.title_widget

    override fun onViewCreate(rootView: View) {
        addView("饼图") { startFragment(PieViewFragment()) }
        addView("雷达图") { startFragment(RadarViewFragment()) }
        addView("WheelView") { startFragment(WheelViewFragment()) }
    }
}