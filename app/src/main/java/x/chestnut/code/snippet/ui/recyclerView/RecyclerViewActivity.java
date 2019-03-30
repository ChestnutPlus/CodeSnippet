package x.chestnut.code.snippet.ui.recyclerView;

import x.chestnut.code.snippet.base.BackFragmentBaseActivity;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.RecyclerViewBaseUseFragment;
import x.chestnut.code.snippet.ui.recyclerView.clickEvent.ClickRecyclerFragment;
import x.chestnut.code.snippet.ui.recyclerView.headerFooter.HeaderFooterFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecoration.ItemDecorationFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.ItemDecorationSectionFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationStickySection.ItemDecorationStickySectionFragment;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemRecyclerFragment;

public class RecyclerViewActivity extends BackFragmentBaseActivity {
    @Override
    public void lazyLoadViewAfterOnResume() {
        setTitle("RecyclerView示例");
        addView("基本用法",view -> startFragment(RecyclerViewBaseUseFragment.newInstance()));
        addView("多种Item",view -> startFragment(MultiItemRecyclerFragment.newInstance()));
        addView("设置点击事件",view -> startFragment(ClickRecyclerFragment.newInstance()));
        addView("设置ItemDecoration分割线",view -> startFragment(ItemDecorationFragment.newInstance()));
        addView("ItemDecoration实现Section",view -> startFragment(ItemDecorationSectionFragment.newInstance()));
        addView("ItemDecoration实现StickySection",view -> startFragment(ItemDecorationStickySectionFragment.newInstance()));
        addView("HeaderFooter实现",view -> startFragment(HeaderFooterFragment.newInstance()));
    }
}
