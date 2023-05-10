package com.example.petdoc.ui.chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;

import java.util.concurrent.ThreadLocalRandom;


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
        setMyWebviewSettings(webView);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
                return true;
            }
        });

        webView.loadUrl("https://meet.jit.si/PetDoc"+ MainActivity.USERID);
        return v;
    }

    private void setMyWebviewSettings(WebView webView) {
        WebSettings MyWebviewSettings = webView.getSettings();
        MyWebviewSettings.setAllowFileAccessFromFileURLs(true);
        MyWebviewSettings.setAllowUniversalAccessFromFileURLs(true);
        MyWebviewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        MyWebviewSettings.setJavaScriptEnabled(true);
        MyWebviewSettings.setDomStorageEnabled(true);
        MyWebviewSettings.setBuiltInZoomControls(true);
        MyWebviewSettings.setAllowFileAccess(true);
        MyWebviewSettings.setSupportZoom(true);
        MyWebviewSettings.setSupportMultipleWindows(true);
        String desktopBrowser= "Mozilla/5.0";
        MyWebviewSettings.setUserAgentString(desktopBrowser);
    }
}