package x.chestnut.code.snippet.ui.fragment.lazyLoad

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseActivity
import x.chestnut.code.snippet.ui.fragment.lazyLoad.BlankFragment.Companion.newInstance
import java.util.*

class FragmentLazyLoadActivity : BaseActivity() {

    override val layoutId: Int
        get() {
            setTitle("FragmentLazyLoad")
            return R.layout.activity_fragment_lazy_load
        }

    override fun lazyLoadViewAfterOnResume() {
        val list: MutableList<Fragment>
        list = ArrayList()
        list.add(newInstance("One"))
        list.add(newInstance("Two"))
        list.add(newInstance("Three"))
        val myAdapter = MyViewPagerAdapter(supportFragmentManager, list)
        val viewPager: ViewPager? = findViewById<View>(R.id.view_pager) as? ViewPager
        viewPager?.adapter = myAdapter
        viewPager?.currentItem = 0
    }
}