package modularity.coderdojoevents.Activities.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import modularity.coderdojoevents.EventBrite.Response.Events;

public class EventsTabAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Evento", "Descrizione", "Mappa", "PlaceHolder"};
    private Events currentEvent;

    public EventsTabAdapter(FragmentManager fm, Events currentEvent) {
        super(fm);
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