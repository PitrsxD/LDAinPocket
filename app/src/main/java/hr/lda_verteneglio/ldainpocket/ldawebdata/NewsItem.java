package hr.lda_verteneglio.ldainpocket.ldawebdata;

public class NewsItem {

    private String mPostTitle;
    private String mPostText;
    private String mPostDate;
    private String mPostURL;
    private String mPostImageURL;

    public NewsItem (String postTitle, String postText, String postDate, String postURL,
                     String postImageURL) {
        mPostTitle = postTitle;
        mPostText = postText;
        mPostDate = postDate;
        mPostURL = postURL;
        mPostImageURL = postImageURL;
    }

    public NewsItem (String postTitle, String postText, String postDate, String postURL) {
        mPostTitle = postTitle;
        mPostText = postText;
        mPostDate = postDate;
        mPostURL = postURL;
    }

    public String getPostTitle () {return mPostTitle; }

    public String getPostText () {return mPostText; }

    public String getPostDate () {return mPostDate; }

    public String getPostURL () {return mPostURL; }

    public String getPostImageURL () {return mPostImageURL; }

}
