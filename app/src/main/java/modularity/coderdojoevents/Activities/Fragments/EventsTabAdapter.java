package modularity.coderdojoevents.Activities.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

public class EventsTabAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = new String[3];
    private Events currentEvent;

    public EventsTabAdapter(Context context, FragmentManager fm, Events currentEvent) {
        super(fm);
        TITLES[0] = context.getString(R.string.tab_title_event);
        TITLES[1] = context.getString(R.string.tab_title_description);
        TITLES[2] = context.getString(R.string.tab_title_map);

        this.currentEvent = currentEvent;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return FragmentEventMain.newInstance(position, currentEvent);
        else if (position == 1)
            return FragmentEventDescription.newInstance(position, currentEvent);
        else if (position == 2)
            return FragmentEventMap.newInstance(position, currentEvent);
        else return FragmentEventMain.newInstance(position, currentEvent);
    }

}