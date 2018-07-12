package hr.lda_verteneglio.ldainpocket;

import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarAdapter;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarItem;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarLoader;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsAdapter;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsItem;

public class ActivitiesActivity extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<List<CalendarItem>> {


    private final String urlEvents = "https://www.googleapis.com/calendar/v3/calendars/" +
            "lda.verteneglio%40gmail.com/events?";

    private final String eventKey = "";

    CalendarAdapter calendarAdapter;

    ListView eventListView;

    View rootView;

    Context context;

    ProgressBar progressBarEvents;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_activities, container, false);
        //Thanks to context we will get name of Activity a start the right array list

        calendarAdapter = new CalendarAdapter(this.getContext(), new ArrayList<CalendarItem>());

        eventListView = rootView.findViewById(R.id.events_list);

        progressBarEvents = rootView.findViewById(R.id.progress_bar_activities);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this).forceLoad();
            progressBarEvents.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    @Override
    public Loader<List<CalendarItem>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(urlEvents);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("maxResults", "2");
        uriBuilder.appendQueryParameter("orderBy", "startTime");
        uriBuilder.appendQueryParameter("singleEvents", "true");
        uriBuilder.appendQueryParameter("key", eventKey);

        return new CalendarLoader(this.getContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<CalendarItem>> loader, List<CalendarItem> data) {
        Log.i("Loader", "Finished");
        calendarAdapter.clear();
        progressBarEvents.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            calendarAdapter.addAll(data);
            eventListView.setAdapter(calendarAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<CalendarItem>> loader) {
        calendarAdapter.clear();
    }

    @Override
    public void onPause() {
        calendarAdapter.clear();
        super.onPause();
    }

    @Override
    public void onResume() {
        context = getContext();
        calendarAdapter = new CalendarAdapter(context, new ArrayList<CalendarItem>());

        eventListView = rootView.findViewById(R.id.events_list);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this).forceLoad();
            progressBarEvents.setVisibility(View.VISIBLE);
        super.onResume();

        }
    }

}
