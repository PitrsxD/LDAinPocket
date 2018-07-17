package hr.lda_verteneglio.ldainpocket.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.R;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarAdapter;
import hr.lda_verteneglio.ldainpocket.Config;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarItem;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarLoader;

public class ActivitiesFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<List<CalendarItem>> {

    private final String urlEvents = "https://www.googleapis.com/calendar/v3/calendars/" +
            "lda.verteneglio%40gmail.com/events?";

    private final String eventKey = Config.KEY;

    private CalendarAdapter calendarAdapter;
    private ListView eventListView;
    private View rootView;
    private ProgressBar progressBarEvents;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activities, container, false);
        //Thanks to context we will get name of Activity a start the right array list

        calendarAdapter = new CalendarAdapter(this.getContext(), new ArrayList<CalendarItem>());
        eventListView = rootView.findViewById(R.id.events_list);
        View emptyViewEvents = rootView.findViewById(R.id.empty_view_events);
        eventListView.setEmptyView(emptyViewEvents);
        progressBarEvents = rootView.findViewById(R.id.progress_bar_activities);

        CardView partnersCardView = rootView.findViewById(R.id.partners_cardview);
        partnersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPartners = new Intent(getContext(), PartnersDatabaseActivity.class);
                startActivity(openPartners);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        calendarAdapter.clear();
        super.onPause();
    }


    @Override
    public void onResume() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManagerEvents = getLoaderManager();
            loaderManagerEvents.initLoader(0, null, this).forceLoad();

            LoaderManager loaderManagerPartners = getLoaderManager();
            loaderManagerPartners.initLoader(1,null,this).forceLoad();
            super.onResume();
        } else {
            progressBarEvents.setVisibility(View.GONE);
            ImageView emptyImageViewEvents = rootView.findViewById(R.id.empty_imageview_events);
            TextView emptyTextViewEvents = rootView.findViewById(R.id.empty_textview_events);
            emptyImageViewEvents.setImageResource(R.drawable.ic_signal_no_internet);
            emptyTextViewEvents.setText(R.string.missing_connectivity);
            super.onResume();
        }
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

}
