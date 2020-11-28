package x.chestnut.code.snippet.other;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import x.chestnut.code.snippet.BuildConfig;
import x.chestnut.code.snippet.R;
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
    private boolean mIsShowAllContent = false;
    private TextView mTips = null;
    private TextView mTvContent = null;

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
        //字符串通配符
        // 1. %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格
        // 2. %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0
        // 3. %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.3时，
        //           代表的是在输出之前放置2个空格，且小数点后四舍五入保留3位。
        String userName = "chestnut";
        int mailCount = 3;
        addView(getString(R.string.str_1, userName, mailCount), null);
        addView(getString(R.string.str_2, userName, mailCount), null);
        addView(getString(R.string.str_3, 312.365f, 50f), null);
        addView(getString(R.string.str_3, 21212.365f, 50000.128534f), null);

        String showAllStr = "--查看全部--";
        String showLittleStr = "---收起---";
        mTips = addView(mIsShowAllContent ? showLittleStr : showAllStr, v -> {
            if(mIsShowAllContent) {
                mTvContent.setMaxLines(1);
                mTips.setText(showAllStr);
            } else {
                mTvContent.setMaxLines(200);
                mTips.setText(showLittleStr);
            }
            mIsShowAllContent = !mIsShowAllContent;
        });
        mTvContent = addView(R.string.long_text, null);
        mTvContent.setMaxLines(mIsShowAllContent ? 200 : 1);
    }

    @Override
    protected String getActionBarTitle() {
        return "Other Example";
    }
}
