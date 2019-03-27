package x.chestnut.code.snippet.ui.recyclerView.baseUse;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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

public class BaseUseHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;

    public BaseUseHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img);
    }

    public void setImageView(@DrawableRes int imageView) {
        this.imageView.setImageResource(imageView);
    }

}
