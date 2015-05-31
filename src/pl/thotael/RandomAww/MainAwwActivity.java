package pl.thotael.RandomAww;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import pl.thotael.RandomAww.action.InitAction;
import pl.thotael.RandomAww.action.NetworkAction;

import static android.content.DialogInterface.OnClickListener;

public class MainAwwActivity extends Activity {

    private WebView webView;

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public void displayInWebView(String html) {
        webView.loadData(html, "text/html", null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        checkNetworkAndPerformAction(new InitAction(this));
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.ic_launcher);
        }
    }

    public void checkNetworkAndPerformAction(final NetworkAction action) {
        if (isOnline()) {
            action.performAction();
        } else {
            displayNoConnectionDialog(action);
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void displayNoConnectionDialog(final NetworkAction action) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(getString(R.string.no_connection_msg));

        createExitButton(alertDialog);
        prepareRetryButton(action, alertDialog);

        if (action instanceof InitAction) {
            alertDialog.setCancelable(false);
        }

        alertDialog.show();
    }

    private void prepareRetryButton(final NetworkAction action, AlertDialog.Builder alertDialog) {
        alertDialog.setPositiveButton(R.string.retry_msg, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkNetworkAndPerformAction(action);
            }
        });
    }

    private void createExitButton(AlertDialog.Builder alertDialog) {
        alertDialog.setNegativeButton(R.string.exit_msg, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
    }
}
