package x.chestnut.code.snippet.ui.window

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import x.chestnut.code.snippet.base.ScrollBaseFragment

class WindowInsetsFragment : ScrollBaseFragment() {

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)
        val controller = ViewCompat.getWindowInsetsController(rootView)
        //控制状态栏
        addView("显示状态栏") {
            controller?.show(WindowInsetsCompat.Type.statusBars())
        }
        addView("隐藏状态栏") {
            controller?.hide(WindowInsetsCompat.Type.statusBars())
        }
        addView("状态栏文字颜色改为黑色") {
            controller?.isAppearanceLightStatusBars = true
        }
        addView("状态栏文字颜色改为白色") {
            controller?.isAppearanceLightStatusBars = false
        }
        //控制导航栏
        addView("显示导航栏") {
            controller?.show(WindowInsetsCompat.Type.navigationBars())
        }
        addView("隐藏导航栏") {
            controller?.hide(WindowInsetsCompat.Type.navigationBars())
        }
    }
}
