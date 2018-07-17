package hr.lda_verteneglio.ldainpocket;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hr.lda_verteneglio.ldainpocket.ui.ActivitiesFragment;
import hr.lda_verteneglio.ldainpocket.ui.GoAbroadFragment;
import hr.lda_verteneglio.ldainpocket.ui.MoreFragment;
import hr.lda_verteneglio.ldainpocket.ui.NewsFragment;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;


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
            return new NewsFragment();
        } else if (position == 1) {
            return new ActivitiesFragment();
        } else if (position == 2) {
            return new GoAbroadFragment();
        } else

        {
            return new MoreFragment();
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
                return mContext.getString(R.string.title_news);
            case 1:
                return mContext.getString(R.string.title_activities);
            case 2:
                return mContext.getString(R.string.title_go_abroad);
            case 3:
                return mContext.getString(R.string.title_more);
            default:
                return null;
        }
    }


}

