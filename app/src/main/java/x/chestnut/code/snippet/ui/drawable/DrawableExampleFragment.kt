package x.chestnut.code.snippet.ui.drawable

import android.view.View
import x.chestnut.code.snippet.base.ScrollBaseFragment
import x.chestnut.code.snippet.ui.drawable.roundedBitmapDrawable.RoundDrawableFragment

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/4/2 23:39
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class DrawableExampleFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        addView("RoundDrawable") { startFragment(RoundDrawableFragment()) }
    }

    override val actionBarTitle: String
        get() = "Drawable示例"
}