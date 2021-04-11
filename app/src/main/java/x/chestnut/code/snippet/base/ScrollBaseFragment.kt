package x.chestnut.code.snippet.base

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.flexbox.FlexboxLayout
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.utils.ConvertUtils

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:25
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class ScrollBaseFragment : BaseFragment() {

    protected fun addView(content: CharSequence?,
                          onClickListener: View.OnClickListener?
    ): TextView {
        val mRootView = mMinView
        val textView = TextView(context)
        if (mRootView != null) {
            val flexboxLayout = mRootView.findViewById<View>(R.id.flx) as FlexboxLayout
            textView.background = ResourcesCompat.getDrawable(resources,
                    R.drawable.tv_bg_round, null)
            textView.text = content
            textView.gravity = Gravity.CENTER
            val padding = ConvertUtils.dp2px(mRootView.context, 10f)
            textView.setPadding(padding, padding, padding, padding)
            flexboxLayout.addView(textView)
            val margin = ConvertUtils.dp2px(mRootView.context, 5f)
            val marginTopBottom = ConvertUtils.dp2px(mRootView.context, 5f)
            val layoutParams = textView.layoutParams as FlexboxLayout.LayoutParams
            layoutParams.setMargins(margin, marginTopBottom, margin, marginTopBottom)
            textView.tag = content
            if (onClickListener != null) {
                textView.isFocusable = true
                textView.isClickable = true
                textView.setOnClickListener(onClickListener)
            }
        }
        return textView
    }

    protected fun addView(@StringRes strId: Int,
                          onClickListener: View.OnClickListener?
    ): TextView {
        return addView(getString(strId), onClickListener)
    }

    override fun setContentView(): Int {
        return R.layout.layout_scroll
    }
}