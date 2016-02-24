package modularity.coderdojoevents.Notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import modularity.coderdojoevents.Activities.ActivityEventTabbed;
import modularity.coderdojoevents.Adapters.FormatHelper;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Helper class for showing and canceling event
 * notifications.
 * <p/>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class EventNotification {


    private static final String TAG = "Event";

    public static void notify(final Context context, final Events event) {

        final Resources res = context.getResources();

        final int number = event.getName().hashCode();

        final Bitmap picture = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);

        final String ticker = event.getName().getText();
        final String title = event.getName().getText();
        final String text = event.getDescription().getText();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                .setDefaults(Notification.DEFAULT_ALL)

                .setSmallIcon(R.drawable.ic_notif_dojo)

                .setContentTitle(title)

                .setContentText(text)

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                .setLargeIcon(picture)

                .setTicker(ticker)

                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(context, ActivityEventTabbed.class)
                                        .putExtra("event", event),
                                PendingIntent.FLAG_UPDATE_CURRENT))


                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text)
                        .setBigContentTitle(title)
                        .setSummaryText(FormatHelper.formatDate(event.getStart(), event.getEnd())))

                .addAction(
                        R.drawable.ic_ticket_small,
                        context.getString(R.string.take_ticket),
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse(event.getUrl())),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.ic_action_stat_share,
                        context.getString(R.string.share),
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse(event.getUrl())), //TODO SHARE
                                PendingIntent.FLAG_UPDATE_CURRENT))

                .setAutoCancel(true);

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(TAG, 0, notification);
        } else {
            nm.notify(TAG.hashCode(), notification);
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(TAG, 0);
        } else {
            nm.cancel(TAG.hashCode());
        }
    }
}
