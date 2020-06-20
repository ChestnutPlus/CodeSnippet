package x.chestnut.code.snippet.contract;

import android.view.View;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.other.OtherExampleFragment;
import x.chestnut.code.snippet.ui.drawable.DrawableExampleFragment;
import x.chestnut.code.snippet.ui.fragment.FragmentExampleFragment;
import x.chestnut.code.snippet.ui.recyclerView.RecyclerViewFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/4/2 23:29
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class MainFragment extends ScrollBaseFragment{

    public MainFragment() {}

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("Fragment", view -> startFragment(FragmentExampleFragment.newInstance()));
        addView("RecyclerView", view -> startFragment(RecyclerViewFragment.newInstance()));
        addView("Drawable", view -> startFragment(DrawableExampleFragment.newInstance()));
        addView("Other Example", view -> startFragment(OtherExampleFragment.newInstance()));
    }

    @Override
    protected void onViewResume() {
        setTitle(getString(R.string.app_name));
    }
}
