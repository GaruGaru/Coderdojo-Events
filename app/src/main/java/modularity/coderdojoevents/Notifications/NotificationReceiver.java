package modularity.coderdojoevents.Notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import modularity.coderdojoevents.Api.EventBrite.Android.AsyncBriteRequestArea;
import modularity.coderdojoevents.Api.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.Api.EventBrite.RequestBuilder.DojoRequestBuilder;
import modularity.coderdojoevents.Api.EventBrite.RequestBuilder.Request;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.Settings.DojoSettings;

public class NotificationReceiver extends BroadcastReceiver {

    private static final long DELAY = AlarmManager.INTERVAL_HALF_DAY;
    private static AlarmManager alarmInstance = null;

    public static void start(Context context) {
        Intent alarmIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        alarmInstance = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmInstance.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), DELAY, pendingIntent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final DojoSettings settings = new DojoSettings(context);
        Request request = DojoRequestBuilder.build(settings);

        new AsyncBriteRequestArea(new BriteListener() {
            @Override
            public void onRequestDone(BriteEvent eventList) {
                requestOk(context, settings, eventList);
            }

            @Override
            public void onRequestError(int errorCode) {
                // Ignore
            }
        }).execute(request);

    }

    private void requestOk(Context context, DojoSettings settings, BriteEvent event) {

        if (settings.getEvents() != null) {
            Events[] oldEvents = settings.getEvents().getEvents();

            Events[] newEvents = event.getEvents();
            List<Events> differences = NotificationHelper.getDifferences(oldEvents, newEvents);

            for (Events e : differences)
                NotificationHelper.showEventNotification(context, e);

        }

        settings.setEvents(event);
    }

}