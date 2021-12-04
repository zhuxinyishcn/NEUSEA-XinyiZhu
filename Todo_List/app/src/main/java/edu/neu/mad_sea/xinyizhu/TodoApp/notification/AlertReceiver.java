package edu.neu.mad_sea.xinyizhu.TodoApp.notification;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import edu.neu.mad_sea.xinyizhu.TodoApp.MainActivity;

public class AlertReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent i = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
    NotificationHelper notificationHelper = new NotificationHelper(context);
    NotificationCompat.Builder builder = notificationHelper.getChannelNotification()
        .setContentIntent(pendingIntent);
    notificationHelper.getManager().notify(1234, builder.build());
  }
}
