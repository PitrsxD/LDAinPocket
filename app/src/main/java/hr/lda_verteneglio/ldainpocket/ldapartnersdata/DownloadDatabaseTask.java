package hr.lda_verteneglio.ldainpocket.ldapartnersdata;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadDatabaseTask extends AsyncTask<String, Integer, Boolean> {



    private PartnerDBHelper mDB;

    InputStream inputStream;

    @Override
    protected Boolean doInBackground(String... mUrl) {
        try {
            File dbDownloadPath = new File(PartnerDBHelper.getDatabaseFolder());
            if (!dbDownloadPath.exists()) {
                dbDownloadPath.mkdir();
            }

            URL url = null;

            try {
                url = new URL(mUrl[0]);
            } catch (MalformedURLException urlE) {
                Log.e("NewsUtils", "Error with parsing url to URL", urlE);
            }


            HttpURLConnection urlConnection = null;
            inputStream = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(PartnerDBHelper.getDatabaseFolder());
            } else {
                Log.e("NewsUtils", "Error downloading DB: " + urlConnection.getResponseCode());
            }
        } catch (IOException conE) {
            Log.e("NewsUtils", "Problem with retrieving data", conE);
            {
            }
        }

        return null;
    }


}
