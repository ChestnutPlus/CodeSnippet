package x.chestnut.code.snippet.base;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;
import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.utils.ConvertUtils;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/2 23:25
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public abstract class ScrollBaseFragment extends BaseFragment{

    protected TextView addView(CharSequence content, View.OnClickListener onClickListener) {
        FlexboxLayout flexboxLayout = (FlexboxLayout) rootView.findViewById(R.id.flx);
        TextView textView = new TextView(rootView.getContext());
        textView.setBackground(ResourcesCompat.getDrawable(getResources(),
                R.drawable.tv_bg_round, null));
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        int padding = ConvertUtils.dp2px(rootView.getContext(), 10);
        textView.setPadding(padding,padding,padding,padding);
        flexboxLayout.addView(textView);
        int margin = ConvertUtils.dp2px(rootView.getContext(), 5);
        int marginTopBottom = ConvertUtils.dp2px(rootView.getContext(), 5);
        FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams)
                textView.getLayoutParams();
        layoutParams.setMargins(margin, marginTopBottom, margin, marginTopBottom);
        textView.setTag(content);
        if (onClickListener != null) {
            textView.setFocusable(true);
            textView.setClickable(true);
            textView.setOnClickListener(onClickListener);
        }
        return textView;
    }

    protected TextView addView(@StringRes int strId, View.OnClickListener onClickListener) {
        return addView(getString(strId), onClickListener);
    }

    @Override
    protected int setContentView() {
        return R.layout.layout_scroll;
    }
}
