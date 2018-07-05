package hr.lda_verteneglio.ldainpocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class GoAbroadActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    Intent intentNews = new Intent(GoAbroadActivity.this,NewsActivity.class);
                    startActivity(intentNews);
                    return true;
                case R.id.navigation_activities:
                    Intent intentActivities = new Intent(GoAbroadActivity.this,ActivitiesActivity.class);
                    startActivity(intentActivities);
                    return true;
                case R.id.navigation_go_abroad:
                    return true;
                case R.id.navigation_more:
                    Intent intentMore = new Intent(GoAbroadActivity.this,MoreActivity.class);
                    startActivity(intentMore);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_abroad);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_go_abroad);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
