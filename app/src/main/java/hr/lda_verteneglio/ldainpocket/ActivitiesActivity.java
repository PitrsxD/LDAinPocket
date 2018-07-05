package hr.lda_verteneglio.ldainpocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivitiesActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    Intent intentNews = new Intent(ActivitiesActivity.this,NewsActivity.class);
                    startActivity(intentNews);
                    return true;
                case R.id.navigation_activities:
                    return true;
                case R.id.navigation_go_abroad:
                    Intent intentGoAbroad = new Intent(ActivitiesActivity.this,GoAbroadActivity.class);
                    startActivity(intentGoAbroad);
                    return true;
                case R.id.navigation_more:
                    Intent intentMore = new Intent(ActivitiesActivity.this,MoreActivity.class);
                    startActivity(intentMore);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_activities);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
