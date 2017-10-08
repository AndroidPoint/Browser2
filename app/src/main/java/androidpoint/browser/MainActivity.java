package androidpoint.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;
    private WebView webview;
    private Button btn;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.pb);
        webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("www.Facebook.com");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Button search = (Button) findViewById(R.id.btn);
        assert search != null;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.et);
                String search = et.getText().toString().trim();
                if (search.isEmpty() && search.equals("")) {
                    search = "https://www.Facebook.com";


                } else {
                    search = "http://" + search;
                }
                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webview.loadUrl(search);
                webview.setWebViewClient(new WebViewClient());
                webview = (WebView) findViewById(R.id.webview);

            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        pb.setProgress(0);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int Progress) {
                pb.setProgress(Progress);
                if (Progress == 100) {
                    pb.setVisibility(view.GONE);
                } else {
                    pb.setVisibility(view.VISIBLE);
                }

                super.onProgressChanged(view, Progress);
            }

        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}



