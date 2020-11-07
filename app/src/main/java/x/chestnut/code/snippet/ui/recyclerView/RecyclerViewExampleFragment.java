package x.chestnut.code.snippet.ui.recyclerView;

import android.view.View;

import x.chestnut.code.snippet.base.ScrollBaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.RecyclerViewBaseUseFragment;
import x.chestnut.code.snippet.ui.recyclerView.clickEvent.ClickRecyclerFragment;
import x.chestnut.code.snippet.ui.recyclerView.controlSpeed.ControlSpeedRecyclerViewFragment;
import x.chestnut.code.snippet.ui.recyclerView.headerFooter.HeaderFooterFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecoration.ItemDecorationFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationSection.ItemDecorationSectionFragment;
import x.chestnut.code.snippet.ui.recyclerView.itemDecorationStickySection.ItemDecorationStickySectionFragment;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemRecyclerFragment;
import x.chestnut.code.snippet.ui.recyclerView.scrollImg.ScrollImgItemFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.XItemFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/2 23:50
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class RecyclerViewExampleFragment extends ScrollBaseFragment{

    public RecyclerViewExampleFragment() {

    }

    public static RecyclerViewExampleFragment newInstance() {
        return new RecyclerViewExampleFragment();
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        addView("基本用法",view -> startFragment(RecyclerViewBaseUseFragment.newInstance()));
        addView("多种Item",view -> startFragment(MultiItemRecyclerFragment.newInstance()));
        addView("设置点击事件",view -> startFragment(ClickRecyclerFragment.newInstance()));
        addView("设置ItemDecoration分割线",view -> startFragment(ItemDecorationFragment.newInstance()));
        addView("ItemDecoration实现Section",view -> startFragment(ItemDecorationSectionFragment.newInstance()));
        addView("ItemDecoration实现StickySection",view -> startFragment(ItemDecorationStickySectionFragment.newInstance()));
        addView("HeaderFooter实现",view -> startFragment(HeaderFooterFragment.newInstance()));
        addView("Scroll-Img-Item",view -> startFragment(ScrollImgItemFragment.newInstance()));
        addView("Control-RecyclerView-Speed",view -> startFragment(ControlSpeedRecyclerViewFragment.newInstance()));
        addView("X-Item-封装",view -> startFragment(XItemFragment.newInstance()));
    }

    @Override
    protected void onViewResume() {
        setTitle("RecyclerView示例");
    }
}
