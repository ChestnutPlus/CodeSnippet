package x.chestnut.code.snippet.ui.fragment.backStack

import android.content.Context
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
 * time  : 2019/3/27 18:21
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class BackFragment : BaseFragment() {

    /**
     * 这个接口由Activity实现，就可以进行通信
     * 注意，是在 onAttach 和 onDetach
     * 中注册监听和销毁监听
     */
    interface OnClickListener {
        fun onClick(param: String?)
    }

    private val TAG = "BackFragment"
    private var mParam1: String? = null
    private var onClickListener: OnClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is OnClickListener) onClickListener = activity as OnClickListener?
    }

    override fun onDetach() {
        super.onDetach()
        onClickListener = null
    }

    override fun setContentView(): Int {
        return R.layout.fragment_back
    }

    override fun onViewCreate(rootView: View) {
        val tv = rootView.findViewById<TextView>(R.id.tv)
        tv.text = mParam1
        rootView.findViewById<View>(R.id.btn).setOnClickListener {
            onClickListener?.onClick(mParam1)
        }
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
        fun newInstance(param1: String?): BackFragment {
            val fragment = BackFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}