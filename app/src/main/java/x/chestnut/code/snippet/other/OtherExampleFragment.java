package x.chestnut.code.snippet.other;

import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.other.asyncTask.AsyncTaskExampleFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/3/25 22:55
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class OtherExampleFragment extends ScrollBaseFragment {

    public OtherExampleFragment() {}

    public static OtherExampleFragment newInstance() {
        return new OtherExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("AsyncTask", view -> startFragment(AsyncTaskExampleFragment.newInstance()));
    }

    @Override
    protected void onViewResume() {
        setTitle("Other Example");
    }
}
