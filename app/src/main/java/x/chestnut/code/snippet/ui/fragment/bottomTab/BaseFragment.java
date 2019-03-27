package x.chestnut.code.snippet.ui.fragment.bottomTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2018/12/24 15:33
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(setContentView(), container, false);
        onViewCreate(rootView);
        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onViewPause();
        } else {
            onViewResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isHidden())
            onViewPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden())
            onViewResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onViewDestroy();
    }

    protected abstract int setContentView();
    protected abstract void onViewCreate(View rootView);
    protected abstract void onViewResume();
    protected abstract void onViewPause();
    protected abstract void onViewDestroy();
}
