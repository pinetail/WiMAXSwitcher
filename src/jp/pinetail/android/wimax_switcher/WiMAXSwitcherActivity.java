package jp.pinetail.android.wimax_switcher;

import jp.pinetail.android.wimax_switcher.broadcast.ScreenOffReceiver;
import jp.pinetail.android.wimax_switcher.broadcast.ScreenOnReceiver;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class WiMAXSwitcherActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(new ScreenOnReceiver(), filter);
        
        IntentFilter filter2 = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new ScreenOffReceiver(), filter2);

    }
}