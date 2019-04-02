package x.chestnut.code.snippet.ui.fragment.backStack;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 18:21
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class BackFragment extends BaseFragment {

    /**
     * 这个接口由Activity实现，就可以进行通信
     *      注意，是在 onAttach 和 onDetach
     *      中注册监听和销毁监听
     */
    public interface OnClickListener {
        void onClick(String param);
    }

    private String TAG = "BackFragment";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private OnClickListener onClickListener;

    public BackFragment() {}

    public static BackFragment newInstance(String param1) {
        BackFragment fragment = new BackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnClickListener)
            onClickListener = (OnClickListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClickListener = null;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_back;
    }

    @Override
    protected void onViewCreate(View rootView) {
        TextView tv = rootView.findViewById(R.id.tv);
        tv.setText(mParam1);
        rootView.findViewById(R.id.btn).setOnClickListener(v -> {
            if (onClickListener!=null)
                onClickListener.onClick(mParam1);
        });
        Log.i(TAG,"onViewCreate, " + mParam1);
    }

    @Override
    protected void onViewResume() {
        Log.i(TAG,"onViewResume, " + mParam1);
    }

    @Override
    protected void onViewPause() {
        Log.i(TAG,"onViewPause, " + mParam1);
    }

    @Override
    protected void onViewDestroy() {
        Log.i(TAG,"onViewDestroy, " + mParam1);
    }
}
