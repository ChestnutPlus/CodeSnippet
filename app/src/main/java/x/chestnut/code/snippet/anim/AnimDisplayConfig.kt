package x.chestnut.code.snippet.anim

import android.widget.FrameLayout

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2021/4/16 23:32
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
data class AnimDisplayConfig(
        var isShowActionBar: Boolean = false,
        var isShowStatusBar: Boolean = true,
        var isAutoPlayAnim: Boolean = true,
        var isLandscape: Boolean = true,
        var isShowSeekBar: Boolean = true,
        var isShowControlBtn: Boolean = true,
        var diyViewHeight: Int = FrameLayout.LayoutParams.MATCH_PARENT,
        var diyViewWight: Int = FrameLayout.LayoutParams.MATCH_PARENT,
        var seekBarMax: Int = 100,
        var seekBarMin: Int = 0,
)
