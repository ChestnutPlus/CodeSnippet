package x.chestnut.code.snippet.ui.recyclerView;

import x.chestnut.code.snippet.base.BackFragmentBaseActivity;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.RecyclerViewBaseUseFragment;
import x.chestnut.code.snippet.ui.recyclerView.clickEvent.ClickRecyclerFragment;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemRecyclerFragment;

public class RecyclerViewActivity extends BackFragmentBaseActivity {
    @Override
    public void lazyLoadViewAfterOnResume() {
        setTitle("RecyclerView示例");
        addView("基本用法",view -> {
            startFragment(RecyclerViewBaseUseFragment.newInstance());
        });
        addView("多种Item",view -> {
            startFragment(MultiItemRecyclerFragment.newInstance());
        });
        addView("设置点击事件",view -> {
            startFragment(ClickRecyclerFragment.newInstance());
        });
    }
}
