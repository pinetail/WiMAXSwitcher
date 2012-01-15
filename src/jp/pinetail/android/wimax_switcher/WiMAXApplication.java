package jp.pinetail.android.wimax_switcher;

import jp.pinetail.android.wimax_switcher.broadcast.WiMAXService;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class WiMAXApplication extends Application {

    private int wimaxStatus;

    private SharedPreferences pref;

    @Override
    public void onCreate() {
        super.onCreate();

        pref = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
    }

    public int getWimaxWifiStatus() {
        return pref.getInt("wimax_wifi_status", WiMAXService.STATE_INTERMEDIATE);
    }

    public void setWimaxWifiStatus(int wimaxStatus) {

        Editor editor = pref.edit();

        editor.putInt("wimax_wifi_status", wimaxStatus);
        editor.commit();
    }
    
    public int getWimaxScreenStatus() {
        return pref.getInt("wimax_screen_status", WiMAXService.STATE_INTERMEDIATE);
    }

    public void setWimaxScreenStatus(int wimaxStatus) {

        Editor editor = pref.edit();

        editor.putInt("wimax_screen_status", wimaxStatus);
        editor.commit();
    }


}
