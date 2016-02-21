package modularity.coderdojoevents.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.ArrayList;
import java.util.List;

import modularity.coderdojoevents.Activities.ActivityEventTabbed;
import modularity.coderdojoevents.Adapters.FormatHelper;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 21/02/2016.
 */
public class NotificationHelper {

    private static int id = 0;

    public static void showEventNotification(Context context, Events event) {
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notif_dojo)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setContentTitle(FormatHelper.formatTitle(event.getName().getText()))
                        .setContentText(FormatHelper.formatDate(event.getStart(), event.getEnd()));

        Intent resultIntent = new Intent(context, ActivityEventTabbed.class);

        resultIntent.putExtra("event", event);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(ActivityEventTabbed.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        nBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(id++, nBuilder.build());
    }

    public static List<Events> getDifferences(Events[] oldEvents, Events[] newEvents) {
        List<Events> newsList = new ArrayList<>();
        for (Events n : newEvents)
            if (!contains(oldEvents, n.getId()))
                newsList.add(n);
        return newsList;
    }

    private static boolean contains(Events[] lst, String id) {
        for (Events e : lst)
            if (e.getId().equals(id))
                return true;
        return false;
    }
}
