package x.chestnut.code.snippet.ui.recyclerView;

import x.chestnut.code.snippet.base.BackFragmentScrollBaseActivity;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.RecyclerViewBaseUseFragment;

public class RecyclerViewActivity extends BackFragmentScrollBaseActivity {
    @Override
    public void lazyLoadViewAfterOnResume() {
        setTitle("RecyclerView示例");
        addView("基本用法",view -> {
            startFragment(RecyclerViewBaseUseFragment.newInstance());
        });
    }
}
