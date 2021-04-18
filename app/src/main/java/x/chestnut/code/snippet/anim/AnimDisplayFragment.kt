package x.chestnut.code.snippet.anim

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.zhouyou.view.seekbar.SignSeekBar
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.ScreenUtils
import java.lang.reflect.Constructor

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/12 23:53
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
abstract class AnimDisplayFragment<T : View> : BaseFragment() {

    private var mAnimConfig: AnimDisplayConfig = AnimDisplayConfig()
    private var mAnimController: ImageView? = null
    private var mSeekBar: SignSeekBar? = null
    private var mDiyView: T? = null
    private var mPause = true
    private var mIsChangeScreen = false

    protected open fun initDiyView(view: T) {}
    abstract fun getDiyViewClass(): Class<T>
    abstract fun setProgress(view: T, progress: Int)
    abstract fun getProgress(view: T): Int
    abstract fun onPlayAnim(view: T)
    abstract fun onPauseAnim(view: T)
    abstract fun onStopAnim(view: T)

    protected open fun getConfig(): AnimDisplayConfig {
        return mAnimConfig
    }

    override fun setContentView(): Int {
        return R.layout.fragment_anim_display
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        mAnimConfig = getConfig()

        //1. init controller
        mAnimController = view.findViewById(R.id.img_view_control)
        mAnimController?.let { controller ->
            controller.setOnClickListener {
                if (mPause) {
                    updatePlayAnimUI()
                    onPlayAnim(mDiyView!!)
                } else {
                    updatePauseAnimUI()
                    onPauseAnim(mDiyView!!)
                }
            }
            controller.setOnLongClickListener {
                mSeekBar?.setProgress(0F)
                updatePauseAnimUI()
                onStopAnim(mDiyView!!)
                true
            }
            controller.visibility = if (mAnimConfig.isShowControlBtn) VISIBLE else INVISIBLE
        }

        //2. init seek bar
        mSeekBar = view.findViewById(R.id.sign_seek_bar)
        mSeekBar?.let { seekBar ->
            seekBar.setOnProgressChangedListener(object : SignSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(signSeekBar: SignSeekBar?, progress: Int,
                                               progressFloat: Float, fromUser: Boolean) {
                    if (fromUser) {
                        mDiyView?.let { diyView ->
                            setProgress(diyView, progress)
                        }
                    }
                }
                override fun getProgressOnActionUp(signSeekBar: SignSeekBar?,
                                                   progress: Int, progressFloat: Float) {}
                override fun getProgressOnFinally(signSeekBar: SignSeekBar?, progress: Int,
                                                  progressFloat: Float, fromUser: Boolean) {}
            })
            seekBar.visibility = if (mAnimConfig.isShowSeekBar) VISIBLE else INVISIBLE
            seekBar.configBuilder
                    .min(mAnimConfig.seekBarMin.toFloat())
                    .max(mAnimConfig.seekBarMax.toFloat())
                    .build()
        }

        //3. init diy view
        val frameLayout = view.findViewById<View>(R.id.layout) as FrameLayout
        val constructor: Constructor<T> = getDiyViewClass()
                .getConstructor(Context::class.java, AttributeSet::class.java)
        val diyView = constructor.newInstance(frameLayout.context, null)
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                mAnimConfig.diyViewWight, mAnimConfig.diyViewHeight)
        layoutParams.gravity = Gravity.CENTER
        diyView.layoutParams = layoutParams
        frameLayout.addView(diyView, 0)
        initDiyView(diyView)
        mDiyView = diyView

        //4. init configs
        mIsChangeScreen = ScreenUtils.setScreenOrientation(activity, mAnimConfig.isLandscape)
        if (mAnimConfig.isAutoPlayAnim) {
            Looper.myQueue().addIdleHandler {
                val delayTime = if(mIsChangeScreen) 1000L else 300L
                mHandler.postDelayed(Runnable {
                    updatePlayAnimUI()
                    onPlayAnim(mDiyView!!)
                }, delayTime)
                false
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mIsChangeScreen) {
            ScreenUtils.setScreenOrientation(activity, !mAnimConfig.isLandscape)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRefreshProgress()
    }

    private val mHandler = Handler()
    private val mRefreshProgress = java.lang.Runnable {
        mSeekBar?.let { seekBar ->
            val progress = getProgress(mDiyView!!)
            if (progress >= seekBar.max) {
                updatePauseAnimUI()
            }
            seekBar.setProgress(progress.toFloat())
            setRefreshProgress()
        }
    }

    private fun setRefreshProgress() {
        mHandler.postDelayed(mRefreshProgress, 50)
    }

    private fun stopRefreshProgress() {
        mHandler.removeCallbacksAndMessages(null)
    }

    private fun updatePlayAnimUI() {
        mPause = false
        mAnimController?.setImageResource(R.drawable.svg_pause)
        //check is the anim is stop and change the ui
        setRefreshProgress()
    }

    private fun updatePauseAnimUI() {
        mPause = true
        mAnimController?.setImageResource(R.drawable.svg_play)
    }
}