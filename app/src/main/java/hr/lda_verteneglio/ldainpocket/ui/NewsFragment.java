package hr.lda_verteneglio.ldainpocket.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsAdapter;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsItem;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsLoader;

public class NewsFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<List<NewsItem>> {

    final String urlNews = "http://www.lda-verteneglio.hr/wp-json/wp/v2/posts?per_page=4";

    private NewsAdapter newsAdapter;

    private ListView newsListView;

    private View rootView;

    private ProgressBar progressBarNews;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container, false);
        //Thanks to context we will get name of Activity a start the right array list

        newsAdapter = new NewsAdapter(this.getContext(), new ArrayList<NewsItem>());

        newsListView = rootView.findViewById(R.id.news_list);
        View emptyViewPosts = rootView.findViewById(R.id.empty_view_posts);
        newsListView.setEmptyView(emptyViewPosts);

        progressBarNews = rootView.findViewById(R.id.progress_bar_news);

        return rootView;
    }

    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());

        String numberOfposts = sharedPref.getString(
                getString(R.string.setting_how_many_of_posts),
                getString(R.string.setting_number_of_posts)
        );

        Uri baseUri = Uri.parse(urlNews);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("per_page", numberOfposts);

        return new NewsLoader(this.getContext(), uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        Log.i("Loader", "Finished");
        newsAdapter.clear();
        progressBarNews.setVisibility(View.GONE);

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
    public void onResume() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nIfo = cm.getActiveNetworkInfo();

        if (nIfo != null && nIfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this).forceLoad();
        } else {
            progressBarNews.setVisibility(View.GONE);
            ImageView emptyImageViewPosts = rootView.findViewById(R.id.empty_imageview_posts);
            TextView emptyTextViewPosts = rootView.findViewById(R.id.empty_textview_posts);
            emptyImageViewPosts.setImageResource(R.drawable.ic_signal_no_internet);
            emptyTextViewPosts.setText(R.string.missing_connectivity);
        }

        super.onResume();
    }


}
