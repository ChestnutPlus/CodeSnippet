package x.chestnut.code.snippet.ui.fragment.lazyLoad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import x.chestnut.code.snippet.R;

public class BlankFragment extends LazyLoadFragment {

    /*声明传入的参数*/
    private String TAG = "BlankFragment";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    /*必须要一个空的构造方法*/
    public BlankFragment() {}

    private TextView textView;

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

    /**
     * 为了防止屏幕旋转的情况，需要取出参数
     * 在 new 的时候去设置好参数
     * @param savedInstanceState state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView(View rootView) {
        Log.i(TAG,mParam1 + ", initView");
        textView = rootView.findViewById(R.id.tv_index);
    }

    @Override
    protected void lazyLoad() {
        Log.i(TAG,mParam1 + ", lazyLoad");
        new MockMetAsyncTask(this).execute(mParam1);
    }

    private void updateView() {
        textView.setText(mParam1);
    }

    /**
     * 用于模拟网络耗时操作
     */
    private static class MockMetAsyncTask extends AsyncTask<String,Void,String> {

        private WeakReference<BlankFragment> blankFragmentWeakReference;

        public MockMetAsyncTask(BlankFragment blankFragment) {
            this.blankFragmentWeakReference = new WeakReference<>(blankFragment);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1*1000);
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
