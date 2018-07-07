package hr.lda_verteneglio.ldainpocket;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarAdapter;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarItem;
import hr.lda_verteneglio.ldainpocket.ldacalendardata.CalendarLoader;

public class ActivitiesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CalendarItem>> {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    Intent intentNews = new Intent(ActivitiesActivity.this, NewsActivity.class);
                    startActivity(intentNews);
                    return true;
                case R.id.navigation_activities:
                    return true;
                case R.id.navigation_go_abroad:
                    Intent intentGoAbroad = new Intent(ActivitiesActivity.this, GoAbroadActivity.class);
                    startActivity(intentGoAbroad);
                    return true;
                case R.id.navigation_more:
                    Intent intentMore = new Intent(ActivitiesActivity.this, MoreActivity.class);
                    startActivity(intentMore);
                    return true;
            }
            return false;
        }
    };

    final String urlEvents = "https://www.googleapis.com/calendar/v3/calendars/lda.verteneglio%40gmail.com" +
            "/events?alwaysIncludeEmail=false&maxResults=2&orderBy=startTime&showDeleted=false&showHiddenInvitations=false&singleEvents=true";

    CalendarAdapter calendarAdapter;

    ListView eventListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_activities);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        calendarAdapter = new CalendarAdapter(this, new ArrayList<CalendarItem>());

        eventListView = findViewById(R.id.events_list);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this).forceLoad();
        }

    }

    @Override
    public Loader<List<CalendarItem>> onCreateLoader(int id, Bundle args) {
        return new CalendarLoader(this, urlEvents);
    }

    @Override
    public void onLoadFinished(Loader<List<CalendarItem>> loader, List<CalendarItem> data) {
        Log.i("Loader", "Finished");
        calendarAdapter.clear();

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
