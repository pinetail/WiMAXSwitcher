package jp.pinetail.android.wimax_switcher.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ScreenOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        Log.d("hoge", "screenOn");

        // 画面の電源が入ったらActivityを起動
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo info = cm.getActiveNetworkInfo();
//
//            if (info != null) {
                // サービス呼び出し(WIFI接続時のオンライン処理)
                Intent serviceIntent = new Intent(context, WiMAXService.class);
                serviceIntent.putExtra("screenStatus", "ScreenOn");
                context.startService(serviceIntent);
//            }
        }
    }

}
