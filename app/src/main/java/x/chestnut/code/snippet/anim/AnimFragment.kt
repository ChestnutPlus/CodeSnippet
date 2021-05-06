package x.chestnut.code.snippet.anim

import android.view.View
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carSign.CarAdvancedSignFragment
import x.chestnut.code.snippet.anim.carSign.CarSignFragment
import x.chestnut.code.snippet.anim.carView.CarMarketFragment
import x.chestnut.code.snippet.anim.countNumberView.CountNumberViewFragment
import x.chestnut.code.snippet.anim.leafLoadingAnim.LeafLoadingViewFragment
import x.chestnut.code.snippet.anim.lottie.LottieShowFragment
import x.chestnut.code.snippet.anim.redRibbon.RedRibbonViewFragment
import x.chestnut.code.snippet.anim.voiceAnim.VoiceAnimFragment
import x.chestnut.code.snippet.anim.voiceAnimLottie.VoiceAnimLottieFragment
import x.chestnut.code.snippet.anim.voiceDbChangeView.VoiceDbChangeViewFragment
import x.chestnut.code.snippet.base.ScrollBaseFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/10 12:24
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class AnimFragment : ScrollBaseFragment() {

    override val actionBarTitleId: Int
        get() = R.string.title_anim

    override fun onLazyViewCreate(rootView: View) {
        addView("CountNumberView") { startFragment(CountNumberViewFragment()) }
        addView("CarSign") { startFragment(CarSignFragment()) }
        addView("CarAdvancedSign") { startFragment(CarAdvancedSignFragment()) }
        addView("车厢扭曲动画") { startFragment(CarMarketFragment()) }
        addView("波普动画") { startFragment(VoiceAnimFragment()) }
        addView("波普动画-Lottie") { startFragment(VoiceAnimLottieFragment()) }
        addView("叶子 loading 动画") { startFragment(LeafLoadingViewFragment()) }
        addView("Lottie Show") { startFragment(LottieShowFragment()) }
        addView("语音变化View") { startFragment(VoiceDbChangeViewFragment()) }
        addView("红领巾动画") { startFragment(RedRibbonViewFragment()) }
    }
}