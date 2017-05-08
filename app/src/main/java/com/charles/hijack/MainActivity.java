package com.charles.hijack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, HijackService.class);
        startService(intent);
        Toast.makeText(this, "Hijack start", Toast.LENGTH_SHORT).show();
    }
}
