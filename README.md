# Web View 만들기

### 1. 초기 셋팅하기

- 기본 웹뷰 위젯을 찾아 XML에 셋팅해줌

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  
  
  	WebView webView;
 	// Button, EditText 등 기본 설정 생략
  	// 실제 소스 상에는 구현함 
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
```



### 2. loadUrl(); 메소드 작성

- url 입력 시, http 프로토콜이 없다면 자동으로 문자열에 붙혀줌
  - 사용자가 'google.com'이라고만 입력해도 'http://google.com'으로 입력됨

```java
 private void loadUrl(String url) {
        // 문자열의 앞에 프로토콜인 http 문자열이 없다면 붙혀준다.
        if (!url.startsWith("http")) {
            // url 호출하기
            url = "http://" + url;
            webView.loadUrl("http://naver.com");
        }
    }
```

### 3. onClick(); 으로 버튼 탭 시, 작업 내용 구현

- 뒤로 갈 수 있는 웹페이지 혹은 앞으로 갈 수 있는 웹페이지가 없는 경우를 조건으로 명시해줘야함
  - .canGoBack(); - 뒤로 갈 페이지가 있는지를 체크할 수 있는 WebView 메소드
  - canGoForward(); - 앞으로 갈 페이지가 있는지를 체크할 수 있는 WebView 메소드

```java
@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                // 뒤로 갈 곳이 있는지 체크
                if (webView.canGoBack()) {
                    webView.goBack();
                }

                // 뒤로가기
                break;

            case R.id.btnFront:
                // 앞으로 갈 곳이 있는지 체크
                if (webView.canGoForward()) {
                    webView.goForward();
                }

                break;

            case R.id.btnGo:
                String url = editText.getText().toString();
                if (!"".equals(url)) {
                    if (url.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        // 공백이 아닐 경우만 처리
                        // or 정규식으로 url 패턴일때만 처리
                        loadUrl(url);
                    } else {
                        Toast.makeText(this, "url이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
```

- [전체 소스 페이지 이동](https://goo.gl/3iU9lu)

