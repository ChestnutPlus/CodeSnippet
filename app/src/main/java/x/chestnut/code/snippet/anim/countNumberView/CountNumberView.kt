package x.chestnut.code.snippet.anim.countNumberView

import android.animation.ObjectAnimator
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatTextView

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2020/11/8 0:01
 * desc  :
 * thanks To: https://www.jianshu.com/p/bcc91ef2f3ed
 * dependent on:
 * update log:
</pre> *
 */
class CountNumberView(context: Context, attrs: AttributeSet?)
    : AppCompatTextView(context, attrs) {

    //动画时长
    private var duration = 1500

    /**
     * 获取当前数字
     * @return
     */
    /**
     * 根据正则表达式，显示对应数字样式
     * @param number
     */
    //显示数字
    var number = 0f
        set(number) {
            field = number
            regex?.let {
                text = String.format(it, number)
            }
        }

    //显示表达式
    private var regex: String? = null

    /**
     * 显示带有动画效果的数字
     * @param number
     * @param regex
     */
    fun showNumberWithAnimation(number: Float, regex: String?) {
        if (TextUtils.isEmpty(regex)) {
            //默认为整数
            this.regex = INTREGEX
        } else {
            this.regex = regex
        }
        //修改number属性，会调用setNumber方法
        val objectAnimator = ObjectAnimator.ofFloat(this,
                "number", 0f, number)
        objectAnimator.duration = duration.toLong()
        //加速器，从慢到快到再到慢
        objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator.start()
    }

    fun setDuration(duration: Int) {
        this.duration = duration
    }

    companion object {
        //显示表示式
        const val INTREGEX = "%1$01.0f" //不保留小数，整数
        const val FLOATREGEX = "%1$01.2f" //保留2位小数
    }
}