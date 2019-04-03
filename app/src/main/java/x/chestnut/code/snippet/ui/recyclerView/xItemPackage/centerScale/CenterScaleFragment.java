package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale;

import android.view.View;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/4/3 9:48
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class CenterScaleFragment extends BaseFragment {

    public CenterScaleFragment() {}

    public static CenterScaleFragment newInstance() {
        return new CenterScaleFragment();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_center_scale_recycler_view;
    }

    @Override
    protected void onLazyViewCreate(View rootView) {



    }
}
