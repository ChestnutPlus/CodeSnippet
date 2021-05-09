package x.chestnut.code.snippet.anim.carTrain.recycler.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import x.chestnut.code.snippet.R
import x.chestnut.code.snippet.anim.carTrain.recycler.OnCallback
import x.chestnut.code.snippet.anim.carTrain.recycler.adapter.CarSimpleAdapter
import x.chestnut.code.snippet.anim.carView.CarBaseView
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XHolder
import x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base.XItem

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/7/31 11:41
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class CarItem @JvmOverloads constructor(@LayoutRes integer: Int?,
                                        override val itemType: Int,
                                        isAnimAfterFirstBindView: Boolean = false
) : XItem<Int?>(integer) {
    private var carBaseView: CarBaseView? = null
    private var isAnimAfterFirstBindView = false
    private var onClickListener: OnCallback<Int>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): XHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(data!!, null)
        view.tag = itemType
        return XHolder(view)
    }

    override fun onBindViewHolder(xHolder: XHolder, i: Int) {
        if (itemType != CarSimpleAdapter.TYPE_ITEM_CAR_Empty) {
            carBaseView = xHolder.getViewById(R.id.car_view) as CarBaseView
            carBaseView?.setOnBodyClickListener(object : CarBaseView.OnBodyClickListener {
                override fun onClick() {
                    onClickListener?.onAction(itemType)
                }
            })
        }
        if (isAnimAfterFirstBindView) {
            startAnim()
            isAnimAfterFirstBindView = false
        }
    }

    fun setOnClickListener(onClickListener: OnCallback<Int>?) {
        this.onClickListener = onClickListener
    }

    fun startAnim() {
        if (carBaseView != null) carBaseView!!.startAnim()
    }

    fun stopAnim() {
        if (carBaseView != null) carBaseView!!.stopAnim()
    }

    override fun releaseRes() {}
    fun release() {
        carBaseView?.release()
    }

    init {
        this.isAnimAfterFirstBindView = isAnimAfterFirstBindView
    }
}