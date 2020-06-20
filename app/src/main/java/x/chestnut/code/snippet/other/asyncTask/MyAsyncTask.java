package x.chestnut.code.snippet.other.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2020/3/25 23:01
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

    private static final String TAG = "MyAsyncTask";

    @Override
    protected Boolean doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread().getName());
        //模拟耗时的操作
        int i = 0;
        for (int j = 0; j < 100; j++) {
            if (isCancelled()) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled: " + Thread.currentThread().getName());
    }
}
