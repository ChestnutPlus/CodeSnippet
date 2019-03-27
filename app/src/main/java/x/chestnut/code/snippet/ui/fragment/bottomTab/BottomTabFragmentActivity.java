package x.chestnut.code.snippet.ui.fragment.bottomTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;

public class BottomTabFragmentActivity extends BaseActivity {

    private Fragment mCurrentFragment;
    private Fragment fragment1,fragment2,fragment3,fragment4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottom_tab_fragment;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {

        fragment1 = TextFragment.newInstance("微信");
        fragment2 = TextFragment.newInstance("通讯录");
        fragment3 = TextFragment.newInstance("发现");
        fragment4 = TextFragment.newInstance("我");

        //init
        addFragment(fragment1);
        showFragment(fragment1);

        findViewById(R.id.tv_1).setOnClickListener(v -> {
            addFragment(fragment1);
            showFragment(fragment1);
        });
        findViewById(R.id.tv_2).setOnClickListener(v -> {
            addFragment(fragment2);
            showFragment(fragment2);
        });
        findViewById(R.id.tv_3).setOnClickListener(v -> {
            addFragment(fragment3);
            showFragment(fragment3);
        });
        findViewById(R.id.tv_4).setOnClickListener(v -> {
            addFragment(fragment4);
            showFragment(fragment4);
        });
    }

    /*添加fragment*/
    private void addFragment(Fragment fragment) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();;
        for (Fragment frag : getSupportFragmentManager().getFragments()) {
            if (frag != fragment) {
                /*先隐藏其他fragment*/
                ft.hide(frag);
            }
            else {
                ft.show(fragment);
            }
        }
        mCurrentFragment = fragment;
        ft.commit();
    }
}
