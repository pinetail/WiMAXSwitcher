package jp.pinetail.android.wimax_switcher;

import jp.pinetail.android.wimax_switcher.broadcast.ScreenOffReceiver;
import jp.pinetail.android.wimax_switcher.broadcast.ScreenOnReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

public class WiMAXSwitcherActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        checkScreenSettings();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    private void checkScreenSettings() {

        CheckBoxPreference pref = (CheckBoxPreference) findPreference("settings_screen");

        if (pref.isChecked()) {
            registerScreenReceiver();
        } else {
            unregisterScreenReceiver();
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {

        if (key.equals("settings_screen")) {
            CheckBoxPreference pref = (CheckBoxPreference) getPreferenceScreen()
                    .findPreference(key);

            if (pref.isChecked()) {
                registerScreenReceiver();
            } else {
                unregisterScreenReceiver();
            }
        }
    }

    /**
     * 
     */
    private void registerScreenReceiver() {
        Log.d("hoge", "registerScreenReceiver");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(new ScreenOnReceiver(), filter);

        IntentFilter filter2 = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new ScreenOffReceiver(), filter2);
    }

    /**
     * 
     */
    private void unregisterScreenReceiver() {
        Log.d("hoge", "unregisterScreenReceiver");
        try {
            unregisterReceiver(new ScreenOnReceiver());
            unregisterReceiver(new ScreenOffReceiver());
        } catch (IllegalArgumentException e) {
            // e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}