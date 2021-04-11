package x.chestnut.code.snippet.contract

import dagger.hilt.android.AndroidEntryPoint
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseActivity
import x.chestnut.code.snippet.base.BaseFragment

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun lazyLoadViewAfterOnResume() {
        BaseFragment.startFragment(this, R
                .id.frame_layout, MainFragment(), false)
    }
}