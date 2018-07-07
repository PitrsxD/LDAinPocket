package hr.lda_verteneglio.ldainpocket.ldacalendardata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import hr.lda_verteneglio.ldainpocket.R;
import hr.lda_verteneglio.ldainpocket.ldawebdata.NewsItem;

public class CalendarAdapter extends ArrayAdapter<CalendarItem> {


    public CalendarAdapter(@NonNull Context context, List<CalendarItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.event_list, parent, false);
        }

        TextView eventTitle = listItemView.findViewById(R.id.event_title_textview1);

        TextView eventStartDate = listItemView.findViewById(R.id.event_date_textview1);

        TextView eventLocation = listItemView.findViewById(R.id.event_location_textview1);

        TextView eventDescription = listItemView.findViewById(R.id.event_text_textview1);

        final CalendarItem currentCalendarItem = getItem(position);

            eventTitle.setText(currentCalendarItem.getEventTitle());
            eventStartDate.setText(currentCalendarItem.getEventDate());
            eventLocation.setText(currentCalendarItem.getEventLocation());
            eventDescription.setText(currentCalendarItem.getEventText());



        return listItemView;
    }
}
