package x.chestnut.code.snippet.other

import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.TextView
import x.chestnut.code.snippet.BuildConfig
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.other.asyncTask.AsyncTaskExampleFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/3/25 22:55
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class OtherExampleFragment : ScrollBaseFragment() {

    companion object {
        private val TAG = OtherExampleFragment::class.java.name
    }

    private var mIsShowAllContent = false
    private var mTips: TextView? = null
    private var mTvContent: TextView? = null

    override val actionBarTitle: String
        get() = "Other Example"

    override fun onLazyViewCreate(rootView: View) {
        addView("AsyncTask") { startFragment(AsyncTaskExampleFragment()) }
        addView("GradleTest") {
            Log.e(TAG, "gradleInfo, name: " + BuildConfig.NAME)
            Log.e(TAG, "gradleInfo, is_debug: " + BuildConfig.IS_DEBUG)
            try {
                val applicationInfo = context?.packageManager?.getApplicationInfo(
                        BuildConfig.APPLICATION_ID, PackageManager.GET_META_DATA)
                val result = applicationInfo?.metaData?.getString("NAME", "not found")
                Log.e(TAG, "result: $result")
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }
        //字符串通配符
        // 1. %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格
        // 2. %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0
        // 3. %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.3时，
        //           代表的是在输出之前放置2个空格，且小数点后四舍五入保留3位。
        val userName = "chestnut"
        val mailCount = 3
        addView(getString(R.string.str_1, userName, mailCount), null)
        addView(getString(R.string.str_2, userName, mailCount), null)
        addView(getString(R.string.str_3, 312.365f, 50f), null)
        addView(getString(R.string.str_3, 21212.365f, 50000.128534f), null)
        val showAllStr = "--查看全部--"
        val showLittleStr = "---收起---"
        mTips = addView(if (mIsShowAllContent) showLittleStr else showAllStr) {
            if (mIsShowAllContent) {
                mTvContent?.maxLines = 1
                mTips?.text = showAllStr
            } else {
                mTvContent?.maxLines = 200
                mTips?.text = showLittleStr
            }
            mIsShowAllContent = !mIsShowAllContent
        }
        mTvContent = addView(R.string.long_text, null)
        mTvContent?.maxLines = if (mIsShowAllContent) 200 else 1
    }
}