package jp.pinetail.android.wimax_switcher;

import jp.pinetail.android.wimax_switcher.broadcast.WiMAXService;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * アプリケーション
 * 
 * @author Shinichi Matsuo
 * 
 */
public class WiMAXApplication extends Application {

    private SharedPreferences pref;

    @Override
    public void onCreate() {
        super.onCreate();

        pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
    }

    /**
     * WiMAXの状態を取得
     * 
     * @return
     */
    public int getWifiStatus() {
        return pref
                .getInt("wimax_wifi_status", WiMAXService.STATE_INTERMEDIATE);
    }

    /**
     * WiFiオン／オフ時のWiMAXの状態を保存
     * 
     * @param wimaxStatus
     */
    public void setWifiStatus(int wimaxStatus) {

        Editor editor = pref.edit();

        editor.putInt("wimax_wifi_status", wimaxStatus);
        editor.commit();
    }

    /**
     * WiMAXの状態を取得
     * 
     * @return
     */
    public int getScreenStatus() {
        return pref.getInt("wimax_screen_status",
                WiMAXService.STATE_INTERMEDIATE);
    }

    /**
     * スクリーンオン／オフ時のWiMAXの状態を保存
     * 
     * @param wimaxStatus
     */
    public void setScreenStatus(int wimaxStatus) {

        Editor editor = pref.edit();

        editor.putInt("wimax_screen_status", wimaxStatus);
        editor.commit();
    }

}
