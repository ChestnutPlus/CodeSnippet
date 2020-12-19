package x.chestnut.code.snippet.contract;

import android.view.View;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.jetpack.JetpackSampleFragment;
import x.chestnut.code.snippet.kotlin.KotlinSampleFragment;
import x.chestnut.code.snippet.other.OtherExampleFragment;
import x.chestnut.code.snippet.ui.countNumberView.CountNumberViewFragment;
import x.chestnut.code.snippet.ui.drawable.DrawableExampleFragment;
import x.chestnut.code.snippet.ui.fragment.FragmentExampleFragment;
import x.chestnut.code.snippet.ui.notification.NotificationFragment;
import x.chestnut.code.snippet.ui.recyclerView.RecyclerViewExampleFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
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
        addView("RecyclerView", view -> startFragment(RecyclerViewExampleFragment.newInstance()));
        addView("Drawable", view -> startFragment(DrawableExampleFragment.newInstance()));
        addView("Other Example", view -> startFragment(OtherExampleFragment.newInstance()));
        addView("CountNumberView", view -> startFragment(CountNumberViewFragment.newInstance()));
        addView("Notification", view -> startFragment(new NotificationFragment()));
        addView("Kotlin", view -> startFragment(new KotlinSampleFragment()));
        addView("Jetpack", view -> startFragment(new JetpackSampleFragment()));
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.app_name);
    }
}
