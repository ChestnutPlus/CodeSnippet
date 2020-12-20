package x.chestnut.code.snippet.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : https://juejin.im/user/676954892408824
 *     time  : 2019/3/24 23:26
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Looper.myQueue().addIdleHandler(() -> {
            lazyLoadViewAfterOnResume();
            return false;
        });
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void lazyLoadViewAfterOnResume();

    public void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setTitle(title);
        }
    }

    protected void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }
}
