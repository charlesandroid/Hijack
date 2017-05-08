package com.charles.hijack;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * com.charles.hijack.HijackTest
 *
 * @author Just.T
 * @since 17/5/8
 */
public class HijackTest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijack);

    }
}
