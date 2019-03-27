package x.chestnut.code.snippet;

import x.chestnut.code.snippet.base.ScrollBaseActivity;
import x.chestnut.code.snippet.ui.fragment.FragmentActivity;
import x.chestnut.code.snippet.ui.recyclerView.RecyclerViewActivity;

public class MainActivity extends ScrollBaseActivity {
    @Override
    public void lazyLoadViewAfterOnResume() {
        addView("Fragment", view -> startActivity(FragmentActivity.class));
        addView("RecyclerView", view -> startActivity(RecyclerViewActivity.class));
    }
}
