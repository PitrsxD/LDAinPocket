package hr.lda_verteneglio.ldainpocket.ldawebdata;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NewsQueryUtils {

    public static List<NewsItem> fetchNews(String requestURL) {

        URL url = createURL(requestURL);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException inputE) {
            Log.e ("NewsUtils", "Problem with closing InputStream", inputE);
        }
        List<NewsItem> newsItem = extractNewsFromJson(jsonResponse);
        return newsItem;
    }

    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException urlE) {
            Log.e("NewsUtils","Error with parsing url to URL", urlE);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (jsonResponse == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.i("NewsUtils","Response code of web: " +
                    String.valueOf(urlConnection.getResponseCode()));

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e ("NewsUtils","Error with connection: " + urlConnection.getResponseCode());
            }
        } catch (IOException conE) {
            Log.e("NewsUtils","Problem with retrieving data", conE);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"posts\":");
        sb.append(jsonResponse);
        sb.append("}");
        jsonResponse = sb.toString();
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilderOutput = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilderOutput.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilderOutput.toString();
    }

    public static List<NewsItem> extractNewsFromJson(String jsonResponse){
        ArrayList<NewsItem> newsItem = new ArrayList<>();
        try {
            JSONObject reader = new JSONObject(jsonResponse);
            JSONArray postsArray = reader.getJSONArray("posts");
            for (int i = 0; i < postsArray.length();i++) {
                JSONObject arrayItem = postsArray.getJSONObject(i);

                JSONObject title = arrayItem.getJSONObject("title");
                String titleRen = title.getString("rendered");
                String titleRep = Html.fromHtml(titleRen).toString();

                JSONObject content = arrayItem.getJSONObject("excerpt");
                String contentRen = content.getString("rendered");
                String contentRep = Html.fromHtml(contentRen).toString();

                String date = arrayItem.getString("date");

                String linkUrl = arrayItem.getString("link");

                JSONObject imageObject = arrayItem.getJSONObject("better_featured_image");
                JSONObject mediaDetails = imageObject.getJSONObject("media_details");
                JSONObject sizes = mediaDetails.getJSONObject("sizes");
                JSONObject thumbnail = sizes.getJSONObject("thumbnail");
                String imageUrl = thumbnail.getString("source_url");

                if (!imageUrl.isEmpty()) {
                    newsItem.add(new NewsItem(titleRep, contentRep, date, linkUrl, imageUrl));
                    Log.i("NewsUtils", newsItem.toString());
                } else {
                    newsItem.add(new NewsItem(titleRep, contentRep, date, linkUrl));
                    Log.i("NewsUtils", newsItem.toString());
                }
            }
        } catch (JSONException jsonE) {
            Log.e("NewsUtils", "Problem with parsing JSON data to NewsItem items");
        }
        return newsItem;
    }
}
