package hr.lda_verteneglio.ldainpocket.ldacalendardata;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsItem;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsQueryUtils;

public class CalendarLoader extends AsyncTaskLoader<List<CalendarItem>> {

    private String mUrl;

    public CalendarLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<CalendarItem> loadInBackground() {
        Log.i("Loader", "loaded");
        if (mUrl == null) {
            return null;
        }
        List<CalendarItem> calenderList = CalendarQueryUtils.fetchEvents(mUrl);
        return  calenderList;
    }

}
