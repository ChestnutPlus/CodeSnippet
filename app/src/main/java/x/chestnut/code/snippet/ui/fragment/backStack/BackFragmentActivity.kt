package x.chestnut.code.snippet.ui.fragment.backStack

import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseActivity
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.ui.fragment.backStack.BackFragment.Companion.newInstance

class BackFragmentActivity : BaseActivity(), BackFragment.OnClickListener {

    /**
     * 有一个小问题，
     * 这里多次添加后，不会
     * 调用 Fragment 的 onViewPause()
     * 具体可以看log
     */
    private val Param1 = "one"
    private val Param2 = "two"
    private val Param3 = "three"
    override val layoutId: Int
        get() = R.layout.activity_back_fragment

    override fun lazyLoadViewAfterOnResume() {
        BaseFragment.startFragment(this, R.id.frame_layout,
                newInstance(Param1), false)
    }

    override fun onClick(param: String?) {
        when (param) {
            Param1 -> BaseFragment.startFragment(this,
                    R.id.frame_layout, newInstance(Param2))
            Param2 -> BaseFragment.startFragment(this,
                    R.id.frame_layout, newInstance(Param3))
            Param3 -> {
            }
        }
    }
}