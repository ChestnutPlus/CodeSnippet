package x.chestnut.code.snippet.ui.fragment;

import x.chestnut.code.snippet.base.ScrollBaseActivity;
import x.chestnut.code.snippet.ui.fragment.backStack.BackFragmentActivity;
import x.chestnut.code.snippet.ui.fragment.bottomTab.BottomTabFragmentActivity;
import x.chestnut.code.snippet.ui.fragment.lazyLoad.FragmentLazyLoadActivity;

public class FragmentActivity extends ScrollBaseActivity {
    @Override
    public void lazyLoadViewAfterOnResume() {
        setTitle("Fragment示例");
        addView("ViewPager懒加载", view -> startActivity(FragmentLazyLoadActivity.class));
        addView("底部Tab效果", view -> startActivity(BottomTabFragmentActivity.class));
        addView("Fragment回退栈", view -> startActivity(BackFragmentActivity.class));
    }
}
