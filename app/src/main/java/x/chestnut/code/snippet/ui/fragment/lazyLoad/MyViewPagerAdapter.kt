package x.chestnut.code.snippet.ui.fragment.lazyLoad

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/26 17:16
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MyViewPagerAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) :
        FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
}