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
class CarSignFragment : AnimDisplayFragment<CarSignView>() {

    override val actionBarTitle: String
        get() = "CarSign"

    override fun getDiyViewClass(): Class<CarSignView> {
        return CarSignView::class.java
    }

    override fun setFactor(view: CarSignView, factor: Int) {
        view.setFactoryByUser(factor.toFloat())
    }

    override fun getFactor(view: CarSignView): Int {
        return view.getFactor().toInt()
    }

    override fun onPlayAnim(view: CarSignView) {
        view.playAnim()
    }

    override fun onPauseAnim(view: CarSignView) {
        view.pauseAnim()
    }

    override fun onStopAnim(view: CarSignView) {
        view.stopAnim()
    }
}