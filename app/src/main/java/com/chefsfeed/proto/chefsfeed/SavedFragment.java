package com.chefsfeed.proto.chefsfeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nbelokopytov on 07.04.2015.
 */
public class SavedFragment extends Fragment {

    public SavedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_saved, container, false);
    }
}