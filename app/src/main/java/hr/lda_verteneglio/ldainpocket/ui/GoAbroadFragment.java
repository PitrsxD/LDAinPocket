package hr.lda_verteneglio.ldainpocket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.lda_verteneglio.ldainpocket.R;

public class GoAbroadFragment extends android.support.v4.app.Fragment {

        View rootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_go_abroad, container, false);
        //Thanks to context we will get name of Activity a start the right array list

        CardView youthExchangeCardView = rootView.findViewById(R.id.go_abroad_youth_exchange_cardview);
        CardView evsCardView = rootView.findViewById(R.id.go_abroad_evs_cardview);

        youthExchangeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youthExchangeIntent = new Intent(getContext(), GoAbroadYExActivity.class);
                startActivity(youthExchangeIntent);
            }
        });
        evsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent evsIntent = new Intent(getContext(), GoAbroadEVSActivity.class);
                startActivity(evsIntent);
            }
        });

        return rootView;
    }
}
