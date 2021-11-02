package edu.neu.mad_sea.xinyizhu.TodoApp.notification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    NotificationHelper notificationHelper = new NotificationHelper(context);
    NotificationCompat.Builder builder = notificationHelper.getChannelNotification();
    notificationHelper.getManager().notify(1, builder.build());
  }
}
