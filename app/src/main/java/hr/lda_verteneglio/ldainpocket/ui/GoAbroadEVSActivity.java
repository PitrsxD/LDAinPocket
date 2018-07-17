package hr.lda_verteneglio.ldainpocket.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import hr.lda_verteneglio.ldainpocket.Config;
import hr.lda_verteneglio.ldainpocket.R;

public class GoAbroadEVSActivity extends AppCompatActivity {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_abroad_evs);

        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayer_evs);
        youTubePlayerFragment.initialize(Config.KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.cueVideo("pIz85_qYP9E");
                }
            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()) {
                    youTubeInitializationResult.getErrorDialog(GoAbroadEVSActivity.this, RECOVERY_DIALOG_REQUEST).show();
                } else {
                    Toast.makeText(GoAbroadEVSActivity.this, "There was an error with initialization.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}


