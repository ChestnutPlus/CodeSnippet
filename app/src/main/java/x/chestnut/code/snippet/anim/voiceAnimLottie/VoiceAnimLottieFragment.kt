package x.chestnut.code.snippet.anim.voiceAnimLottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/18 22:34
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class VoiceAnimLottieFragment : BaseFragment() {

    private var lottieAnimationView: LottieAnimationView? = null

    override fun setContentView(): Int {
        return R.layout.fragment_voice_anim_lottie
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        lottieAnimationView = view.findViewById(R.id.animation_view) as? LottieAnimationView
        return view
    }

    override fun onResume() {
        super.onResume()
        lottieAnimationView?.resumeAnimation()
    }

    override fun onPause() {
        super.onPause()
        lottieAnimationView?.pauseAnimation()
    }
}