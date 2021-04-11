package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base

import android.view.ViewGroup

/**
 * <pre>
 * author: Chestnut
 * blog  : https://juejin.im/user/676954892408824
 * time  : 2017/4/27 22:48
 * desc  :
 * thanks To:
 * dependent on:
 * update log:
</pre> *
 */
abstract class XItem<DATA>(var data: DATA) {
    /**
     * 1.创建视图
     * @param parent    parent
     * @param viewType  type
     * @return  holder
     */
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XHolder

    /**
     * 2.数据绑定
     * @param holder    视图拥有者
     * @param position  位置
     */
    abstract fun onBindViewHolder(holder: XHolder, position: Int)

    /**
     * 3.获取ViewType
     * @return  type
     */
    abstract val itemType: Int

    /**
     * 4. 释放资源
     * 如果有需要回收的资源，子类自己实现
     */
    abstract fun releaseRes()
}