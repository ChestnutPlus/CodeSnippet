package x.chestnut.code.snippet.ui.recyclerView.xItemPackage;

import x.chestnut.code.snippet.base.BackFragmentBaseActivity;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.XItemBaseUseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView.CircleRecyclerViewFragment;

public class XItemActivity extends BackFragmentBaseActivity {

    @Override
    public void lazyLoadViewAfterOnResume() {
        setTitle("X-Item封装");
        addView("基本用法-轻松实现多Item布局",view -> startFragment(XItemBaseUseFragment.newInstance()));
        addView("无限循环RecyclerView",view -> startFragment(CircleRecyclerViewFragment.newInstance()));
    }
}
