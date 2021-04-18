package x.chestnut.code.snippet.anim.carSign

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
class CarAdvancedSignFragment : AnimDisplayFragment<CarAdvancedSignView>() {

    override val actionBarTitle: String
        get() = "CarAdvancedSign"

    override fun initDiyView(view: CarAdvancedSignView) {
        view.setSignStr("栗子家")
    }

    override fun getDiyViewClass(): Class<CarAdvancedSignView> {
        return CarAdvancedSignView::class.java
    }

    override fun setProgress(view: CarAdvancedSignView, progress: Int) {
        view.progress = progress.toFloat()
    }

    override fun getProgress(view: CarAdvancedSignView): Int {
        return view.progress.toInt()
    }

    override fun onPlayAnim(view: CarAdvancedSignView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: CarAdvancedSignView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: CarAdvancedSignView) {
        view.stopAnim()
    }
}