package x.chestnut.code.snippet.base

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import x.chestnut.code.snippet.R

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2018/12/24 15:33
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class BaseFragment : Fragment() {

    protected var mMinView: View? = null
    private var mIsIncludeByViewPager = false
    private var mIsVisible = false
    private var mIsOnResume = false

    /*标识是否已经走过：onCreateView*/
    private var mIsInitView = false

    /*标记是否已经回调过：onViewPause*/
    private var mIsPause = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(setContentView(), container, false)
        mMinView = rootView
        mIsIncludeByViewPager = container is ViewPager
        onViewCreate(rootView)
        mIsInitView = true
        Looper.myQueue().addIdleHandler {
            onLazyViewCreate(rootView)
            false
        }
        return rootView
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            onViewPause()
        } else {
            onViewResume()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mIsVisible = true
            mIsPause = false
            isCanOnResumeByViewPager
        } else {
            mIsVisible = false
            if (mIsInitView && !mIsPause) {
                mIsPause = true
                onViewPause()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (!isHidden && !mIsIncludeByViewPager) onViewPause()
    }

    override fun onResume() {
        super.onResume()
        if (!isHidden && !mIsIncludeByViewPager) onViewResume()
        if (mIsIncludeByViewPager) {
            mIsOnResume = true
            isCanOnResumeByViewPager
        }
    }

    private val isCanOnResumeByViewPager: Unit
        get() {
            if (mIsOnResume && mIsVisible) {
                onViewResume()
                mIsOnResume = false
                mIsVisible = false
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        onViewDestroy()
    }

    protected abstract fun setContentView(): Int
    protected open fun onViewCreate(rootView: View) {}
    protected open fun onLazyViewCreate(rootView: View) {}
    protected open fun onViewResume() {
        val title = actionBarTitle
        if (!TextUtils.isEmpty(title)) {
            setTitle(title)
        } else {
            val id = actionBarTitleId
            if (id != -1) {
                setTitle(getString(id))
            }
        }
    }

    protected open fun onViewPause() {}
    protected open fun onViewDestroy() {}
    protected open val actionBarTitle: String?
        get() = null

    @get:StringRes
    protected open val actionBarTitleId: Int
        get() = -1

    protected fun startActivity(c: Class<*>?) {
        startActivity(Intent(activity, c))
    }

    protected fun startFragment(fragment: Fragment, isAddToBackStack: Boolean = true) {
        startFragment(activity, R.id.frame_layout, fragment, isAddToBackStack)
    }

    private fun setTitle(title: String?) {
        if (activity is AppCompatActivity) {
            val appCompatActivity = activity as AppCompatActivity?
            val actionBar = appCompatActivity?.supportActionBar
            if (actionBar != null) {
                actionBar.title = title
            }
        }
    }

    companion object {
        fun startFragment(fragmentActivity: FragmentActivity?,
                          @IdRes frameLayout: Int,
                          fragment: Fragment, isAddBackStack: Boolean = true) {
            /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
            if (!fragment.isAdded) {
                val fragmentTransaction = fragmentActivity?.supportFragmentManager
                        ?.beginTransaction()
                fragmentTransaction?.let {
                    if (isAddBackStack) it.addToBackStack(null)
                    it.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    it.add(frameLayout, fragment, fragment.javaClass.simpleName)
                    val list = fragmentActivity.supportFragmentManager.fragments
                    for (i in list.indices) {
                        val f = list[i]
                        if (f !== fragment && !f.isHidden) {
                            it.hide(f)
                        }
                    }
                    it.commit()
                }
            }
        }
    }
}