package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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

public class ViewHolderItemImg extends RecyclerView.ViewHolder{

    private ImageView imageView;

    public ViewHolderItemImg(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img);
    }

    public void setImageView(@DrawableRes int imageView) {
        this.imageView.setImageResource(imageView);
    }
}
