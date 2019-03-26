package x.chestnut.code.snippet.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
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