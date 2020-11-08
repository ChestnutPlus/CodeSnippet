package x.chestnut.code.snippet.other.asyncTask;

import android.os.AsyncTask;
import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/3/25 23:00
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class AsyncTaskExampleFragment extends ScrollBaseFragment {

    public AsyncTaskExampleFragment() {}

    public static AsyncTaskExampleFragment newInstance() {
        return new AsyncTaskExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("普通用法", view -> {
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute("strings");
            getActivity().getWindow().getDecorView().postDelayed(() -> {
                myAsyncTask.cancel(true);
            }, 1000);
        });
        addView("并行执行任务", view -> {
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"strings");
        });
    }

    @Override
    protected String getActionBarTitle() {
        return "AsyncTask";
    }
}
