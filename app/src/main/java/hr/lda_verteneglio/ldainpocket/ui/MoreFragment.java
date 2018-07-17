package hr.lda_verteneglio.ldainpocket.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.lda_verteneglio.ldainpocket.R;

public class MoreFragment extends android.support.v4.app.Fragment {

    View rootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_more, container, false);
        //Thanks to context we will get name of Activity a start the right array list
        return rootView;
    }
}
