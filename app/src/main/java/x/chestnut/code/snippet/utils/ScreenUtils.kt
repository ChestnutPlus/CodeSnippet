package x.chestnut.code.snippet.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/11/7 14:29
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
object ScreenUtils {
    fun setScreenOrientation(a: Activity?, isLandscape: Boolean): Boolean {
        a?.let { activity ->
            if (!isLandscape && activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                return true
            }
            if (isLandscape && activity.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                return true
            }
        }
        return false
    }

    fun getScreenWidthByPX(context: Context?): Int {
        val resources = context?.resources
        val dm = resources?.displayMetrics
        return dm?.widthPixels ?: 0
    }
}