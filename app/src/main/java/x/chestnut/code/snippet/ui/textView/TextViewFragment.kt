package x.chestnut.code.snippet.ui.textView

import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.ConvertUtils.sp2px

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/18 23:17
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class TextViewFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_text_view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        
        val textView2 = view.findViewById(R.id.tv2) as TextView
        val clickString = "I Love Android!"
        val spannableString = SpannableString(clickString)
        //设置点击的位置，为 position[2,6)，
        // 第二个字符开始到第6个字符，前开后闭
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(@NonNull widget: View) {
                Toast.makeText(activity, "Love", Toast.LENGTH_SHORT).show()
            }
        }, 2, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView2.text = spannableString
        textView2.movementMethod = LinkMovementMethod.getInstance()

        val textView3 = view.findViewById(R.id.tv3) as TextView
        val content = "This is a test, you can click baidu or youku."
        val ss = SpannableString(content)
        
        //设置网络超链接
        ss.setSpan(URLSpan("http://www.baidu.com"),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(URLSpan("http://www.youku.com"),
                content.indexOf("youku"), ss.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        
        //设置字体颜色
        ss.setSpan(ForegroundColorSpan(Color.parseColor("#ff0000")),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(ForegroundColorSpan(Color.parseColor("#ff00ff")),
                content.indexOf("youku"), ss.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        
        // 设置字体大小
        ss.setSpan(AbsoluteSizeSpan(sp2px(view.context, 25f)),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(AbsoluteSizeSpan(sp2px(view.context, 30f)),
                content.indexOf("youku"), ss.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        
        // 取消下划线
        ss.setSpan(object : UnderlineSpan() {
            override fun updateDrawState(@NonNull textPaint: TextPaint) {
                textPaint.isUnderlineText = false
            }
        },
                content.indexOf("youku"), ss.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView3.text = ss
        textView3.movementMethod = LinkMovementMethod.getInstance()

        val textView4 = view.findViewById(R.id.tv4) as TextView
        val linkWord1 = "Android"
        val linkWord2 = "Are you ok?"
        val linkWord3 = "think you!"
        val word = "Hello $linkWord1,$linkWord2 I'm fine,$linkWord3"
        val spannableStringBuilder = SpannableStringBuilder(word)
        val index1 = word.indexOf(linkWord1)
        val index2 = word.indexOf(linkWord2)
        val index3 = word.indexOf(linkWord3)
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(@NonNull widget: View) {
                Toast.makeText(activity, linkWord1, Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(@NonNull ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.RED //设置文件颜色
                ds.isUnderlineText = true //设置下划线
            }
        }, index1, index1 + linkWord1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(@NonNull widget: View) {
                Toast.makeText(activity, linkWord2, Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(@NonNull ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.GREEN //设置文件颜色
                ds.isUnderlineText = false //设置下划线
            }
        }, index2, index2 + linkWord2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(@NonNull widget: View) {
                Toast.makeText(activity, linkWord3, Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(@NonNull ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE //设置文件颜色
                ds.isUnderlineText = false //设置下划线
            }
        }, index3, index3 + linkWord3.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView4.textSize = 14f
        textView4.text = spannableStringBuilder
        textView4.movementMethod = LinkMovementMethod.getInstance()
        return view
    }
}