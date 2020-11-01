package x.chestnut.code.snippet.base;

import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2018/12/24 15:33
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public abstract class BaseFragment extends Fragment {

    private boolean isIncludeByViewPager = false;
    protected View rootView;

    private boolean isVisible = false;
    private boolean isOnResume = false;
    /*标识是否已经走过：onCreateView*/
    private boolean isInitView = false;
    /*标记是否已经回调过：onViewPause*/
    private boolean isPause = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setContentView(), container, false);
        isIncludeByViewPager = container instanceof ViewPager;
        onViewCreate(rootView);
        isInitView = true;
        Looper.myQueue().addIdleHandler(() -> {
            onLazyViewCreate(rootView);
            return false;
        });
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            isPause = false;
            isCanOnResumeByViewPager();
        }else{
            isVisible = false;
            if (isInitView && !isPause) {
                isPause = true;
                onViewPause();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isHidden() && !isIncludeByViewPager)
            onViewPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()&& !isIncludeByViewPager)
            onViewResume();
        if (isIncludeByViewPager) {
            isOnResume = true;
            isCanOnResumeByViewPager();
        }
    }

    private void isCanOnResumeByViewPager(){
        if(isOnResume && isVisible){
            onViewResume();
            isOnResume = false;
            isVisible = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onViewDestroy();
    }

    protected abstract int setContentView();
    protected void onViewCreate(View rootView){}
    protected void onLazyViewCreate(View rootView) {}
    protected void onViewResume(){}
    protected void onViewPause(){}
    protected void onViewDestroy(){}

    public static void startFragment(FragmentActivity fragmentActivity, @IdRes int frameLayout, Fragment fragment) {
        startFragment(fragmentActivity,frameLayout, fragment,true);
    }

    public static void startFragment(FragmentActivity fragmentActivity, @IdRes int frameLayout, Fragment fragment, boolean isAddBackStack) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            if (isAddBackStack)
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.add(frameLayout, fragment, fragment.getClass().getSimpleName());
            List<Fragment> list = fragmentActivity.getSupportFragmentManager().getFragments();
            for (int i = 0; i < list.size(); i++) {
                Fragment f = list.get(i);
                if (f!=fragment && !f.isHidden()) {
                    fragmentTransaction.hide(f);
                }
            }
            fragmentTransaction.commit();
        }
    }
}
