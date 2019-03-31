package x.chestnut.code.snippet.base;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.utils.ConvertUtils;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 17:59
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public abstract class ScrollBaseActivity extends BaseActivity{

    protected void addView(String content, View.OnClickListener onClickListener) {
        FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flx);
        TextView textView = new TextView(this);
        textView.setBackground(getResources().getDrawable(R.drawable.tv_bg_round));
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        int padding = ConvertUtils.dp2px(this, 10);
        textView.setPadding(padding,padding,padding,padding);
        flexboxLayout.addView(textView);
        int margin = ConvertUtils.dp2px(this, 5);
        int marginTopBottom = ConvertUtils.dp2px(this, 5);
        FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(margin, marginTopBottom, margin, marginTopBottom);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTag(content);
        textView.setOnClickListener(onClickListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll;
    }
}