package x.chestnut.code.snippet.ui.recyclerView.xItemPackage;

import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.XItemBaseUseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale.CenterScaleFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView.CircleRecyclerViewFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/2 23:56
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class XItemFragment extends ScrollBaseFragment{

    public XItemFragment() {

    }

    public static XItemFragment newInstance() {
        return new XItemFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("基本用法-轻松实现多Item布局",view ->
                startFragment(XItemBaseUseFragment.newInstance()));
        addView("无限循环RecyclerView",view ->
                startFragment(CircleRecyclerViewFragment.newInstance()));
        addView("中心放大RecyclerView",view ->
                startFragment(CenterScaleFragment.newInstance()));
    }

    @Override
    protected void onViewResume() {
        setTitle("X-Item封装");
    }
}
