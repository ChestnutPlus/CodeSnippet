package x.chestnut.code.snippet.ui.recyclerView.xItemPackage.base;

import android.view.ViewGroup;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2017/4/27 22:48
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public abstract class XItem<DATA> {

    public DATA data;   //数据实体，数据Bean

    public XItem(DATA data) {
        this.data = data;
    }

    /**
     * 1.创建视图
     * @param parent    parent
     * @param viewType  type
     * @return  holder
     */
    public abstract XHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 2.数据绑定
     * @param holder    视图拥有者
     * @param position  位置
     */
    public abstract void onBindViewHolder(XHolder holder, int position);

    /**
     * 3.获取ViewType
     * @return  type
     */
    public abstract int getItemType();

    /**
     * 4. 释放资源
     *  如果有需要回收的资源，子类自己实现
     */
    public abstract void releaseRes();
}
