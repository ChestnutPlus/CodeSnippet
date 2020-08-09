package x.chestnut.code.snippet.ui.recyclerView.multiItem;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

public class ViewHolderItemImgTxt extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView tv;

    public ViewHolderItemImgTxt(@NonNull View itemView) {
        super(itemView);
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
