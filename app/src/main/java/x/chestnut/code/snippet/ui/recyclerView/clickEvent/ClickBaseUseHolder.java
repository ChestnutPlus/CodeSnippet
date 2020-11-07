package x.chestnut.code.snippet.ui.recyclerView.clickEvent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import x.chestnut.code.snippet.R;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/27 23:35
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class ClickBaseUseHolder extends RecyclerView.ViewHolder{

    private final ImageView imageView;
    private final TextView tv;
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
