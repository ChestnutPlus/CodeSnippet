package x.chestnut.code.snippet.ui.fragmentLazyLoad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/26 17:35
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public abstract class LazyLoadFragment extends Fragment {

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setContentView(), container, false);
        initView(rootView);
        isInitView = true;
        isCanLoadData();
        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            isCanLoadData();
        }
        else {
            isVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            isCanLoadData();
        }else{
            isVisible = false;
        }
    }

    private void isCanLoadData(){
        if(isInitView && isVisible){
            lazyLoad();
            isInitView = false;
            isVisible = false;
        }
    }

    protected abstract int setContentView();
    protected abstract void initView(View rootView);
    protected abstract void lazyLoad();
}
