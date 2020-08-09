package x.chestnut.code.snippet.ui.fragment.lazyLoad;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseActivity;

public class FragmentLazyLoadActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        setTitle("FragmentLazyLoad");
        return R.layout.activity_fragment_lazy_load;
    }

    @Override
    public void lazyLoadViewAfterOnResume() {

        ViewPager viewPager;
        List<Fragment> list;

        list = new ArrayList<>();
        list.add(BlankFragment.newInstance("One"));
        list.add(BlankFragment.newInstance("Two"));
        list.add(BlankFragment.newInstance("Three"));

        MyViewPagerAdapter myAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(0);
    }
}
