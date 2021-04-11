package x.chestnut.code.snippet.other.asyncTask

import android.os.AsyncTask
import android.util.Log

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/3/25 23:01
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MyAsyncTask : AsyncTask<String?, Int?, Boolean>() {

    override fun doInBackground(vararg params: String?): Boolean {
        Log.d(TAG, "doInBackground: " + Thread.currentThread().name)
        //模拟耗时的操作
        val i = 0
        for (j in 0..99) {
            if (isCancelled) {
                break
            }
            try {
                Thread.sleep(200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        return true
    }

    override fun onCancelled() {
        super.onCancelled()
        Log.d(TAG, "onCancelled: " + Thread.currentThread().name)
    }

    companion object {
        private const val TAG = "MyAsyncTask"
    }
}