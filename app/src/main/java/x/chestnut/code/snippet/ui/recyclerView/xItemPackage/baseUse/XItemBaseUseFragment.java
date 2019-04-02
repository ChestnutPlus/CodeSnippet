package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem;

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

public class XItemBaseUseFragment extends BaseFragment{

    private RecyclerView recyclerView;

    public static XItemBaseUseFragment newInstance() {
        XItemBaseUseFragment fragment = new XItemBaseUseFragment();
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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        SimpleAdapter simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.addAll(getBeans());
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
                "你的脸，那么白净，弯弯的一双眉毛，那么修长；水汪汪的一对眼睛，那么明亮！",
                "青翠的柳丝，怎能比及你的秀发；碧绿涟漪，怎能比及你的眸子。",
                "留给我印象最深的是你那双湖水般清澈的眸子，以及长长的、一闪一闪的睫毛，像是探询，像是关切，像是问候",
                "一个美丽的女人是一颗钻石，一个好的女人是一个宝库。",
                "西湖美不美，美；东湖美不美，美！不过，有你在我面前，足以使我陶醉。",
                "当我凝视到你的眼，当我听到你的声音，当我闻到你秀发上的淡淡清香，当我感受到我剧烈的心跳，我明白了：你是我今生的唯一！"
        };
        int[] longBgRes = {
            R.drawable.long_0,
            R.drawable.long_1,
            R.drawable.long_2,
            R.drawable.long_3,
            R.drawable.long_4,
        };
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 50; i++) {
            switch (random.nextInt(100)%4) {
                case 0:
                    ItemImg itemImg = new ItemImg(bgRes[i%bgRes.length]);
                    beans.add(itemImg);
                    break;
                case 1:
                    ItemTxt itemTxt = new ItemTxt(contents[i%contents.length]);
                    beans.add(itemTxt);
                    break;
                case 2:
                    ItemImgTxt itemImgTxt = new ItemImgTxt(bgRes[i%bgRes.length],contents[i%contents.length]);
                    beans.add(itemImgTxt);
                    break;
                case 3:
                    ItemScrollImg itemScrollImg = new ItemScrollImg(longBgRes[i%longBgRes.length], recyclerView);
                    beans.add(itemScrollImg);
                    break;
            }
        }
        return beans;
    }
}
