package com.akexorcist.mvpsimple.module.viewer.header;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.mvpsimple.R;

public class FeedImageHeaderFragment extends Fragment {


    public static FeedImageHeaderFragment newInstance() {
        FeedImageHeaderFragment fragment = new FeedImageHeaderFragment();
        return fragment;
    }

    public FeedImageHeaderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer_image_header, container, false);
    }

}
