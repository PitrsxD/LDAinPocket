package hr.lda_verteneglio.ldainpocket.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

import hr.lda_verteneglio.ldainpocket.R;
import hr.lda_verteneglio.ldainpocket.ldapartnersdata.DownloadDatabaseTask;

public class PartnersDatabaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private String DB_DOWNLOAD_PATH = "http://svobodapeter.com/activity_partners.db";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);
        //DownloadDatabaseTask.execute(DB_DOWNLOAD_PATH);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //return new CursorLoader(this, ToolsEntry.CONTENT_URI, projection, null,
        //        null, null);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
