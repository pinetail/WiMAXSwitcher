package jp.pinetail.android.wimax_switcher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class WiMAXNotificationManager {

    public static void notify(Context context) {
        NotificationManager mManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n = new Notification();
        n.icon = R.drawable.icon;
        n.tickerText = "WiFi!!";
        n.flags = Notification.FLAG_ONGOING_EVENT;
        n.setLatestEventInfo(context,
                context.getResources().getString(R.string.app_name), "Wifi!!",
                contentIntent(context));
        mManager.notify(R.string.app_name, n);
    }

    public static void cancel(Context context) {
        NotificationManager mManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mManager.cancel(R.string.app_name);
    }

    public static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, WiMAXSwitcherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        return pi;
    }
}
