package x.chestnut.code.snippet.contract;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;
import x.chestnut.code.snippet.base.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {
        BaseFragment.startFragment(this,R
                .id.frame_layout,MainFragment.newInstance(),false);
    }
}
