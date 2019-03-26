package x.chestnut.code.snippet.ui.hideShow;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;
import x.chestnut.code.snippet.ui.fragmentLazyLoad.BlankFragment;

public class AddFragmentActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        setTitle("AddFragment");
        return R.layout.activity_add_fragment;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {
        findViewById(R.id.btn_add).setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            tx.add(R.id.frame_layout,BlankFragment.newInstance(String.valueOf(System.currentTimeMillis())) ,"ONE");
            tx.addToBackStack(null);
            tx.commit();
        });
    }
}
