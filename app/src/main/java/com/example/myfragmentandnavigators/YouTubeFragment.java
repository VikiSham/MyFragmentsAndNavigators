package com.example.myfragmentandnavigators;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class YouTubeFragment extends Fragment {
    WebView wvYouTube;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_you_tube, container, false);
        wvYouTube=view.findViewById(R.id.wvYouTube);

        wvYouTube.getSettings().setJavaScriptEnabled(true);
        wvYouTube.setWebViewClient(new WebViewClient());
        wvYouTube.loadUrl("https://www.youtube.com/");
        return view;
    }
}