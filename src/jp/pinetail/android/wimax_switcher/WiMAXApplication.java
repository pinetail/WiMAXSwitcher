package jp.pinetail.android.wimax_switcher;

import android.app.Application;

public class WiMAXApplication extends Application {

    private int wimaxStatus;

    public int getWimaxStatus() {
        return wimaxStatus;
    }

    public void setWimaxStatus(int wimaxStatus) {
        this.wimaxStatus = wimaxStatus;
    }

}
