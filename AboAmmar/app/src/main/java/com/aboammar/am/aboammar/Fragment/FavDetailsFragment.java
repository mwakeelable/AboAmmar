package com.aboammar.am.aboammar.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboammar.am.aboammar.R;
import com.aboammar.am.aboammar.UI.MainActivity;

/**
 * Created by Mahmoud on 22-May-16.
 */
public class FavDetailsFragment extends Fragment {
    MainActivity activity;

    public FavDetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.fav_details_fragment, container, false);
    }
}
