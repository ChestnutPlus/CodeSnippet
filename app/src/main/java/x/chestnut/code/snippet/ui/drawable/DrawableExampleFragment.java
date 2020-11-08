package x.chestnut.code.snippet.ui.drawable;

import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.ui.drawable.roundedBitmapDrawable.RoundDrawableFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/2 23:39
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class DrawableExampleFragment extends ScrollBaseFragment{

    public DrawableExampleFragment() {}

    public static DrawableExampleFragment newInstance() {
        return new DrawableExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("RoundDrawable", view ->
                startFragment(RoundDrawableFragment.newInstance()));
    }

    @Override
    protected String getActionBarTitle() {
        return "Drawable示例";
    }
}
