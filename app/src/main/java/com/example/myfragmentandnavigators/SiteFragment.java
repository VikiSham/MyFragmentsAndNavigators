package com.example.myfragmentandnavigators;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SiteFragment extends Fragment {

    WebView wvStack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site, container, false);
        wvStack=view.findViewById(R.id.wvStack);

        wvStack.getSettings().setJavaScriptEnabled(true);
        wvStack.setWebViewClient(new WebViewClient());
        wvStack.loadUrl("https://stackoverflow.com/");
        return view;
    }
}