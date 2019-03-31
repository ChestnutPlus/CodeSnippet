package x.chestnut.code.snippet.ui.recyclerView.clickEvent;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import x.chestnut.code.snippet.R;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2019/3/27 23:35
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ClickBaseUseHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView tv;
    View layout;

    public ClickBaseUseHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.layout);
        imageView = itemView.findViewById(R.id.img);
        tv = itemView.findViewById(R.id.tv);
    }

    public void setImageView(@DrawableRes int imageView) {
        this.imageView.setImageResource(imageView);
    }

    public void setContent(String content) {
        this.tv.setText(content);
    }
}