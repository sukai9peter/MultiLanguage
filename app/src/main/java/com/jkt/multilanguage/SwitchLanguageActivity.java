package com.jkt.multilanguage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SwitchLanguageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_language);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chinese:
                LanguageUtil.updateLocale(this, LanguageUtil.LOCALE_CHINESE);
                ActivityManager.getInstance().recreateAllOtherActivity(this);
                Toast.makeText(this, "change to chinese", Toast.LENGTH_SHORT).show();
                break;
            case R.id.english:
                LanguageUtil.updateLocale(this, LanguageUtil.LOCALE_ENGLISH);
                ActivityManager.getInstance().recreateAllOtherActivity(this);
                Toast.makeText(this, "change to english", Toast.LENGTH_SHORT).show();
                break;
            case R.id.russian:
                LanguageUtil.updateLocale(this, LanguageUtil.LOCALE_RUSSIAN);
                ActivityManager.getInstance().recreateAllOtherActivity(this);
                Toast.makeText(this, "change to russian", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
