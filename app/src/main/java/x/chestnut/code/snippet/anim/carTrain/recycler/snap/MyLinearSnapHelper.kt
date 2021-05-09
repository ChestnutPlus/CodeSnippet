package x.chestnut.code.snippet.anim.carTrain.recycler.snap

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import x.chestnut.code.snippet.anim.carTrain.recycler.OnCallback

/**
 * <pre>
 * author: Chestnut
 * blog  : http://www.jianshu.com/u/a0206b5f4526
 * time  : 2018/8/3 10:49
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
class MyLinearSnapHelper : LinearSnapHelper() {

    private var currentPager = -1
    private var mayToPosition = -1

    fun attachToRecyclerView(recyclerView: RecyclerView?,
                             linearLayoutManager: LinearLayoutManager,
                             pagerChangeCallback: OnCallback<Int>?,
                             onScrolledPosition: OnCallback<Int>?) {
        super.attachToRecyclerView(recyclerView)
        currentPager = linearLayoutManager.findFirstVisibleItemPosition()
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val newPager = linearLayoutManager.findFirstVisibleItemPosition()
                if (pagerChangeCallback != null && newPager != currentPager) {
                    currentPager = newPager
                    pagerChangeCallback.onAction(currentPager)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = linearLayoutManager.findFirstVisibleItemPosition()
                if (position != mayToPosition) {
                    mayToPosition = position
                    onScrolledPosition?.onAction(position)
                }
            }
        })
    }
}