package hr.lda_verteneglio.ldainpocket.ldawebdata;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.R;

/**
 * The MIT License (MIT) - For ImageLoader with name VolleySingletion
 * <p>
 * Copyright (c) 2014 Cypress North
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p>
 * Original link to the source: https://github.com/CypressNorth/Volley-Singleton
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public NewsAdapter(Context context, List<NewsItem> objects) {
        super(context, 0, objects);
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false);

        }

        NetworkImageView postImageView = listItemView.findViewById(R.id.post_image_imageview);
        TextView postDateTextView = listItemView.findViewById(R.id.post_date_textview);
        TextView postTitleTextView = listItemView.findViewById(R.id.post_title_textview);
        TextView postTextTextView = listItemView.findViewById(R.id.post_text_textview);
        LinearLayout postNewsLayout = listItemView.findViewById(R.id.post_news_layout);

        final NewsItem currentNewsItem = getItem(position);

        try {
            URL urlImage = new URL(currentNewsItem.getPostImageURL());
            if (urlImage != null) {
                postImageView.setImageUrl(currentNewsItem.getPostImageURL(), mImageLoader);
            }
        } catch (MalformedURLException urlE) {
            Log.e("NewsAdapter", "Error with getting URL from string", urlE);
        }

        StringBuilder sbTitle = new StringBuilder();
        sbTitle.append(currentNewsItem.getPostTitle()).setLength(70);
        if (currentNewsItem.getPostTitle().length() > 70) {
            sbTitle.append("...");
        }
        String mTitle = sbTitle.toString();

        StringBuilder sbText = new StringBuilder();
        sbText.append(currentNewsItem.getPostText()).setLength(140);
        sbText.append("...");
        String mText = sbText.toString();

        String mDate = currentNewsItem.getPostDate();
        if (mDate != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                Date newDate = DATE_FORMAT.parse(mDate);
                DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
                String date = DATE_FORMAT.format(newDate);
                postDateTextView.setText(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (mTitle != null) {
            postTitleTextView.setText(mTitle);
        }
        if (mText != null) {
            postTextTextView.setText(mText);
        }

        postNewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openUrl = new Intent(Intent.ACTION_VIEW);
                openUrl.setData(Uri.parse(currentNewsItem.getPostURL()));
                getContext().startActivity(openUrl);
            }
        });

        return listItemView;

    }
}
