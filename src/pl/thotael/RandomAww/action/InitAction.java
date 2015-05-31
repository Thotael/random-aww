package pl.thotael.RandomAww.action;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import pl.thotael.RandomAww.MainAwwActivity;
import pl.thotael.RandomAww.R;

/**
 * Created by SeBa on 2015-05-31.
 */
public class InitAction implements NetworkAction {

    private MainAwwActivity activity;

    public InitAction(MainAwwActivity activity) {
        this.activity = activity;
    }

    @Override
    public void performAction() {
        init();
    }

    public void init() {
        activity.setContentView(R.layout.main);
        activity.setWebView((WebView) activity.findViewById(R.id.webView));

        setupCatButton();
        setupDogButton();
        setupRandomizeButton();

        new DisplayDogOrCatAction(activity).performAction();
    }

    private void setupDogButton() {
        Button dogButton = (Button) activity.findViewById(R.id.dogButton);
        dogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.checkNetworkAndPerformAction(new DisplayDogAction(activity));
            }
        });
    }

    private void setupCatButton() {
        final Button catButton = (Button) activity.findViewById(R.id.catButton);
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.checkNetworkAndPerformAction(new DisplayCatAction(activity));
            }
        });
    }

    private void setupRandomizeButton() {
        Button randomizeButton = (Button) activity.findViewById(R.id.randomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.checkNetworkAndPerformAction(new DisplayDogOrCatAction(activity));
            }
        });
    }
}
