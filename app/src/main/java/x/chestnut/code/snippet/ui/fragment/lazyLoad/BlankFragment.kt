package x.chestnut.code.snippet.ui.fragment.lazyLoad

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import java.lang.ref.WeakReference

class BlankFragment : BaseFragment() {

    /*声明传入的参数*/
    private val TAG = "BlankFragment"
    private var mParam1: String? = null
    private var textView: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun setContentView(): Int {
        return R.layout.fragment_blank
    }

    override fun onViewCreate(rootView: View) {
        mParam1 = arguments?.getString(ARG_PARAM1)
        Log.i(TAG, "$mParam1, onViewCreate")
        textView = rootView.findViewById(R.id.tv_index)
        progressBar = rootView.findViewById(R.id.progress)
    }

    override fun onViewResume() {
        Log.i(TAG, "$mParam1, onViewResume")
        progressBar?.visibility = View.VISIBLE
        MockMetAsyncTask(this).execute(mParam1)
    }

    override fun onViewPause() {
        Log.i(TAG, "$mParam1, onViewPause")
    }

    override fun onViewDestroy() {
        Log.i(TAG, "$mParam1, onViewDestroy")
    }

    private fun updateView() {
        progressBar?.visibility = View.INVISIBLE
        textView?.text = mParam1
    }

    /**
     * 用于模拟网络耗时操作
     */
    private class MockMetAsyncTask(blankFragment: BlankFragment?)
        : AsyncTask<String?, Void?, String>() {

        private val weakReference: WeakReference<BlankFragment> = WeakReference(blankFragment)

        override fun doInBackground(vararg strings: String?): String {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return ""
        }

        override fun onPostExecute(s: String) {
            weakReference.get()?.updateView()
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        /**
         * 使用这个方法进行实例化
         * @param param1 参数
         * @return fragment
         */
        fun newInstance(param1: String?): BlankFragment {
            val fragment = BlankFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}