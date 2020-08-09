package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.circleScrollRecyclerView;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemImg;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemTxt;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/29 21:50
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class CircleRecyclerViewFragment extends BaseFragment{

    public static CircleRecyclerViewFragment newInstance() {
        CircleRecyclerViewFragment fragment = new CircleRecyclerViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_circle_recycler_view;
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        CircleRecyclerView recyclerView = (CircleRecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setNeedLoop(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setDefaultSelected(12,4);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);
        CircleSimpleAdapter circleSimpleAdapter = new CircleSimpleAdapter();
        recyclerView.setAdapter(circleSimpleAdapter);
        circleSimpleAdapter.addAll(getBeans());
    }

    public List<XItem> getBeans() {
        List<XItem> beans = new ArrayList<>();
        int[] bgRes = {
                R.drawable.girl_1,
                R.drawable.girl_2,
                R.drawable.girl_3,
                R.drawable.girl_4,
                R.drawable.girl_5,
                R.drawable.girl_6,
        };
        String[] contents = {
                "1. 你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                "2. 青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                "3. 留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                "4. 一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                "5. 西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                "6. 当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
        };
        for (int i = 0; i < bgRes.length; i++) {
            ItemTxt itemTxt = new ItemTxt(contents[i%contents.length]);
            beans.add(itemTxt);

            ItemImg itemImg = new ItemImg(bgRes[i%bgRes.length]);
            beans.add(itemImg);
        }
        return beans;
    }
}
