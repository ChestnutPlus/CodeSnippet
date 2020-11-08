package x.chestnut.code.snippet.ui.countNumberView;

import android.view.View;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2020/11/8 16:10
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class CountNumberViewFragment extends BaseFragment {

    public static CountNumberViewFragment newInstance() {
        return new CountNumberViewFragment();
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_count_number_view;
    }

    @Override
    protected String getActionBarTitle() {
        return "CountNumberView";
    }

    @Override
    protected void onViewCreate(View rootView) {
        super.onViewCreate(rootView);

        CountNumberView countNumberView = rootView.findViewById(R.id.tv_1);
        countNumberView.showNumberWithAnimation(3201.23f, CountNumberView.FLOATREGEX);

        countNumberView = rootView.findViewById(R.id.tv_2);
        countNumberView.setDuration(5000);
        countNumberView.showNumberWithAnimation(164813, CountNumberView.INTREGEX);
    }
}
