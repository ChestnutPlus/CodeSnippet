package x.chestnut.code.snippet.ui.fragment.lazyLoad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;

public class BlankFragment extends BaseFragment {

    /*声明传入的参数*/
    private final String TAG = "BlankFragment";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    /*必须要一个空的构造方法*/
    public BlankFragment() {}

    private TextView textView;
    private ProgressBar progressBar;

    /**
     * 使用这个方法进行实例化
     * @param param1 参数
     * @return fragment
     */
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void onViewCreate(View rootView) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        Log.i(TAG,mParam1 + ", onViewCreate");
        textView = rootView.findViewById(R.id.tv_index);
        progressBar = rootView.findViewById(R.id.progress);
    }

    @Override
    protected void onViewResume() {
        Log.i(TAG,mParam1 + ", onViewResume");
        progressBar.setVisibility(View.VISIBLE);
        new MockMetAsyncTask(this).execute(mParam1);
    }

    @Override
    protected void onViewPause() {
        Log.i(TAG,mParam1 + ", onViewPause");
    }

    @Override
    protected void onViewDestroy() {
        Log.i(TAG,mParam1 + ", onViewDestroy");
    }

    private void updateView() {
        progressBar.setVisibility(View.INVISIBLE);
        textView.setText(mParam1);
    }

    /**
     * 用于模拟网络耗时操作
     */
    private static class MockMetAsyncTask extends AsyncTask<String,Void,String> {

        private final WeakReference<BlankFragment> blankFragmentWeakReference;

        public MockMetAsyncTask(BlankFragment blankFragment) {
            this.blankFragmentWeakReference = new WeakReference<>(blankFragment);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            if (blankFragmentWeakReference!=null && blankFragmentWeakReference.get()!=null) {
                blankFragmentWeakReference.get().updateView();
            }
        }
    }
}
