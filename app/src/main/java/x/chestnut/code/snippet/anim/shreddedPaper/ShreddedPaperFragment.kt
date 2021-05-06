package x.chestnut.code.snippet.anim.shreddedPaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.BarUtils
import x.chestnut.code.snippet.utils.ScreenUtils

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/7 0:10
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class ShreddedPaperFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_anim_shredded_paper
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        BarUtils.hideStatusBar(this.activity)
        BarUtils.hideActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        BarUtils.showStatusBar(this.activity)
        BarUtils.showActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, false)
        super.onDestroyView()
    }

    override fun onLazyViewCreate(rootView: View) {
        val layout = rootView.findViewById(R.id.layout) as RelativeLayout
        val shreddedPaperView = ShreddedPaperView(rootView.context)
        rootView.findViewById<TextView>(R.id.tv_content).setOnClickListener {
            view -> shreddedPaperView.startAnim(layout, view as TextView)
        }
        rootView.findViewById<TextView>(R.id.tv_content1).setOnClickListener {
            view -> shreddedPaperView.startAnim(layout, (view as TextView))
        }
    }
}
