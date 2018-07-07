package hr.lda_verteneglio.ldainpocket.ldacalendardata;

public class CalendarItem {

    private String mEventTitle;
    private String mEventText;
    private String mEventDate;
    private String mEventLocation;

    public CalendarItem(String eventTitle, String eventText, String eventDate, String eventLocation) {
        mEventTitle = eventTitle;
        mEventText = eventText;
        mEventDate = eventDate;
        mEventLocation = eventLocation;
    }


    public String getEventTitle() {
        return mEventTitle;
    }

    public String getEventText() {
        return mEventText;
    }

    public String getEventDate() {
        return mEventDate;
    }

    public String getEventLocation() {
        return mEventLocation;
    }

}
