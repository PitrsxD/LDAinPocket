package hr.lda_verteneglio.ldainpocket;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsAdapter;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsItem;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsLoader;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {

    final String urlNews = "http://www.lda-verteneglio.hr/wp-json/wp/v2/posts?per_page=4";

    NewsAdapter newsAdapter;

    ListView newsListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    return true;
                case R.id.navigation_activities:
                    Intent intentActivities = new Intent(NewsActivity.this,ActivitiesActivity.class);
                    startActivity(intentActivities);
                    return true;
                case R.id.navigation_go_abroad:
                    Intent intentGoAbroad = new Intent(NewsActivity.this,GoAbroadActivity.class);
                    startActivity(intentGoAbroad);
                    return true;
                case R.id.navigation_more:
                    Intent intentMore = new Intent(NewsActivity.this,MoreActivity.class);
                    startActivity(intentMore);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_news);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        newsAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());

        newsListView = findViewById(R.id.news_list);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        }
    }

    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String numberOfposts = sharedPref.getString(
                getString(R.string.setting_how_many_of_posts),
                getString(R.string.setting_number_of_posts)
        );

        Uri baseUri = Uri.parse(urlNews);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("per_page", numberOfposts);

        return new NewsLoader(this, uriBuilder.toString());
    }



    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        Log.i("Loader", "Finished");
        newsAdapter.clear();

        if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
            newsListView.setAdapter(newsAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        Log.i("Loader", "reseted");
        newsAdapter.clear();
    }

    @Override
    protected void onPause() {
        newsAdapter.clear();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        newsAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());

        newsListView = findViewById(R.id.news_list);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this).forceLoad();
        }
    }
}
