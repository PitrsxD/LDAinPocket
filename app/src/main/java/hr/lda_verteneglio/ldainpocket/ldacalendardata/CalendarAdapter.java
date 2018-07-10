package hr.lda_verteneglio.ldainpocket.ldacalendardata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.lda_verteneglio.ldainpocket.R;

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

        String mDate = currentCalendarItem.getEventDate();
        if (mDate != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
            try {
                Date newDate = DATE_FORMAT.parse(mDate);
                DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String date = DATE_FORMAT.format(newDate);
                eventStartDate.setText(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        eventLocation.setText(currentCalendarItem.getEventLocation());

        StringBuilder sbText = new StringBuilder();
        sbText.append(currentCalendarItem.getEventText()).setLength(150);
        if (currentCalendarItem.getEventText().length() > 150) {
            sbText.append("...");
        }
        String mText = sbText.toString();
        eventDescription.setText(mText);


        return listItemView;
    }
}
