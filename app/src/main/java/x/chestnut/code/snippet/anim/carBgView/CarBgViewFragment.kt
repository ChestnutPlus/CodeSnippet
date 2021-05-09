package x.chestnut.code.snippet.anim.carBgView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.BarUtils
import x.chestnut.code.snippet.utils.ScreenUtils

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/5/9 11:31
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class CarBgViewFragment : BaseFragment() {

    private var carBgScrollView: CarBgScrollView? = null
    private var carGrassLandView: CarGrassLandView? = null

    override fun setContentView(): Int {
        return R.layout.fragment_car_bg_view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        BarUtils.hideStatusBar(this.activity)
        BarUtils.hideActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, true)
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        carBgScrollView = rootView.findViewById(R.id.car_bg_scroll_view) as CarBgScrollView
        carGrassLandView = rootView.findViewById(R.id.car_grass_land_sign_view) as CarGrassLandView
        return rootView
    }

    override fun onResume() {
        super.onResume()
        carBgScrollView?.playAnim()
        carGrassLandView?.startAnim()
    }

    override fun onPause() {
        super.onPause()
        carBgScrollView?.stopAnim()
        carGrassLandView?.stopAnim()
    }

    override fun onDestroyView() {
        BarUtils.showStatusBar(this.activity)
        BarUtils.showActionBar(this.activity)
        ScreenUtils.setScreenOrientation(activity, false)
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        carBgScrollView?.release()
        carGrassLandView?.release()
    }
}
