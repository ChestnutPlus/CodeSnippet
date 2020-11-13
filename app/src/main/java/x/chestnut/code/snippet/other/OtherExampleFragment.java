package x.chestnut.code.snippet.other;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import x.chestnut.code.snippet.BuildConfig;
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

    private static final String TAG = OtherExampleFragment.class.getName();

    public OtherExampleFragment() {}

    public static OtherExampleFragment newInstance() {
        return new OtherExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("AsyncTask", view -> startFragment(AsyncTaskExampleFragment.newInstance()));
        addView("GradleTest", view -> {
            Log.e(TAG, "gradleInfo, name: " + BuildConfig.NAME);
            Log.e(TAG, "gradleInfo, is_debug: " + BuildConfig.IS_DEBUG);
            try {
                ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(
                        BuildConfig.APPLICATION_ID, PackageManager.GET_META_DATA);
                String result = applicationInfo.metaData.getString("NAME", "not found");
                Log.e(TAG, "result: " + result);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected String getActionBarTitle() {
        return "Other Example";
    }
}
