package x.chestnut.code.snippet.other.asyncTask;

import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import x.chestnut.code.snippet.base.ScrollBaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
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

        String WORK_TAG = "WLAN_DETECT";
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(WlanDetectWork.class)
                .addTag(WORK_TAG)
                .build();
        WorkManager.getInstance().enqueue(request);
        WorkManager.getInstance().cancelAllWorkByTag(WORK_TAG);
        WorkManager.getInstance().getWorkInfosByTagLiveData(WORK_TAG)
                .observe(this, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(@Nullable List<WorkInfo> workInfos) {

                    }
                });
    }

    @Override
    protected void onViewResume() {
        setTitle("AsyncTask");
    }


}
