package x.chestnut.code.snippet.widget.radarView

import android.view.View
import android.widget.RelativeLayout
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment
import java.util.*

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/5 16:07
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class RadarViewFragment : ScrollBaseFragment() {

    override val actionBarTitle: String
        get() = "雷达图"

    override fun onLazyViewCreate(rootView: View) {
        val random = Random()
        val radarDataBeans: MutableList<RadarDataBean> = ArrayList()
        for (i in 0..4) {
            val radarDataBean = RadarDataBean(random.nextInt(100) * 0.1f)
            radarDataBean.name = "a$i"
            radarDataBeans.add(radarDataBean)
        }

        val radarView = RadarView(rootView.context, null)
        radarView.setData(radarDataBeans)

        val layout = rootView.findViewById<RelativeLayout>(R.id.layout)
        val layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                600, 600)
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        radarView.layoutParams = layoutParams
        layout.addView(radarView, 0)
    }
}