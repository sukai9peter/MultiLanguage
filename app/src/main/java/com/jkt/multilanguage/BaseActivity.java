package com.jkt.multilanguage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by 天哥哥 on 2017/2/2 0026.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
