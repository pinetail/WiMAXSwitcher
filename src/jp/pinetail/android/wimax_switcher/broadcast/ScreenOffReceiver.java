package jp.pinetail.android.wimax_switcher.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * スクリーンOFF時のレシーバー
 * 
 * @author Shinichi Matsuo
 *
 */
public class ScreenOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d("hoge", "screenOff");

        // スクリーンがOFFになった場合
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {

            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(context);

            boolean screenFlg = pref.getBoolean("settings_screen", false);

            if (screenFlg == false) {
                return;
            }
            
            // サービス呼び出し(WIFI接続時のオンライン処理)
            Intent serviceIntent = new Intent(context, WiMAXService.class);
            serviceIntent.putExtra("screenStatus", "ScreenOff");
            context.startService(serviceIntent);
        }
    }

}
