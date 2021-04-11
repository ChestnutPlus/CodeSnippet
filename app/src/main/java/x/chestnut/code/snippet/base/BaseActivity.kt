package x.chestnut.code.snippet.base

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2019/3/24 23:26
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        Looper.myQueue().addIdleHandler {
            lazyLoadViewAfterOnResume()
            false
        }
    }

    @get:LayoutRes
    abstract val layoutId: Int
    abstract fun lazyLoadViewAfterOnResume()
    fun setTitle(title: String?) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    protected fun startActivity(c: Class<*>?) {
        startActivity(Intent(this, c))
    }
}