package x.chestnut.code.snippet.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
 *     time  : 2019/4/2 23:25
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public abstract class ScrollBaseFragment extends BaseFragment{

    protected void startActivity(Class c) {
        startActivity(new Intent(getActivity(),c));
    }

    protected void startFragment(Fragment fragment) {
        startFragment(fragment,true);
    }

    protected void startFragment(Fragment fragment, boolean isAddToBackStack) {
        BaseFragment.startFragment(getActivity(),R.id.frame_layout, fragment,isAddToBackStack);
    }

    protected void setTitle(String title) {
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar!=null) {
                actionBar.setTitle(title);
            }
        }
    }

    protected void addView(String content, View.OnClickListener onClickListener) {
        FlexboxLayout flexboxLayout = (FlexboxLayout) rootView.findViewById(R.id.flx);
        TextView textView = new TextView(rootView.getContext());
        textView.setBackground(getResources().getDrawable(R.drawable.tv_bg_round));
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        int padding = ConvertUtils.dp2px(rootView.getContext(), 10);
        textView.setPadding(padding,padding,padding,padding);
        flexboxLayout.addView(textView);
        int margin = ConvertUtils.dp2px(rootView.getContext(), 5);
        int marginTopBottom = ConvertUtils.dp2px(rootView.getContext(), 5);
        FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(margin, marginTopBottom, margin, marginTopBottom);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTag(content);
        textView.setOnClickListener(onClickListener);
    }

    @Override
    protected int setContentView() {
        return R.layout.layout_scroll;
    }
}
