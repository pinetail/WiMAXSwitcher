package jp.pinetail.android.wimax_switcher.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ScreenOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d("hoge", "screenOff");

        // 画面の電源が入ったらActivityを起動
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            
//            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            NetworkInfo info = cm.getActiveNetworkInfo();
//
//            if (info != null) {
                // サービス呼び出し(WIFI接続時のオンライン処理)
                Intent serviceIntent = new Intent(context, WiMAXService.class);
                serviceIntent.putExtra("screenStatus", "ScreenOff");
                context.startService(serviceIntent);
//            }
        }
    }

}
