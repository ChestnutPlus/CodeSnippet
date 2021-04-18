package x.chestnut.code.snippet.anim.lottie

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
 *     time  : 2021/4/18 23:44
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class LottieShowFragment : BaseFragment() {

    private var lottieAnimationView: LottieAnimationView? = null
    private val animRes = arrayOfNulls<String>(6)
    private var index = 0

    init {
        animRes[0] = "anim/lottie_1.json"
        animRes[1] = "anim/lottie_2.json"
        animRes[2] = "anim/lottie_3.json"
        animRes[3] = "anim/lottie_4.json"
        animRes[4] = "anim/lottie_5.json"
        animRes[5] = "anim/lottie_6.json"
    }

    override val actionBarTitle: String
        get() = "Lottie Show"

    override fun setContentView(): Int {
        return R.layout.fragment_lottie
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val lottieAnimView = view.findViewById(R.id.animation_view) as LottieAnimationView
        lottieAnimView.setAnimation(animRes[index])
        lottieAnimView.playAnimation()
        lottieAnimView.setOnClickListener {
            index++
            if (index >= animRes.size) {
                index = 0
            }
            lottieAnimView.setAnimation(animRes[index])
            lottieAnimView.playAnimation()
        }
        lottieAnimationView = lottieAnimView
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