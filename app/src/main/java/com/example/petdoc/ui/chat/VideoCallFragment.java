package com.example.petdoc.ui.chat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
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

        webView.setWebChromeClient(new WebChromeClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                return true;
            }
        });

        webView.loadUrl("https://meet.jit.si/PetDoc"+ MainActivity.USERID+"#config.disableDeepLinking=true");
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
        MyWebviewSettings.setMediaPlaybackRequiresUserGesture(false);
        //String desktopBrowser= "Mozilla/5.0";
        //MyWebviewSettings.setUserAgentString(desktopBrowser);
    }
}