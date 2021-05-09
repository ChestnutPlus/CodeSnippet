package x.chestnut.code.snippet.anim.carTrain

import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carSign.CarSignView
import x.chestnut.code.snippet.anim.carTrain.recycler.OnCallback
import x.chestnut.code.snippet.anim.carTrain.recycler.adapter.CarSimpleAdapter
import x.chestnut.code.snippet.anim.carTrain.recycler.item.CarItem
import x.chestnut.code.snippet.anim.carTrain.recycler.layoutManager.MyLinearLayoutManager
import x.chestnut.code.snippet.anim.carTrain.recycler.recyclerView.CarRecyclerGallery
import x.chestnut.code.snippet.anim.carTrain.recycler.snap.MyLinearSnapHelper
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.BarUtils
import x.chestnut.code.snippet.utils.ScreenUtils

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/9 13:33
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class CarTrainFragment : BaseFragment() {

    private var carSignView: CarSignView? = null

    private var mRecyclerView: CarRecyclerGallery? = null
    private var linearLayoutManager: MyLinearLayoutManager? = null
    private var carSimpleAdapter: CarSimpleAdapter? = null

    private var currentIndex = -1
    private var isFirstEnter = true
    private var anim: TranslateAnimation? = null
    private val mOnCarClickListener: OnCallback<Int> = object : OnCallback<Int> {
        override fun onAction(t: Int) {
//            if (t != currentIndex) {
//                //这里滑动的距离应该是，当前item.width/2+下一个或者上一个item.width/2
//                val carWidth = resources.getDimension(R.dimen.car_width).toInt()
//                val scrollX: Int = (t - currentIndex) * carWidth
//                mRecyclerView?.smoothScrollBy(scrollX, 0)
//            } else {
//                Toast.makeText(this@CarTrainFragment.context, "position:$currentIndex",
//                        Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun setContentView(): Int {
        return R.layout.fragment_car_train
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        BarUtils.hideStatusBar(this.activity)
        BarUtils.hideActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, true)
    }

    override fun onLazyViewCreate(rootView: View) {
        super.onLazyViewCreate(rootView)

        val carSignView = rootView.findViewById(R.id.car_sign_view) as CarSignView
        carSignView.isReadyToShowAnim = false
        val mRecyclerView = rootView.findViewById(R.id.recycler_view) as CarRecyclerGallery
        val carSimpleAdapter = CarSimpleAdapter()
        val linearLayoutManager = MyLinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        this@CarTrainFragment.carSimpleAdapter = carSimpleAdapter
        this@CarTrainFragment.carSignView = carSignView
        this@CarTrainFragment.mRecyclerView = mRecyclerView
        this@CarTrainFragment.linearLayoutManager = linearLayoutManager

        //pager init
        val snapHelper = MyLinearSnapHelper()
        snapHelper.attachToRecyclerView(mRecyclerView, linearLayoutManager,
                object : OnCallback<Int> {
                    override fun onAction(t: Int) {
                        currentIndex = t + 2
                        for (i in 0 until carSimpleAdapter.itemCount) {
                            val carItem: CarItem = carSimpleAdapter.data!![i] as CarItem
                            if (i == currentIndex) {
                                carItem.startAnim()
                            } else carItem.stopAnim()
                        }
                    }
                },
                object : OnCallback<Int> {
                    override fun onAction(t: Int) {
                        carSignView.setSignStr("type:" + (t + 1))
                        carSignView.playAnim()
                    }
                })

        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = carSimpleAdapter

        val carItemLocomotive = CarItem(R.layout.recycler_item_car_locomotive,
                CarSimpleAdapter.TYPE_ITEM_CAR_Locomotive, true)
        carItemLocomotive.setOnClickListener(null)

        val carItemASG = CarItem(R.layout.recycler_item_car_asg, CarSimpleAdapter.TYPE_ITEM_CAR_ASG)
        carItemASG.setOnClickListener(null)

        val carItemTools = CarItem(R.layout.recycler_item_car_tools, CarSimpleAdapter.TYPE_ITEM_CAR_Tools)
        carItemTools.setOnClickListener(mOnCarClickListener)

        val carItemArt = CarItem(R.layout.recycler_item_car_art, CarSimpleAdapter.TYPE_ITEM_CAR_Art)
        carItemArt.setOnClickListener(mOnCarClickListener)

        val carItemOptimization = CarItem(R.layout.recycler_item_car_optimization, CarSimpleAdapter.TYPE_ITEM_CAR_Optimization)
        carItemOptimization.setOnClickListener(mOnCarClickListener)

        val carItemChildren = CarItem(R.layout.recycler_item_car_children, CarSimpleAdapter.TYPE_ITEM_CAR_Children)
        carItemChildren.setOnClickListener(mOnCarClickListener)

        val carItemEngCheck = CarItem(R.layout.recycler_item_car_eng_check, CarSimpleAdapter.TYPE_ITEM_CAR_Eng_Check)
        carItemEngCheck.setOnClickListener(mOnCarClickListener)

        val carItemMenu = CarItem(R.layout.recycler_item_car_menu, CarSimpleAdapter.TYPE_ITEM_CAR_Menu)
        carItemMenu.setOnClickListener(mOnCarClickListener)

        val carItemClassroom = CarItem(R.layout.recycler_item_car_classroom, CarSimpleAdapter.TYPE_ITEM_CAR_Classroom)
        carItemClassroom.setOnClickListener(mOnCarClickListener)

        val carItemStory = CarItem(R.layout.recycler_item_car_story, CarSimpleAdapter.TYPE_ITEM_CAR_Story)
        carItemStory.setOnClickListener(mOnCarClickListener)

        val carItemMarket = CarItem(R.layout.recycler_item_car_market, CarSimpleAdapter.TYPE_ITEM_CAR_Market)
        carItemMarket.setOnClickListener(mOnCarClickListener)

        val carItemEmpty = CarItem(R.layout.recycler_item_car_empty, CarSimpleAdapter.TYPE_ITEM_CAR_Empty)
        carItemEmpty.setOnClickListener(null)

        carSimpleAdapter.add(carItemLocomotive)
        carSimpleAdapter.add(carItemASG)
        carSimpleAdapter.add(carItemTools)
        carSimpleAdapter.add(carItemArt)
        carSimpleAdapter.add(carItemOptimization)
        carSimpleAdapter.add(carItemChildren)
        carSimpleAdapter.add(carItemEngCheck)
        carSimpleAdapter.add(carItemMenu)
        carSimpleAdapter.add(carItemClassroom)
        carSimpleAdapter.add(carItemStory)
        carSimpleAdapter.add(carItemMarket)
        carSimpleAdapter.add(carItemEmpty)
        carSimpleAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstEnter) {
            isFirstEnter = false
            Looper.myQueue().addIdleHandler {
                //滑动 disable
                linearLayoutManager!!.setScrollHorizontallyEnabled(false)
                //动画
                val anim = TranslateAnimation(ScreenUtils.getScreenWidthByPX(context).toFloat(),
                        0f, 0f, 0f)
                anim.fillAfter = true
                anim.duration = 3000
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        linearLayoutManager?.setScrollHorizontallyEnabled(true)
                        mRecyclerView?.setReadyToScale(true)
                        carSignView?.setSignStr("type:1")
                        carSignView?.isReadyToShowAnim = true
                        carSignView?.playAnim()
                        mRecyclerView?.smoothScrollBy(20, 0)
                        this@CarTrainFragment.anim = null
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
                this@CarTrainFragment.anim = anim
                mRecyclerView?.startAnimation(anim)
                false
            }
        }
    }

    override fun onDestroyView() {
        BarUtils.showStatusBar(this.activity)
        BarUtils.showActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, false)
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (carSimpleAdapter != null) {
            for (i in 0 until carSimpleAdapter!!.itemCount) {
                val carItem = carSimpleAdapter?.data?.get(i) as? CarItem
                carItem?.release()
            }
        }
        carSignView?.release()
        if (anim != null) anim!!.cancel()
        anim = null
    }
}