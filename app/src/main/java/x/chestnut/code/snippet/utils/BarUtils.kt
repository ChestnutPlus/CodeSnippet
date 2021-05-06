package x.chestnut.code.snippet.utils

import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/18 13:04
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
object BarUtils {

    fun showActionBar(activity: Activity?) {
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.show()
        } else {
            activity?.actionBar?.show()
        }
    }

    fun hideActionBar(activity: Activity?) {
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.hide()
        } else {
            activity?.actionBar?.hide()
        }
    }

    fun isActionBarExists(activity: Activity): Boolean {
        return activity.actionBar != null
    }

    fun hideStatusBar(activity: Activity?) {
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun showStatusBar(activity: Activity?) {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun isStatusBarExists(activity: Activity): Boolean {
        val params = activity.window.attributes
        return params.flags and 1024 != 1024
    }
}