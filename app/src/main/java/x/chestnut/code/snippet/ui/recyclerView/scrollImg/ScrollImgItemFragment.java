package x.chestnut.code.snippet.ui.recyclerView.scrollImg;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.ui.fragment.bottomTab.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.IMultiType;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.ItemImgBean;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.ItemImgTxtBean;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.ItemTxtBean;
import x.chestnut.code.snippet.ui.recyclerView.multiItem.MultiItemAdapter;

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

public class ScrollImgItemFragment extends BaseFragment{

    public static ScrollImgItemFragment newInstance() {
        ScrollImgItemFragment fragment = new ScrollImgItemFragment();
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
        MultiItemAdapter baseAdapter = new MultiItemAdapter(getBeans(),recyclerView);
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

    public List<IMultiType> getBeans() {
        List<IMultiType> beans = new ArrayList<>();
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

        for (int longBgRe : longBgRes) {
            for (int i = 0; i < 8; i++) {
                switch (random.nextInt(100) % 3) {
                    case 0:
                        ItemImgBean itemImgBean = new ItemImgBean();
                        itemImgBean.bgRes = bgRes[random.nextInt(100) % bgRes.length];
                        beans.add(itemImgBean);
                        break;
                    case 1:
                        ItemTxtBean itemTxtBean = new ItemTxtBean();
                        itemTxtBean.content = contents[random.nextInt(100) % contents.length];
                        beans.add(itemTxtBean);
                        break;
                    case 2:
                        ItemImgTxtBean itemImgTxtBean = new ItemImgTxtBean();
                        itemImgTxtBean.bgRes = bgRes[random.nextInt(100) % bgRes.length];
                        itemImgTxtBean.content = contents[random.nextInt(100) % contents.length];
                        beans.add(itemImgTxtBean);
                        break;
                }
            }

            ItemScrollImgBean itemScrollImgBean = new ItemScrollImgBean();
            itemScrollImgBean.bgRes = longBgRe;
            beans.add(itemScrollImgBean);
        }

        for (int i = 0; i < 8; i++) {
            switch (random.nextInt(100)%3) {
                case 0:
                    ItemImgBean itemImgBean = new ItemImgBean();
                    itemImgBean.bgRes = bgRes[random.nextInt(100)%bgRes.length];
                    beans.add(itemImgBean);
                    break;
                case 1:
                    ItemTxtBean itemTxtBean = new ItemTxtBean();
                    itemTxtBean.content = contents[random.nextInt(100)%contents.length];
                    beans.add(itemTxtBean);
                    break;
                case 2:
                    ItemImgTxtBean itemImgTxtBean = new ItemImgTxtBean();
                    itemImgTxtBean.bgRes = bgRes[random.nextInt(100)%bgRes.length];
                    itemImgTxtBean.content = contents[random.nextInt(100)%contents.length];
                    beans.add(itemImgTxtBean);
                    break;
            }
        }

        return beans;
    }
}
