package x.chestnut.code.snippet.ui.recyclerView.itemDecoration;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.fragment.bottomTab.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseAdapter;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean;

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

public class ItemDecorationFragment extends BaseFragment{

    public static ItemDecorationFragment newInstance() {
        ItemDecorationFragment fragment = new ItemDecorationFragment();
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
        recyclerView.addItemDecoration(new MyItemDecoration());
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
        String[] contents = {
                "你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                "青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                "留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                "一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                "西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                "当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
        };
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < bgRes.length; i++) {
                BaseUseBean baseUseBean = new BaseUseBean();
                baseUseBean.bgRes = bgRes[i];
                baseUseBean.content = contents[i];
                beans.add(baseUseBean);
            }
        }
        return beans;
    }
}
