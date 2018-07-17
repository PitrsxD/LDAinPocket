package hr.lda_verteneglio.ldainpocket.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import hr.lda_verteneglio.ldainpocket.R;
import hr.lda_verteneglio.ldainpocket.SimpleFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        ViewPager viewPager = findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Upper navigation through fragments - will call sources from SimpleFragmentPagerAdapter.class
        //Will change also the background color of Tablayout and selected Text
        tabLayout = findViewById(R.id.navigation);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {

        int[] tabIcons = {
                R.drawable.ic_art_track_black_24dp,
                R.drawable.ic_event_black_24dp,
                R.drawable.ic_flight_takeoff_black_24dp,
                R.drawable.ic_more_black_24dp};

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}
