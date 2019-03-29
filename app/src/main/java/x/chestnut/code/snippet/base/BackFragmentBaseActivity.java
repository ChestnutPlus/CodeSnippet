package x.chestnut.code.snippet.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import x.chestnut.code.snippet.R;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 22:38
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public abstract class BackFragmentBaseActivity extends ScrollBaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_no_scroll_back_fragment;
    }

    /*添加fragment*/
    protected void startFragment(Fragment fragment) {
        startFragment(fragment,true);
    }

    protected void startFragment(Fragment fragment, boolean isAddBackStack) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (isAddBackStack)
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.add(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
