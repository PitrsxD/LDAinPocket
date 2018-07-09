package hr.lda_verteneglio.ldainpocket.ldacalendardata;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
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

public class CalendarQueryUtils {

    public static List<CalendarItem> fetchEvents(Context context, String requestURL) {

        URL url = createURL(requestURL);


        String jsonResponse = null;
        try {
            AccountManager am = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
            ;
            Bundle options = new Bundle();
            Account[] myAccount_ = am.getAccountsByType("com.google");
            String SCOPE = "https://www.googleapis.com/auth/calendar.readonly";
            //am.getAuthToken(myAccount_, "View your tasks",options, context, new OnTokenAcquired(), new Handler (new OnError()));
            jsonResponse = makeHttpRequest(url);
        } catch (IOException inputE) {
            Log.e("CalendarUtils", "Problem with closing InputStream", inputE);
        }
        List<CalendarItem> calendarItem = extractCalendarFromJson(jsonResponse);
        return calendarItem;
    }

    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException urlE) {
            Log.e("CalendarUtils", "Error with parsing url to URL", urlE);
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
            urlConnection.addRequestProperty("client_id", "1057977611894-t0snj8jdu0o27dcp5dctfer" +
                    "bmjkbltiq.apps.googleusercontent.com");
            //urlConnection.addRequestProperty();
            urlConnection.connect();
            Log.i("CalendarUtils", "Response code of web: " +
                    String.valueOf(urlConnection.getResponseCode()));

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("CalendarUtils", "Error with connection: " + urlConnection.getResponseCode());
            }
        } catch (IOException conE) {
            Log.e("CalendarUtils", "Problem with retrieving data", conE);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
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

    public static List<CalendarItem> extractCalendarFromJson(String jsonResponse) {
        ArrayList<CalendarItem> calendarItem = new ArrayList<>();
        try {
            JSONObject reader = new JSONObject(jsonResponse);
            JSONArray eventsArray = reader.getJSONArray("items");
            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject items = eventsArray.getJSONObject(i);
                String eventTitle = items.getString("summary");
                String eventText = items.getString("description");
                JSONObject startEvent = items.getJSONObject("start");
                String eventDate = startEvent.getString("dateTime");
                String eventLocation = items.getString("location");

                calendarItem.add(new CalendarItem(eventTitle, eventText, eventDate, eventLocation));
                Log.i("CalendarUtils", calendarItem.toString());
            }
        } catch (JSONException jsonE) {
            Log.e("CalendarUtils", "Problem with parsing JSON data to CalendarItem items");
        }
        return calendarItem;
    }

}

