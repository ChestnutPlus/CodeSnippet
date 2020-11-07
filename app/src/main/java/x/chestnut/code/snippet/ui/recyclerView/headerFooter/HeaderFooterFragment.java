package x.chestnut.code.snippet.ui.recyclerView.headerFooter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseAdapter;
import x.chestnut.code.snippet.ui.recyclerView.baseUse.BaseUseBean;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/27 23:09
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class HeaderFooterFragment extends BaseFragment{

    public static HeaderFooterFragment newInstance() {
        HeaderFooterFragment fragment = new HeaderFooterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.layout_recycler_view;
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        //Manager的不同也要娶适配
        switch ((int) (System.currentTimeMillis()%5)) {
            //1.LinearLayoutManager
            case 0:
                LinearLayoutManager l1 = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(l1);
                break;
            case 1:
                LinearLayoutManager l2 = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(l2);
                break;
            //2.GridLayoutManager
            case 2:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            //3.StaggeredGridLayoutManager
            case 3:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                        3,StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            case 4:
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(
                        3,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(s);
                break;
        }

        //Adapter Wrapper
        BaseUseAdapter baseAdapter = new BaseUseAdapter(getBeans());
        WrapperHeaderFooterAdapter wrapperHeaderFooterAdapter = new WrapperHeaderFooterAdapter(baseAdapter);
        //add header
        for (int i = 0; i < 3; i++) {
            TextView header = new TextView(getContext());
            header.setText("Header-"+i);
            wrapperHeaderFooterAdapter.addHeaderView(header);
        }
        //add Footer
        for (int i = 0; i < 3; i++) {
            TextView header = new TextView(getContext());
            header.setText("Footer-"+i);
            wrapperHeaderFooterAdapter.addFootView(header);
        }
        recyclerView.setAdapter(wrapperHeaderFooterAdapter);
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
