package x.chestnut.code.snippet.ui.textView;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;
import x.chestnut.code.snippet.utils.ConvertUtils;

public class TextViewActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        setTitle("TextView");
        return R.layout.activity_text_view;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {
        spannableWord();
        spannableMore();
        spannableBuilder();
    }

    /**
     * Spannable：监听某些文字的点击事件
     */
    private void spannableWord() {
        TextView textView2 = (TextView) findViewById(R.id.tv2);
        String clickString = "I Love Android!";
        SpannableString spannableString =new SpannableString(clickString);
        //设置点击的位置，为 position[2,6)，
        // 第二个字符开始到第6个字符，前开后闭
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(TextViewActivity.this,"Love",Toast.LENGTH_SHORT).show();
            }
        },2,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(spannableString);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 使用不同的类型的Spannable，达到
     * 不同的效果，例如：URL，下划线，颜色，
     * 字体大小等等
     */
    private void spannableMore() {
        TextView textView3 = (TextView) findViewById(R.id.tv3);
        String content = "This is a test, you can click baidu or youku.";
        SpannableString ss = new SpannableString(content);
        //设置网络超链接
        ss.setSpan(new URLSpan("http://www.baidu.com"),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new URLSpan("http://www.youku.com"),
                content.indexOf("youku"), ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体颜色
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ff00ff")),
                content.indexOf("youku"), ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置字体大小
        ss.setSpan(new AbsoluteSizeSpan(ConvertUtils.sp2px(this, 25)),
                content.indexOf("baidu"), content.indexOf(" or"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(ConvertUtils.sp2px(this, 30)),
                content.indexOf("youku"), ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 取消下划线
        ss.setSpan(new UnderlineSpan() {
                       @Override
                       public void updateDrawState(@NonNull TextPaint textPaint) {
                           textPaint.setUnderlineText(false);
                       }
                   },
                content.indexOf("youku"), ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(ss);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 使用SpannableBuilder去设置不同
     * 的风格，颜色，超链接，大小等等
     */
    private void spannableBuilder() {
        TextView textView4 = (TextView) findViewById(R.id.tv4);
        final String linkWord1 = "Android";
        final String linkWord2 = "Are you ok?";
        final String linkWord3 = "think you!";
        String word = "Hello " + linkWord1 + "," + linkWord2 + " I'm fine," + linkWord3;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(word);
        int index1 = word.indexOf(linkWord1);
        int index2 = word.indexOf(linkWord2);
        int index3 = word.indexOf(linkWord3);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(TextViewActivity.this, linkWord1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
        }, index1, index1 + linkWord1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(TextViewActivity.this, linkWord2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.GREEN);       //设置文件颜色
                ds.setUnderlineText(false);      //设置下划线
            }
        }, index2, index2 + linkWord2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(TextViewActivity.this, linkWord3, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);       //设置文件颜色
                ds.setUnderlineText(false);      //设置下划线
            }
        }, index3, index3 + linkWord3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setTextSize(14);
        textView4.setText(spannableStringBuilder);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
