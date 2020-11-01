package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.centerScale;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import x.chestnut.code.snippet.R;
import x.chestnut.code.snippet.base.BaseFragment;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.ItemImg;
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.baseUse.SimpleAdapter;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/4/3 9:48
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class CenterScaleFragment extends BaseFragment {

    public CenterScaleFragment() {}

    public static CenterScaleFragment newInstance() {
        return new CenterScaleFragment();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_center_scale_recycler_view;
    }

    @Override
    protected void onLazyViewCreate(View rootView) {
        CenterScaleRecyclerView recyclerView = (CenterScaleRecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 50; i++) {
            switch (random.nextInt(100)%4) {
                case 0:
                    ItemImg itemImg = new ItemImg(bgRes[i%bgRes.length]);
                    itemImg.setHeight(200);
                    itemImg.setWidth(200);
                    beans.add(itemImg);
                    break;
            }
        }
        return beans;
    }
}
