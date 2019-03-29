package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import x.chestnut.code.snippet.R;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/29 22:13
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ViewHolderItemTxt extends RecyclerView.ViewHolder{

    private TextView tv;

    public ViewHolderItemTxt(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    public void setContent(String content) {
        this.tv.setText(content);
    }
}
