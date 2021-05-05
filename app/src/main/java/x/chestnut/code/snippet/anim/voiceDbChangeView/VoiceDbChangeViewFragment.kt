package x.chestnut.code.snippet.anim.voiceDbChangeView

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
class VoiceDbChangeViewFragment : AnimDisplayFragment<VoiceDBChangeView>() {

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isAutoPlayAnim = false
        config.isShowControlBtn = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        return config
    }

    override fun getDiyViewClass(): Class<VoiceDBChangeView> {
        return VoiceDBChangeView::class.java
    }

    override fun setProgress(view: VoiceDBChangeView, progress: Int) {
        view.factor = progress.toFloat()
    }

    override fun getProgress(view: VoiceDBChangeView): Int {
        return 0
    }

    override fun onPlayAnim(view: VoiceDBChangeView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: VoiceDBChangeView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: VoiceDBChangeView) {
        view.stopAnim()
    }

    override fun onRelease(view: VoiceDBChangeView) {
        view.stopAnim()
    }
}