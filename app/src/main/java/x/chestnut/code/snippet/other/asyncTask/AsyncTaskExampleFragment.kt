package x.chestnut.code.snippet.other.asyncTask

import android.os.AsyncTask
import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/3/25 23:00
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class AsyncTaskExampleFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        addView("普通用法") {
            val myAsyncTask = MyAsyncTask()
            myAsyncTask.execute("strings")
            activity?.window?.decorView?.postDelayed({
                myAsyncTask.cancel(true)
            }, 1000)
        }
        addView("并行执行任务") {
            val myAsyncTask = MyAsyncTask()
            myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "strings")
        }
    }

    override val actionBarTitle: String
        get() = "AsyncTask"
}