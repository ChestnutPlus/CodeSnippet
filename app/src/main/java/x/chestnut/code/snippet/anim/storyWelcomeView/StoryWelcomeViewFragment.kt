package x.chestnut.code.snippet.anim.storyWelcomeView

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
class StoryWelcomeViewFragment : AnimDisplayFragment<StoryWelcomeView>() {

    override fun getConfig(): AnimDisplayConfig {
        val config = super.getConfig()
        config.isShowSeekBar = false
        config.isShowStatusBar = false
        config.isShowActionBar = false
        return config
    }

    override fun getDiyViewClass(): Class<StoryWelcomeView> {
        return StoryWelcomeView::class.java
    }

    override fun setProgress(view: StoryWelcomeView, progress: Int) {
        view.setFactor(progress.toFloat())
    }

    override fun getProgress(view: StoryWelcomeView): Int {
        return view.getFactor().toInt()
    }

    override fun onPlayAnim(view: StoryWelcomeView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: StoryWelcomeView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: StoryWelcomeView) {
        view.stopAnim()
    }

    override fun onRelease(view: StoryWelcomeView) {
        view.stopAnim()
    }
}