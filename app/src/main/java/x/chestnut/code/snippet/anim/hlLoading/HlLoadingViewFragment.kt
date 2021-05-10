package x.chestnut.code.snippet.anim.hlLoading

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
class HlLoadingViewFragment : AnimDisplayFragment<HlLoadingView>() {

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isShowSeekBar = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        config.seekBarMin = HlLoadingView.FACTOR_START.toInt()
        config.seekBarMax = HlLoadingView.FACTOR_END.toInt()
        return config
    }

    override fun getDiyViewClass(): Class<HlLoadingView> {
        return HlLoadingView::class.java
    }

    override fun setProgress(view: HlLoadingView, progress: Int) {
        view.setFactor(progress.toFloat())
    }

    override fun getProgress(view: HlLoadingView): Int {
        return view.getFactor().toInt()
    }

    override fun onPlayAnim(view: HlLoadingView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: HlLoadingView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: HlLoadingView) {
        view.stopAnim()
    }

    override fun onRelease(view: HlLoadingView) {
        view.release()
    }
}