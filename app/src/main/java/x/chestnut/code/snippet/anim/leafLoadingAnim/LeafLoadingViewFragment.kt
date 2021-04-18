package x.chestnut.code.snippet.anim.leafLoadingAnim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.base.BaseFragment
import x.chestnut.code.snippet.utils.ScreenUtils

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/18 22:58
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
class LeafLoadingViewFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_leaf_loading
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val leafLoadingView = view.findViewById(R.id.leaf) as LeafLoadingView

        //progress
        val tvProgress = view.findViewById(R.id.tv_progress) as TextView
        (view.findViewById(R.id.seek_bar_progress) as SeekBar)
                .setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                        leafLoadingView.setProgress(i)
                        tvProgress.text = "progress：{x}".replace("{x}", i.toString())
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })

        //fan-bg-margin
        val tvFanBgMargin = view.findViewById(R.id.tv_fan_bg_margin) as TextView
        (view.findViewById(R.id.seek_bar_fan_bg_margin) as SeekBar)
                .setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                        leafLoadingView.setFanBgMargin(i)
                        tvFanBgMargin.text = "fan-bg-margin：{x}".replace("{x}", i.toString())
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })

        //fan-speed
        val tvSpeed = view.findViewById(R.id.tv_fan_speed) as TextView
        (view.findViewById(R.id.seek_bar_fan_speed) as SeekBar)
                .setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                        leafLoadingView.setFanSpeedFactor(i)
                        tvSpeed.text = "fan-speed：{x}".replace("{x}", i.toString())
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })

        //leaf-size
        val tvLeafSize = view.findViewById(R.id.tv_leaf_size) as TextView
        (view.findViewById(R.id.seek_bar_leaf_size) as SeekBar)
                .setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                        leafLoadingView.setLeafSize(i)
                        tvLeafSize.text = "leaf-size：{x}".replace("{x}", i.toString())
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })

        return view
    }
}