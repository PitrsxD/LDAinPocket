package hr.lda_verteneglio.ldainpocket;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String tabTitles[] = new String[]{"Events", "Services"};

    public SimpleFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    /**
     * Constructor for fragment adapter
     *
     * @param position - coresponding with position of TabLayout and selecting right Fragment to show
     * @return
     */
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            return new NewsActivity();
        } else if (position == 1) {
            return new ActivitiesActivity();
        } else if (position == 2) {
            return new GoAbroadActivity();
        } else

        {
            return new MoreActivity();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                mContext.getResources().getDrawable(R.drawable.ic_art_track_black_24dp);
                return mContext.getString(R.string.title_news);
            case 1:
                mContext.getResources().getDrawable(R.drawable.ic_event_black_24dp);
                return mContext.getString(R.string.title_activities);
            case 2:
                mContext.getResources().getDrawable(R.drawable.ic_flight_takeoff_black_24dp);
                return mContext.getString(R.string.title_go_abroad);
            case 3:
                mContext.getResources().getDrawable(R.drawable.ic_more_black_24dp);
                return mContext.getString(R.string.title_more);
            default:
                return null;
        }
    }
}

