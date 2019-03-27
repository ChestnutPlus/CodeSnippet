package x.chestnut.code.snippet.ui.recyclerView.baseUse;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.fragment.bottomTab.BaseFragment;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 23:09
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class RecyclerViewBaseUseFragment extends BaseFragment{

    public static RecyclerViewBaseUseFragment newInstance() {
        RecyclerViewBaseUseFragment fragment = new RecyclerViewBaseUseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void onViewCreate(View rootView) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        BaseUseAdapter baseAdapter = new BaseUseAdapter(getBeans());
        recyclerView.setAdapter(baseAdapter);
    }

    @Override
    protected void onViewResume() {

    }

    @Override
    protected void onViewPause() {

    }

    @Override
    protected void onViewDestroy() {

    }

    public List<BaseUseBean> getBeans() {
        List<BaseUseBean> beans = new ArrayList<>();
        int[] bgRes = {
            R.drawable.girl_1,
            R.drawable.girl_2,
            R.drawable.girl_3,
            R.drawable.girl_4,
            R.drawable.girl_5,
            R.drawable.girl_6,
        };
        for (int i = 0; i < 5; i++) {
            for (int bgRe : bgRes) {
                BaseUseBean baseUseBean = new BaseUseBean();
                baseUseBean.bgRes = bgRe;
                beans.add(baseUseBean);
            }
        }
        return beans;
    }
}
