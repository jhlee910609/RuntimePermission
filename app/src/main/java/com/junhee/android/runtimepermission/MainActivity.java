package com.junhee.android.runtimepermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button btnBack, btnFront, btnGo;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--------- [기본적인 WebView 셋팅] ---------

        btnBack = (Button) findViewById(R.id.btnBack);
        btnFront = (Button) findViewById(R.id.btnFront);
        btnGo = (Button) findViewById(R.id.btnGo);
        editText = (EditText) findViewById(R.id.editAddress);
        webView = (WebView) findViewById(R.id.webview);
        // script 사용필수로 셋팅해줘야함
        webView.getSettings().setJavaScriptEnabled(true);

        // 1. 웹뷰 클라이언트를 지정 (해당 작업을 하지 않으면 내장 브라우저가 팝업된다.)
        // http:// 프로토콜만 호출이 됨
        webView.setWebViewClient(new WebViewClient());

        // 2. 둘 다 셋팅할 것 : 프로토콜에 따라 클라이언트가 선택되는 것으로 파악됨..
        // https://
        webView.setWebChromeClient(new WebChromeClient());

        // uri 호출하기
        loadUrl("naver.com");
        //-------------------------------------------
        btnBack.setOnClickListener(this);
        btnGo.setOnClickListener(this);
        btnFront.setOnClickListener(this);

    }

    private void loadUrl(String url) {
        // 문자열의 앞에 프로토콜인 http 문자열이 없다면 붙혀준다.
        if (!url.startsWith("http://")) {
            // url 호출하기
            webView.loadUrl("http://naver.com");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                // 뒤로 갈 곳이 있는지 체크
                if(webView.canGoBack()){
                    webView.goBack();
                }

                // 뒤로가기
                break;

            case R.id.btnFront:
                // 앞으로 갈 곳이 있는지 체크
                if(webView.canGoForward()){
                    webView.goForward();
                }

                break;

            case R.id.btnGo:
                String url = editText.getText().toString();
                if (!"".equals(url)) {    // 공백이 아닐 경우만 처리
                                          // or 정규식으로 url 패턴일때만 처리
                                          //
                    loadUrl(url);
                }
                break;

        }

    }
}
