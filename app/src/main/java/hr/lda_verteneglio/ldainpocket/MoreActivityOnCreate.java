package hr.lda_verteneglio.ldainpocket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MoreActivityOnCreate extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        ViewPager cityPager = findViewById(R.id.city_pager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        cityPager.setAdapter(adapter);

        //Upper navigation through fragments - will call sources from SimpleFragmentPagerAdapter.class
        //Will change also the background color of Tablayout and selected Text
        TabLayout tabLayout = findViewById(R.id.navigation);
        tabLayout.setupWithViewPager(cityPager);
    }
}
