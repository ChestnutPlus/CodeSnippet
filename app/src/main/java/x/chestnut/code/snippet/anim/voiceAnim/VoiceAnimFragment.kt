package x.chestnut.code.snippet.anim.voiceAnim

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
class VoiceAnimFragment : AnimDisplayFragment<VoiceAnimView>() {

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isShowSeekBar = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        return config
    }

    override fun getDiyViewClass(): Class<VoiceAnimView> {
        return VoiceAnimView::class.java
    }

    override fun setProgress(view: VoiceAnimView, progress: Int) {}

    override fun getProgress(view: VoiceAnimView): Int {
        return 0
    }

    override fun onPlayAnim(view: VoiceAnimView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: VoiceAnimView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: VoiceAnimView) {
        view.stopAnim()
    }

    override fun onRelease(view: VoiceAnimView) {
        view.stopAnim()
    }
}