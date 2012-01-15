package jp.pinetail.android.wimax_switcher.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class WiFiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {

            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(context);

            boolean wifiFlg = pref.getBoolean("settings_wifi", false);

            if (wifiFlg == false) {
                return;
            }

            Bundle extras = intent.getExtras();
            if (extras != null) {
                NetworkInfo info = extras
                        .getParcelable(WifiManager.EXTRA_NETWORK_INFO);

                if (info != null) {
                    // サービス呼び出し(WIFI接続時のオンライン処理)
                    Intent serviceIntent = new Intent(context,
                            WiMAXService.class);
                    serviceIntent.putExtra("networkStatus", info.isConnected());
                    context.startService(serviceIntent);
                }
            }
        }
    }
}
