package x.chestnut.code.snippet.anim.redRibbon

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
class RedRibbonViewFragment : AnimDisplayFragment<RedRibbonView>() {

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isAutoPlayAnim = false
        config.isShowControlBtn = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        config.seekBarMin = 0
        config.seekBarMax = 4
        config.diyViewWight = 500
        config.diyViewHeight = 500
        return config
    }

    override fun getDiyViewClass(): Class<RedRibbonView> {
        return RedRibbonView::class.java
    }

    override fun setProgress(view: RedRibbonView, progress: Int) {
        view.factor = progress.toFloat()
    }

    override fun getProgress(view: RedRibbonView): Int {
        return 0
    }

    override fun onPlayAnim(view: RedRibbonView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: RedRibbonView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: RedRibbonView) {
        view.stopAnim()
    }

    override fun onRelease(view: RedRibbonView) {
        view.stopAnim()
    }
}