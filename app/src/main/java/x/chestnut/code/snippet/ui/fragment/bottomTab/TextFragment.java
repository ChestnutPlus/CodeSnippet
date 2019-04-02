package x.chestnut.code.snippet.ui.fragment.bottomTab;

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
 *     time  : 2019/3/27 16:06
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class TextFragment extends BaseFragment {

    private String TAG = "TextFragment";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public TextFragment() {}

    public static TextFragment newInstance(String param1) {
        TextFragment fragment = new TextFragment();
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
    protected int setContentView() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void onViewCreate(View rootView) {
        TextView textView = rootView.findViewById(R.id.tv_index);
        textView.setText(mParam1);
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
