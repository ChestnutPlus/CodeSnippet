package x.chestnut.code.snippet.anim.carView

import x.chestnut.code.snippet.anim.AnimDisplayConfig
import x.chestnut.code.snippet.anim.AnimDisplayFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/16 23:36
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class CarMarketFragment : AnimDisplayFragment<CarMarketView>() {

    override val actionBarTitle: String
        get() = "车厢扭曲动画"

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isShowSeekBar = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        return config
    }

    override fun getDiyViewClass(): Class<CarMarketView> {
        return CarMarketView::class.java
    }

    override fun setProgress(view: CarMarketView, progress: Int) {}

    override fun getProgress(view: CarMarketView): Int {
        return 0
    }

    override fun onPlayAnim(view: CarMarketView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: CarMarketView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: CarMarketView) {}

    override fun onRelease(view: CarMarketView) {
        view.stopAnim()
    }
}