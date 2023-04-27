package com.example.petdoc.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.petdoc.R;


public class VideoCallFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_call, container, false);
        WebView webView = v.findViewById(R.id.web_view);
        //webView.loadUrl("http://google.com");
        webView.loadUrl("http://meet.google.com/new");
        //sendUrl();
        return v;
    }
}