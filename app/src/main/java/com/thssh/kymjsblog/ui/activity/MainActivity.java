package com.thssh.kymjsblog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.SkinActivity;

public class MainActivity extends SkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void actionMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
