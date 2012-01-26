package jp.pinetail.android.wimax_switcher.broadcast;

import jp.pinetail.android.wimax_switcher.WiMAXNotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * 端末起動時のレシーバー
 * 
 * @author Shinichi Matsuo
 * 
 */
public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d("hoge", "bootCompleted");

        // 画面の電源が入ったらActivityを起動
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            // スクリーンONレシーバーの登録
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            context.registerReceiver(new ScreenOnReceiver(), filter);

            // スクリーンOFFレシーバーの登録
            IntentFilter filter2 = new IntentFilter(Intent.ACTION_SCREEN_OFF);
            context.registerReceiver(new ScreenOffReceiver(), filter2);

            // 通知バーの表示
            WiMAXNotificationManager.notify(context);
        }
    }



}
