package x.chestnut.code.snippet.anim.countNumberView

import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/11/8 16:10
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CountNumberViewFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_count_number_view
    }

    override val actionBarTitle: String
        get() = "CountNumberView"

    override fun onViewCreate(rootView: View) {
        super.onViewCreate(rootView)
        var countNumberView: CountNumberView = rootView.findViewById(R.id.tv_1)
        countNumberView.showNumberWithAnimation(3201.23f, CountNumberView.FLOATREGEX)
        countNumberView = rootView.findViewById(R.id.tv_2)
        countNumberView.setDuration(5000)
        countNumberView.showNumberWithAnimation(164813f, CountNumberView.INTREGEX)
    }
}