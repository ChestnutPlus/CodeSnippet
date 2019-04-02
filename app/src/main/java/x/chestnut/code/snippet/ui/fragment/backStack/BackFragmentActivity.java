package x.chestnut.code.snippet.ui.fragment.backStack;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;
import x.chestnut.code.snippet.base.BaseFragment;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_back_fragment;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {
        BaseFragment.startFragment(this,R.id.frame_layout, BackFragment.newInstance(Param1),false);
    }

    @Override
    public void onClick(String param) {
        switch (param) {
            case Param1:
                BaseFragment.startFragment(this,R.id.frame_layout, BackFragment.newInstance(Param2));
                break;
            case Param2:
                BaseFragment.startFragment(this,R.id.frame_layout, BackFragment.newInstance(Param3));
                break;
            case Param3:
                break;
        }
    }
}
