package com.aboammar.am.aboammar.SubFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboammar.am.aboammar.R;
import com.aboammar.am.aboammar.UI.MainActivity;

/**
 * Created by wakeel on 06/06/16.
 */
public class ElectronicFragment extends Fragment {
    MainActivity activity;

    public ElectronicFragment (){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.electronics_fragment, container, false);
    }
}
