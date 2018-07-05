package hr.lda_verteneglio.ldainpocket.ldawebdata;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<NewsItem> loadInBackground() {
        Log.i("Loader", "loaded");
        if (mUrl == null) {
            return null;
        }
        List<NewsItem> newsList = NewsQueryUtils.fetchNews(mUrl);
        return  newsList;
    }
}
