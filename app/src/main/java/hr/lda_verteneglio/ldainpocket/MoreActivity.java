package hr.lda_verteneglio.ldainpocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MoreActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    Intent intentNews = new Intent(MoreActivity.this,NewsActivity.class);
                    startActivity(intentNews);
                    return true;
                case R.id.navigation_activities:
                    Intent intentActivities = new Intent(MoreActivity.this,ActivitiesActivity.class);
                    startActivity(intentActivities);
                    return true;
                case R.id.navigation_go_abroad:
                    Intent intentGoAbroad = new Intent(MoreActivity.this,GoAbroadActivity.class);
                    startActivity(intentGoAbroad);
                    return true;
                case R.id.navigation_more:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_more);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
