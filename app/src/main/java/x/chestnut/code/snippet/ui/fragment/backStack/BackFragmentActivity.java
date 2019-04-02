package x.chestnut.code.snippet.ui.fragment.backStack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;

public class BackFragmentActivity extends BaseActivity implements BackFragment.OnClickListener{

    /**
     * 有一个小问题，
     *      这里多次添加后，不会
     *      调用 Fragment 的 onViewPause()
     *      具体可以看log
     */

    private final String Param1 = "one";
    private final String Param2 = "two";
    private final String Param3 = "three";

    /*添加fragment*/
    private void addFragment(Fragment fragment) {
        addFragment(fragment,true);
    }

    private void addFragment(Fragment fragment, boolean isAddBackStack) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            List<Fragment> list = getSupportFragmentManager().getFragments();
            for (int i = 0; i < list.size(); i++) {
                Fragment f = list.get(i);
                if (f!=fragment && !f.isHidden()) {
                    fragmentTransaction.hide(f);
                }
            }
            if (isAddBackStack)
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.add(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_back_fragment;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {
        addFragment(BackFragment.newInstance(Param1), false);
    }

    @Override
    public void onClick(String param) {
        switch (param) {
            case Param1:
                addFragment(BackFragment.newInstance(Param2));
                break;
            case Param2:
                addFragment(BackFragment.newInstance(Param3));
                break;
            case Param3:
                break;
        }
    }
}
