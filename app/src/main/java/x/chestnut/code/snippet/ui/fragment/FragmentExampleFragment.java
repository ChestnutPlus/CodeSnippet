package x.chestnut.code.snippet.ui.fragment;

import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.ui.fragment.backStack.BackFragmentActivity;
import x.chestnut.code.snippet.ui.fragment.bottomTab.BottomTabFragmentActivity;
import x.chestnut.code.snippet.ui.fragment.lazyLoad.FragmentLazyLoadActivity;

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

public class FragmentExampleFragment extends ScrollBaseFragment{

    public FragmentExampleFragment() {}

    public static FragmentExampleFragment newInstance() {
        return new FragmentExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("ViewPager懒加载", view -> startActivity(FragmentLazyLoadActivity.class));
        addView("底部Tab效果", view -> startActivity(BottomTabFragmentActivity.class));
        addView("Fragment回退栈", view -> startActivity(BackFragmentActivity.class));
    }

    @Override
    protected String getActionBarTitle() {
        return "Fragment示例";
    }
}
