package x.chestnut.code.snippet.ui.fragment.bottomTab

import android.view.View
import androidx.fragment.app.Fragment
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseActivity

class BottomTabFragmentActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_bottom_tab_fragment

    override fun lazyLoadViewAfterOnResume() {
        val fragment1 = TextFragment.newInstance("微信")
        val fragment2 = TextFragment.newInstance("通讯录")
        val fragment3 = TextFragment.newInstance("发现")
        val fragment4 = TextFragment.newInstance("我")

        //init
        addFragment(fragment1)
        showFragment(fragment1)
        findViewById<View>(R.id.tv_1).setOnClickListener {
            addFragment(fragment1)
            showFragment(fragment1)
        }
        findViewById<View>(R.id.tv_2).setOnClickListener {
            addFragment(fragment2)
            showFragment(fragment2)
        }
        findViewById<View>(R.id.tv_3).setOnClickListener {
            addFragment(fragment3)
            showFragment(fragment3)
        }
        findViewById<View>(R.id.tv_4).setOnClickListener {
            addFragment(fragment4)
            showFragment(fragment4)
        }
    }

    /*添加fragment*/
    private fun addFragment(fragment: Fragment) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout,
                    fragment, fragment.javaClass.simpleName).commit()
        }
    }

    /*显示fragment*/
    private fun showFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        for (frag in supportFragmentManager.fragments) {
            if (frag !== fragment) {
                /*先隐藏其他fragment*/
                ft.hide(frag)
            } else {
                ft.show(fragment)
            }
        }
        ft.commit()
    }
}