package x.chestnut.code.snippet.ui.fragment.bottomTab

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/27 16:06
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class TextFragment : BaseFragment() {

    private val TAG = "TextFragment"
    private var mParam1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
    }

    override fun setContentView(): Int {
        return R.layout.fragment_blank
    }

    override fun onViewCreate(rootView: View) {
        val textView = rootView.findViewById<TextView>(R.id.tv_index)
        textView.text = mParam1
        Log.i(TAG, "onViewCreate, $mParam1")
    }

    override fun onViewResume() {
        Log.i(TAG, "onViewResume, $mParam1")
    }

    override fun onViewPause() {
        Log.i(TAG, "onViewPause, $mParam1")
    }

    override fun onViewDestroy() {
        Log.i(TAG, "onViewDestroy, $mParam1")
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: String?): TextFragment {
            val fragment = TextFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}