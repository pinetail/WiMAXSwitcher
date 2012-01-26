package jp.pinetail.android.wimax_switcher.broadcast;

import java.lang.reflect.Method;

import jp.pinetail.android.wimax_switcher.WiMAXApplication;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * WiMAXサービス
 * @author Shinichi Matsuo
 *
 */
public class WiMAXService extends Service {
    static final String TAG = "WiMAXService";

    public static final int STATE_DISABLED = 0;
    public static final int STATE_ENABLED = 1;
    public static final int STATE_INTERMEDIATE = 2;

    private static final String CONTEXT_WIMAX_SERVICE = "wimax";

    private static final int WIMAX_STATE_DISABLING = 0;
    private static final int WIMAX_STATE_DISABLED = 1;
    private static final int WIMAX_STATE_ENABLING = 2;
    private static final int WIMAX_STATE_ENABLED = 3;

    private WiMAXApplication app;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public void onCreate() {
        app = (WiMAXApplication) getApplication();
    }

    @Override
    public void onStart(Intent intent, int StartId) {
        int status = getWimaxState(getApplicationContext());

        Bundle extras = intent.getExtras();

        if (extras.containsKey("networkStatus")) {
            boolean nwStatus = extras.getBoolean("networkStatus");

            Log.d(TAG, "network:" + nwStatus);

            // WiFiに接続 and WiMAX ON
            if (nwStatus == true) {
                app.setWifiStatus(status);
                if (status == STATE_ENABLED) {
                    setWimaxDisabled(getApplicationContext());
                }
            } else if (nwStatus == false) {
                if (status == STATE_DISABLED
                        && app.getWifiStatus() == STATE_ENABLED) {
                    setWimaxEnabled(getApplicationContext());
                }
            }
        } else if (extras.containsKey("screenStatus")) {

            Log.d(TAG, "screenStatus:" + extras.getString("screenStatus") + " status:" + status);

            if (extras.getString("screenStatus").equals("ScreenOn")) {
                if (app.getScreenStatus() == STATE_ENABLED) {
                    setWimaxEnabled(getApplicationContext());
                }
                
            } else if (extras.getString("screenStatus").equals("ScreenOff")) {
                app.setScreenStatus(status);
                
                // WiMAXがONの場合
                if (status == STATE_ENABLED) {
                    setWimaxDisabled(getApplicationContext());
                }
            }
        }

        stopSelf();
    }

    /**
     * WiMAXの状態取得メソッド
     * 
     * @param context
     * @return
     */
    private static int getWimaxState(Context context) {
        int wimaxState = WIMAX_STATE_DISABLED;

        try {
            Object wimaxManager = context
                    .getSystemService(CONTEXT_WIMAX_SERVICE);
            Method getWimaxState = wimaxManager.getClass().getMethod(
                    "getWimaxState", (Class[]) null);
            wimaxState = (Integer) getWimaxState.invoke(wimaxManager,
                    (Object[]) null);
        } catch (Exception e) {
            Log.e(TAG, "could not get wimax state", e);
            wimaxState = WIMAX_STATE_DISABLED;
        }

        if (wimaxState == WIMAX_STATE_DISABLED
                || wimaxState == WIMAX_STATE_DISABLING) {
            return STATE_DISABLED;
        } else if (wimaxState == WIMAX_STATE_ENABLED
                || wimaxState == WIMAX_STATE_ENABLING) {
            return STATE_ENABLED;
        } else {
            return STATE_INTERMEDIATE;
        }
    }

    /**
     * WiMAXを有効にするメソッド
     * 
     * @param context
     */
    private void setWimaxEnabled(Context context) {

        int wimaxState = getWimaxState(context);

        try {
            Object wimaxManager = context
                    .getSystemService(CONTEXT_WIMAX_SERVICE);
            Method setWimaxEnabled = wimaxManager.getClass().getMethod(
                    "setWimaxEnabled", new Class[] { Boolean.TYPE });

            if (wimaxState == STATE_DISABLED) {
                setWimaxEnabled.invoke(wimaxManager,
                        new Object[] { Boolean.TRUE });
            }
        } catch (Exception e) {
            Log.e(TAG, "could not toggle wimax state", e);
            return;
        }

    }

    /**
     * WiMAXを無効にするメソッド
     * 
     * @param context
     */
    private void setWimaxDisabled(Context context) {
        int wimaxState = getWimaxState(context);

        try {
            Object wimaxManager = context
                    .getSystemService(CONTEXT_WIMAX_SERVICE);
            Method setWimaxEnabled = wimaxManager.getClass().getMethod(
                    "setWimaxEnabled", new Class[] { Boolean.TYPE });

            if (wimaxState == STATE_ENABLED) {
                setWimaxEnabled.invoke(wimaxManager,
                        new Object[] { Boolean.FALSE });
            }
        } catch (Exception e) {
            Log.e(TAG, "could not toggle wimax state", e);
            return;
        }
    }

}
